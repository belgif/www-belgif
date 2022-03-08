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
package be.belgif.www;

import be.belgif.www.dao.Activity;
import be.belgif.www.dao.Dao;
import be.belgif.www.dao.EifLevel;
import be.belgif.www.dao.EifPrinciple;
import be.belgif.www.dao.EifRecommendation;
import be.belgif.www.dao.Legislation;
import be.belgif.www.dao.Link;
import be.belgif.www.dao.Organization;
import be.belgif.www.dao.Page;
import be.belgif.www.dao.Specification;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.DCTERMS;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.ORG;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.SKOS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple data store, populated by reading a series of RDF data files
 * 
 * @author Bart Hanssens
 */
public class Store  {
	private final Logger LOG = LoggerFactory.getLogger(Store.class);

	/** Various types of pages */
	private Map<String,EifLevel> levels = new HashMap<>();
	private Map<String,EifPrinciple> principles = new HashMap<>();
	private Map<String,EifRecommendation> recommendations = new HashMap<>();
	private Map<String,Link> links = new HashMap<>();
	private Map<String,Page> pages = new HashMap<>();
	private Map<String,Organization> integrators = new HashMap<>();
	private Map<String,Specification> specifications = new HashMap<>();
	private Map<String,Legislation> legislations = new HashMap<>();
	private Map<String,Activity> activities = new HashMap<>();

	/**
	 * Get EIF Levels as a map
	 * @return 
	 */
	public Map<String,EifLevel> getLevels() {
		return levels;
	}

	/**
	 * Get EIF Principles as a map
	 * 
	 * @return 
	 */
	public Map<String,EifPrinciple> getPrinciples() {
		return principles;
	}

	/**
	 * Get EIF Recommendations as a map
	 * 
	 * @return 
	 */
	public Map<String,EifRecommendation> getRecommendations() {
		return recommendations;
	}

	/**
	 * Get links as a map
	 * 
	 * @return 
	 */
	public Map<String,Link> getLinks() {
		return links;
	}

	/**
	 * Get static pages as a map
	 * 
	 * @return 
	 */
	public Map<String,Page> getPages() {
		return pages;
	}

	/**
	 * Get service integrators as a map
	 * 
	 * @return 
	 */
	public Map<String,Organization> getIntegrators() {
		return integrators;
	}

	/**
	 * Get specifications as a map
	 * 
	 * @return 
	 */
	public Map<String,Specification> getSpecifications() {
		return specifications;
	}

	/**
	 * Get legislation as a map
	 * 
	 * @return 
	 */
	public Map<String,Legislation> getLegislations() {
		return legislations;
	}

	/**
	 * Get activities as a map
	 * 
	 * @return 
	 */
	public Map<String,Activity> getActivities() {
		return activities;
	}

	/**
	 * Turn model in a map of specific data types
	 * 
	 * @param <T>
	 * @param name (display) name of the type
	 * @param m full RDF model
	 * @param predicate predicate to be used for filtering
	 * @param object object to be used for filtering
	 * @param local use local name as key or full IRI
	 * @param func mapping function
	 * @return map
	 */
	private <T extends Dao> Map<String,T> filter(String name, Model m, IRI predicate, IRI object, boolean local, Function<IRI,T> func) {
		Map<String,T> map = m.filter(null, predicate, object).subjects().stream()
							.map(IRI.class::cast)
							.collect(Collectors.toMap(local ? IRI::getLocalName : IRI::stringValue, func));
		LOG.info("{} : {}", name, map.size());
		return map;
	}

	/**
	 * Extract various data types / objects from the RDF model
	 * 
	 * @param m model containing all data
	 */
	private void processAll(Model m) {
		integrators = filter("Integrators", m, RDF.TYPE, ORG.ORGANIZATION, true, s -> new Organization(m,s));
		levels = filter("EIF levels", m, SKOS.IN_SCHEME, StoreHelper.level, true,  s -> new EifLevel(m,s));
		principles = filter("EIF principles", m, SKOS.IN_SCHEME, StoreHelper.principle, true, s -> new EifPrinciple(m,s));
		recommendations = filter("EIF recommendations", m, SKOS.IN_SCHEME, StoreHelper.recommendation, true,  s -> new EifRecommendation(m,s));
		pages = filter("Pages", m, RDF.TYPE, Values.iri("http://purl.org/dc/dcmitype/Text"), true, s -> new Page(m,s));
		specifications = filter("Specifications", m, RDF.TYPE, StoreHelper.specification, true, s -> new Specification(m,s));
		legislations = filter("Legislations", m, RDF.TYPE, StoreHelper.legislation, true, s -> new Legislation(m,s));
		activities = filter("Activities", m, RDF.TYPE, FOAF.PROJECT, true, s -> new Activity(m,s));
		links = filter("Links", m, RDF.TYPE, FOAF.DOCUMENT, false, s -> new Link(m,s));
	}
	
	/**
	 * Fix foaf:page without class and title
	 * 
	 * @param m 
	 */
	private void fixFoafPage(Model m) {
		m.filter(null, FOAF.PAGE, null).objects().forEach(o -> {
			
			IRI iri = (IRI) o;
			m.add(iri, RDF.TYPE, FOAF.DOCUMENT);
			if (m.filter(iri, DCTERMS.TITLE, null).objects().isEmpty()) {
				m.add(iri, DCTERMS.TITLE, Values.literal(o.stringValue()));
			}
		});
	}

	/**
	 * Constructor
	 * 
	 * @param indir input directory
	 * @throws IOException 
	 */
	public Store(Path indir) throws IOException {
		LOG.info("Loading data from directory {}", indir);

		Model m = new LinkedHashModel();
		
		List<Path> files;
	
		// Load all files from the data directory into a memory model
		try (Stream<Path> s = Files.list(Path.of(indir.toString(), "belgif"))) {
			files = s.filter(p -> p.toFile().isFile()).collect(Collectors.toList());
		}
	
		for (Path p: files ) {
			LOG.info("Loading data from {}", p);
			
			try(Reader r = Files.newBufferedReader(p)) {
				RDFFormat fmt = Rio.getParserFormatForFileName(p.toString()).orElseThrow(IOException::new);
				m.addAll(Rio.parse(r, "", fmt));
			} catch (IOException ioe) {
				LOG.error("Could not parse / load data", ioe.getMessage());
			}
		}
		fixFoafPage(m);
		processAll(m);
	}
}
