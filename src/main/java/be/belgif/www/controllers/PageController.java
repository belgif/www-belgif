/*
 * Copyright (c) 2020, Bart Hanssens <bart.hanssens@bosa.fgov.be>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package be.belgif.www.controllers;

import be.belgif.www.Store;
import be.belgif.www.dao.Dao;
import be.belgif.www.dao.EifDao;
import be.belgif.www.dao.EifPrinciple;
import be.belgif.www.dao.EifRecommendation;
import be.belgif.www.dao.Legislation;
import be.belgif.www.dao.Organization;
import be.belgif.www.dao.Page;
import be.belgif.www.dao.Specification;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

/**
 *
 * @author Bart.Hanssens
 */
@Controller("/page")
public class PageController {
	@Inject
	Store store;

	private <T extends EifDao> List<T> sortBySeq(Map<String,T> map) {
		return map.values().stream().sorted((a,b) -> a.getSequence() - b.getSequence())
												.collect(Collectors.toUnmodifiableList());
	}
	private <T extends Dao> List<T> sortByTitle(Map<String,T> map, String lang) {
		return map.values().stream().sorted((a,b) -> a.getTitle(lang).compareTo(b.getTitle(lang)))
												.collect(Collectors.toUnmodifiableList());
	}
	private List<Legislation> sortByDate(Map<String,Legislation> map) {
		return map.values().stream().sorted((a,b) -> a.getDate().compareTo(b.getDate()))
												.collect(Collectors.toUnmodifiableList());
	}

	private <T extends EifDao> Map<String,T> filter(Map<String,T> a, List<String> b) {
		return a.values().stream()
				.filter(f -> b.contains(f.getLocalId()))
				.collect(Collectors.toMap(f -> f.getLocalId(), f -> f));
	}

	@View("page")
	@Get("/{id}.{lang}.html")
	public HttpResponse page(String id, String lang) {
		Page page = store.getPages().get(id);
		return HttpResponse.ok(Map.of( "lang", lang, "name", id, "p", page));
	}
	
	@View("integrators")
	@Get("/integrators.{lang}.html")
	public HttpResponse integrators(String lang) {
		Page page = store.getPages().get("integrators");
		List<Organization> integrators = sortByTitle(store.getIntegrators(), lang);
		return HttpResponse.ok(Map.of( "lang", lang, "p", page, "name", "integrators", "integrators", integrators));
	}

	@View("legislation")
	@Get("/legislation/{id}.{lang}.html")
	public HttpResponse legislation(String id, String lang) {
		Legislation legislation = store.getLegislations().get(id);

		List<EifPrinciple> principles = sortBySeq(filter(store.getPrinciples(), legislation.getPrinciples()));	
		List<EifRecommendation> recommendations = sortBySeq(filter(store.getRecommendations(), 
																		legislation.getRecommendations()));

		return HttpResponse.ok(Map.of("lang", lang, "name", id, "legislation", legislation, 
										"principles", principles, "recommendations", recommendations));
	}

	@View("legislations")
	@Get("/legislations.{lang}.html")
	public HttpResponse legislations(String lang) {
		Page page = store.getPages().get("legislations");
		List<Legislation> legislations = sortByDate(store.getLegislations());
		return HttpResponse.ok(Map.of("lang", lang, "name", "legislations", "p", page, "legislations", legislations));
	}
	@View("specification")
	@Get("/specification/{id}.{lang}.html")
	public HttpResponse specification(String id, String lang) {
		Specification specification = store.getSpecifications().get(id);
		return HttpResponse.ok(Map.of("lang", lang, "name", id, "specification", specification));
	}

	@View("specifications")
	@Get("/specifications.{lang}.html")
	public HttpResponse specifications(String lang) {
		Page page = store.getPages().get("specifications");
		List<Specification> specifications = sortByTitle(store.getSpecifications(), lang);
		return HttpResponse.ok(Map.of("lang", lang, "name", "specifications", "p", page, "specifications", specifications));
	}
}
