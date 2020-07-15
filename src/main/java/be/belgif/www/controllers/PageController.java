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

import javax.inject.Inject;

/**
 *
 * @author Bart.Hanssens
 */
@Controller("/page")
public class PageController {
	@Inject
	Store store;

	private List<Organization> sortByName(Map<String,Organization> map, String lang) {
		return map.values().stream().sorted((a,b) -> a.getName(lang).compareTo(b.getName(lang)))
												.collect(Collectors.toUnmodifiableList());
	}

	private List<Specification> sortByTitle(Map<String,Specification> map, String lang) {
		return map.values().stream().sorted((a,b) -> a.getTitle(lang).compareTo(b.getTitle(lang)))
												.collect(Collectors.toUnmodifiableList());
	}

	@View("page")
	@Get("/{id}.{lang}.html")
	public HttpResponse page(String id, String lang) {
		Page page = store.getPages().get(id);
		return HttpResponse.ok(Map.of("p", page, "lang", lang));
	}
	
	@View("integrators")
	@Get("/integrators.{lang}.html")
	public HttpResponse integrators(String lang) {
		Page page = store.getPages().get("integrators");
		List<Organization> integrators = sortByName(store.getIntegrators(), lang);
		return HttpResponse.ok(Map.of("p", page, "lang", lang, "integrators", integrators));
	}

	@View("specification")
	@Get("/specification/{id}.{lang}.html")
	public HttpResponse specifications(String id, String lang) {
		Specification specification = store.getSpecifications().get(id);
		return HttpResponse.ok(Map.of("lang", lang, "specification", specification));
	}

	@View("specifications")
	@Get("/specifications.{lang}.html")
	public HttpResponse specifications(String lang) {
		Page page = store.getPages().get("specifications");
		List<Specification> specifications = sortByTitle(store.getSpecifications(), lang);
		return HttpResponse.ok(Map.of("p", page, "lang", lang, "specifications", specifications));
	}
}
