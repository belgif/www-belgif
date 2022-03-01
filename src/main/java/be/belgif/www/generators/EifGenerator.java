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
import be.belgif.www.dao.DaoEif;
import be.belgif.www.dao.EifLevel;
import be.belgif.www.dao.EifPrinciple;
import be.belgif.www.dao.EifRecommendation;
import be.belgif.www.dao.Legislation;
import be.belgif.www.dao.Page;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Bart.Hanssens
 */
public class EifGenerator extends Generator {
	private final Store store;

	/** 
	 * Order by sequence number
	 */
	private <T extends DaoEif> List<T> sortBySeq(Stream<T> stream) {
		return stream.sorted((a,b) -> a.getSequence() - b.getSequence())
												.collect(Collectors.toUnmodifiableList());
	}
	
	/** 
	 * Order by sequence number
	 */
	private <T extends DaoEif> List<T> sortBySeq(Map<String,T> map) {
		return map.values().stream().sorted((a,b) -> a.getSequence() - b.getSequence())
												.collect(Collectors.toUnmodifiableList());
	}

	private void eif(String lang) throws IOException {
		Page page = store.getPages().get("eif3");
		List<EifLevel> levels = sortBySeq(store.getLevels());
		List<EifPrinciple> principles = sortBySeq(store.getPrinciples());
		write("eif3",
			Map.of("lang", lang, "path", "/eif3/about", "p", page, "levels", levels, "principles", principles),
			"/eif3/about." + lang + ".html");
	}
/*
	private void levels(String lang) throws IOException {	
		List<EifLevel> list = sortBySeq(store.getLevels());
		Page page = store.getPages().get("eif3");
		write("levels",
			Map.of("lang", lang, "path", "/eif3/levels", "levels", list, "p", page),
			"/eif3/levels." + lang + ".html");
	}
*/
	private void level(String id, String lang) throws IOException {
		EifLevel eif = store.getLevels().get(id);
		List<EifRecommendation> list = sortBySeq(eif.getRecommendations().stream()
											.map(s -> store.getRecommendations().get(s)));
		write("level",
			Map.of("lang", lang, "path", "/eif3/level/" + id, "p", eif, "recommendations", list),
			"/eif3/level/" + id + "." + lang + ".html");
	}

	private void principles(String lang) throws IOException {	
		List<EifPrinciple> list = sortBySeq(store.getPrinciples());
		Page page = store.getPages().get("eif3");
		write("principles",
			Map.of("lang", lang, "path", "/eif3/principles", "principles", list, "p", page),
			"/eif3/principles." + lang + ".html");
	}


	private void principle(String id, String lang) throws IOException {
		EifPrinciple eif = store.getPrinciples().get(id);
		List<EifRecommendation> list = sortBySeq(eif.getRecommendations().stream()
											.map(s -> store.getRecommendations().get(s)));
		write("principle",
			Map.of("lang", lang, "path", "/eif3/principle/" + id, "p", eif, "recommendations", list),
			"/eif3/principle/" + id + "." + lang + ".html");
	}


	private void recommendation(String id, String lang) throws IOException {
		EifRecommendation eif = store.getRecommendations().get(id);
		List<Legislation> legislations = store.getLegislations().values().stream()
											.filter(l -> l.getRecommendations().contains(id))
											.collect(Collectors.toUnmodifiableList());
		List<Activity> activities = store.getActivities().values().stream()
											.filter(l -> l.getRecommendations().contains(id))
											.collect(Collectors.toUnmodifiableList());
		write("recommendation", 
			Map.of("lang", lang, "path", "/eif3/recommendation/" + id, "p", eif, 
								"legislations", legislations, "activities", activities),
			"/eif3/recommendation/" + id + "." + lang + ".html");
	}

	private void recommendations(String lang) throws IOException {
		List<EifRecommendation> list = sortBySeq(store.getRecommendations());
		Page page = store.getPages().get("recommendations");
		write("recommendations", 
			Map.of("lang", lang, "path", "/eif3/recommendations", "recommendations", list, "page", page),
			"/eif3/recommendations." + lang + ".html");
	}
	
	@Override
	public void generate(String lang) throws IOException {
		eif(lang);

	//	levels(lang);
		for(String key: store.getLevels().keySet()) {
			level(key, lang);
		}
		principles(lang);
		for(String key: store.getPrinciples().keySet()) {
			principle(key, lang);
		}
		recommendations(lang);
		for(String key: store.getRecommendations().keySet()) {
			recommendation(key, lang);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param store 
	 */
	public EifGenerator(Store store) {
		this.store = store;
	}
}
