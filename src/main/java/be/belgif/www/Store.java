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
package be.belgif.www;

import be.belgif.www.dao.Activity;
import be.belgif.www.dao.EifLevel;
import be.belgif.www.dao.EifPrinciple;
import be.belgif.www.dao.EifRecommendation;
import be.belgif.www.dao.Legislation;
import be.belgif.www.dao.Organization;
import be.belgif.www.dao.Page;
import be.belgif.www.dao.Specification;

import io.micronaut.context.annotation.Value;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.ORG;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.SKOS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple data store, based upon reading a series of RDF data files
 * 
 * @author Bart.Hanssens
 */
@Singleton
public class Store implements AutoCloseable {
	private final Logger LOG = LoggerFactory.getLogger(Store.class);

	@Value("${be.belgif.www.data:.}")
	protected String dataPath;
	
	/** Various types of data */
	private Map<String,EifLevel> levels = new HashMap<>();
	private Map<String,EifPrinciple> principles = new HashMap<>();
	private Map<String,EifRecommendation> recommendations = new HashMap<>();
	private Map<String,Page> pages = new HashMap<>();
	private Map<String,Organization> integrators = new HashMap<>();
	private Map<String,Specification> specifications = new HashMap<>();
	private Map<String,Legislation> legislations = new HashMap<>();
	private Map<String,Activity> activities = new HashMap<>();
	
	public Map<String,EifLevel> getLevels() {
		return levels;
	}

	public Map<String,EifPrinciple> getPrinciples() {
		return principles;
	}

	public Map<String,EifRecommendation> getRecommendations() {
		return recommendations;
	}

	public Map<String,Page> getPages() {
		return pages;
	}

	public Map<String,Organization> getIntegrators() {
		return integrators;
	}

	public Map<String,Specification> getSpecifications() {
		return specifications;
	}

	public Map<String,Legislation> getLegislations() {
		return legislations;
	}

	public Map<String,Activity> getActivities() {
		return activities;
	}

	private <T> Map<String,T> filter(String name, Model m, IRI predicate, IRI object, Function<IRI,T> func) {
		Map<String,T> map = m.filter(null, predicate, object).subjects().stream()
							.map(IRI.class::cast)
							.collect(Collectors.toMap(IRI::getLocalName, func));
		LOG.info("{} : {}", name, integrators.size());
		return map;
	}

	/**
	 * Extract various data types / objects from the RDF model
	 * 
	 * @param m model containing all data
	 */
	private void processAll(Model m) {
		integrators = filter("Integrators", m, RDF.TYPE, ORG.ORGANIZATION, s -> new Organization(m, s));
		levels = filter("EIF levels", m, SKOS.IN_SCHEME, StoreHelper.level, s -> new EifLevel(m, s));
		principles = filter("EIF principles", m, SKOS.IN_SCHEME, StoreHelper.principle, s -> new EifPrinciple(m, s));
		recommendations = filter("EIF recommendations", m, SKOS.IN_SCHEME, StoreHelper.recommendation, s -> new EifRecommendation(m, s));
		pages = filter("Pages", m, RDF.TYPE, FOAF.DOCUMENT, s -> new Page(m, s));
		specifications = filter("Specifications", m, RDF.TYPE, StoreHelper.specification, s -> new Specification(m, s));
		legislations = filter("Legislations", m, RDF.TYPE, StoreHelper.legislation, s -> new Legislation(m, s));
		activities = filter("Activities", m, RDF.TYPE, FOAF.PROJECT, s -> new Activity(m, s));
	}
	
	@PostConstruct
	public void load() throws Exception {
		LOG.info("Loading data from directory {}", dataPath);

		Model m = new LinkedHashModel();
		
		/** Load all files from the data directory into a memory model */
		List<Path> files = Files.list(Path.of(dataPath))
								.filter(p -> p.toFile().isFile())
								.collect(Collectors.toList());
		for (Path p: files ) {
			LOG.info("Loading data from {}", p);
			
			try(Reader r = Files.newBufferedReader(p)) {
				RDFFormat fmt = Rio.getParserFormatForFileName(p.toString()).orElseThrow(IOException::new);
				m.addAll(Rio.parse(r, "", fmt));
			} catch (IOException ioe) {
				LOG.error("Could not parse / load data", ioe.getMessage());
			}
		}
		processAll(m);
	}

	@PreDestroy
	@Override
	public void close() throws Exception {

	}
}
