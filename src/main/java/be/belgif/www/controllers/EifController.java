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
import be.belgif.www.dao.EifPrinciple;
import be.belgif.www.dao.EifRecommendation;
import io.micronaut.core.util.CollectionUtils;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

/**
 *
 * @author Bart.Hanssens
 */
@Controller("/eif3")
public class EifController {
	@Inject
	Store store;

	@View("eif3")
	@Get("/")
	public HttpResponse eif() {
		return HttpResponse.ok();
	}
	
	@View("principles")
	@Get("/principle")
	public HttpResponse principles() {	
		List<EifPrinciple> list = store.getPrinciples().values().stream()
												.sorted((a,b) -> a.getSequence() - b.getSequence())
												.collect(Collectors.toUnmodifiableList());
		return HttpResponse.ok(CollectionUtils.mapOf("lang", "en" ,"principles", list));
	}

	@View("principles")
	@Get("/principle/{id}")
	public HttpResponse principle(String id) {
		return HttpResponse.ok();
	}

	@View("recommendations")
	@Get("/recommendation")
	public HttpResponse recommendations() {
		List<EifRecommendation> list = store.getRecommendations().values().stream()
												.sorted((a,b) -> a.getSequence() - b.getSequence())
												.collect(Collectors.toUnmodifiableList());
		return HttpResponse.ok(CollectionUtils.mapOf("lang", "en" ,"recommendations", list));
	}

	@View("recommendation")
	@Get("/recommendation/{id}")
	public HttpResponse recommendation(String id) {
		return HttpResponse.ok();
	}	
}
