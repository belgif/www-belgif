/*
 * Copyright (c) 2022, FPS BOSA
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
package be.belgif.www.generators;

import be.belgif.www.Store;
import be.belgif.www.dao.Activity;
import be.belgif.www.dao.Dao;
import be.belgif.www.dao.DaoEif;
import be.belgif.www.dao.DaoRelated;
import be.belgif.www.dao.EifPrinciple;
import be.belgif.www.dao.EifRecommendation;
import be.belgif.www.dao.Legislation;
import be.belgif.www.dao.Link;
import be.belgif.www.dao.Organization;
import be.belgif.www.dao.Page;
import be.belgif.www.dao.Specification;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Controller for most (non-EIF3) webpages
 * 
 * @author Bart.Hanssens
 */

public class PageGenerator extends Generator {
	private final Store store;

	/** 
	 * Order by sequence number
	 */
	private <T extends DaoEif> List<T> sortBySeq(Map<String,T> map) {
		return map.values().stream().sorted((a,b) -> a.getSequence() - b.getSequence())
												.collect(Collectors.toUnmodifiableList());
	}

	/** 
	 * Order by title
	 */
	private <T extends Dao> List<T> sortByTitle(Map<String,T> map, String lang) {
		return map.values().stream().sorted((a,b) -> a.getTitle(lang).compareTo(b.getTitle(lang)))
												.collect(Collectors.toUnmodifiableList());
	}

	/** 
	 * Order by date
	 */
	private List<Legislation> sortByDate(Map<String,Legislation> map) {
		return map.values().stream().sorted((a,b) -> - a.getDate().compareTo(b.getDate()))
												.collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Lookup a list of items in a map
	 */
	private <T extends DaoEif> Map<String,T> lookup(Map<String,T> a, List<String> b) {
		return a.values().stream()
				.filter(f -> b.contains(f.getLocalId()))
				.collect(Collectors.toMap(f -> f.getLocalId(),  f -> f));
	}

	/**
	 * Get list of links
	 * 
	 * @param t
	 * @return list of websites
	 */
	private List<Link> getLinks(DaoRelated t) {
		Map<String, Link> links = store.getLinks();
		List<String> urls = t.getLinks();
		return urls.stream().map(u -> links.get(u)).filter(Objects::nonNull).collect(Collectors.toUnmodifiableList());		
	}

	private void page(String id, String lang) throws IOException {
		Page page = store.getPages().get(id);
		write("page",
			Map.of( "lang", lang, "path", "/page/" + id, "p", page),
			"/page/" + id + "." + lang + ".html");
	}

	private void integrators(String lang) throws IOException {
		Page page = store.getPages().get("integrators");
		List<Organization> integrators = sortByTitle(store.getIntegrators(), lang);
		write("integrators",
			Map.of( "lang", lang, "p", page, "path", "/page/integrators", "integrators", integrators),
			"/page/integrators." + lang + ".html");
	}

	private void legislations(String lang) throws IOException {
		Page page = store.getPages().get("legislations");
		List<Legislation> legislations = sortByDate(store.getLegislations());
		write("legislations",
			Map.of("lang", lang, "path", "/page/legislations", "p", page, "legislations", legislations),
			"/page/legislations." + lang + ".html");
	}

	private void legislation(String id, String lang) throws IOException {
		Legislation legislation = store.getLegislations().get(id);
		List<Link> links = getLinks(legislation);
		List<EifPrinciple> principles = sortBySeq(lookup(store.getPrinciples(), legislation.getPrinciples()));	
		List<EifRecommendation> recommendations = sortBySeq(lookup(store.getRecommendations(), 
																		legislation.getRecommendations()));
		write("legislation",
			Map.of("lang", lang, "path", "/page/legislation/" + id, "p", legislation, 
										"links", links,
										"principles", principles, "recommendations", recommendations),
			"/page/legislation/" + id + "." + lang + ".html");
	}

	private void activities(String lang) throws IOException {
		Page page = store.getPages().get("activities");
		List<Activity> activities = sortByTitle(store.getActivities(), lang);
		write("activitys",
			Map.of("lang", lang, "path", "/page/activities", "p", page, "activities", activities),
			"/page/activities." + lang + ".html");
	}

	private void activity(String id, String lang) throws IOException {
		Activity activity = store.getActivities().get(id);
		List<Link> links = getLinks(activity);
		List<EifPrinciple> principles = sortBySeq(lookup(store.getPrinciples(), activity.getPrinciples()));	
		List<EifRecommendation> recommendations = sortBySeq(lookup(store.getRecommendations(), 
																		activity.getRecommendations()));
		write("activity",
			Map.of("lang", lang, "path", "/page/activity/" + id, "p", activity, 
										"links", links,
										"principles", principles, "recommendations", recommendations),
			"/page/activity/" + id + "." + lang + ".html");
	}

	private void specification(String id, String lang) throws IOException {
		Specification specification = store.getSpecifications().get(id);
		write("specification",
			Map.of("lang", lang, "path", "/page/specification/" + id, "p", specification),
			"/page/specification/" + id + "." + lang + ".html");
	}

	private void specifications(String lang) throws IOException {
		Page page = store.getPages().get("specifications");
		List<Specification> specifications = sortByTitle(store.getSpecifications(), lang);
		write("specifications",
			Map.of("lang", lang, "path", "/page/specifications", "p", page, "specifications", specifications),
			"/page/specifications." + lang + ".html");
	}
	

	@Override
	public void generate(String lang) throws IOException {
		activities(lang);
		for(String key: store.getActivities().keySet()) {
			activity(key, lang);
		}
		integrators(lang);
		legislations(lang);
		for(String key: store.getLegislations().keySet()) {
			legislation(key, lang);
		}
		for(String key: store.getPages().keySet()) {
			page(key, lang);
		}
		specifications(lang);
		for(String key: store.getSpecifications().keySet()) {
			specification(key, lang);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param store 
	 */
	public PageGenerator(Store store) {
		this.store = store;
	}
}
