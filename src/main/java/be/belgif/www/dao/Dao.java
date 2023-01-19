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
package be.belgif.www.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.vocabulary.DCTERMS;
import org.eclipse.rdf4j.model.vocabulary.SKOS;


/**
 * Global class
 * 
 * @author Bart Hanssens
 */
public class Dao {
	private final String id;
	private final String localId;
	private final Map<String, String> title;

	/**
	 * Get ID for this document / page
	 * 
	 * @return 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get local ID (without namespace / domain name)
	 * 
	 * @return 
	 */
	public String getLocalId() {
		return localId;
	}

	/**
	 * Get title for a document / page.
	 * This can either be the language specific title or (if missing) the language neutral title.
	 * 
	 * @param lang language code
	 * @return string or null
	 */
	public String getTitle(String lang) {
		return title.getOrDefault(lang, title.get(""));
	}

	/**
	 * Get a language-keyed map for literal values for a specific predicate
	 * 
	 * @param m
	 * @param iri
	 * @param predicate
	 * @return 
	 */
	protected static Map<String,String> langMap(Model m, IRI iri, IRI predicate) {
		return m.filter(iri, predicate, null).objects().stream()
				.map(Literal.class::cast)
				.collect(Collectors.toMap(l -> l.getLanguage().orElse(""), Literal::stringValue));
	}
	/**
	 * Get a language-keyed map for literal values for a specific predicate
	 * 
	 * @param m
	 * @param iri
	 * @param predicate
	 * @return 
	 */
	protected static Map<String,String> langMapIRI(Model m, IRI iri, IRI predicate) {
		return m.filter(iri, predicate, null).objects().stream()
				.map(IRI.class::cast)
				.map(u -> {
					Map<String,String> pages = m.filter(u, DCTERMS.LANGUAGE, null)
						.stream()
						.collect(
							Collectors.toMap(s -> s.getObject().stringValue(), s -> s.getSubject().stringValue()));
					if (pages == null || pages.isEmpty()) {
						pages = Map.of("", u.stringValue());
					}
					return pages;
				})
				.flatMap(map -> map.entrySet().stream())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	/**
	 * Get first property value for a specific subject as a string
	 * 
	 * @param m full RDF model
	 * @param iri subject IRI
	 * @param predicate property
	 * @return string value or empty string
	 */
	protected static String firstString(Model m, IRI iri, IRI predicate) {
		return m.filter(iri, predicate, null).objects().stream().findFirst()
					.map(Value.class::cast)
					.map(Value::stringValue)
					.orElse("");
	}
	
	/**
	 * Get a property values for a specific subject as a list of strings
	 * 
	 * @param m full RDF model
	 * @param iri subject IRI
	 * @param predicate property
	 * @param local local name or full IRI
	 * @return string value or empty string
	 */
	protected static List<String> listString(Model m, IRI iri, IRI predicate, boolean local) {
		return m.filter(iri, predicate, null).objects().stream()
							.map(IRI.class::cast)
							.map(i -> local ? i.getLocalName() : i.stringValue())
							.collect(Collectors.toList());
	}

	/**
	 * Constructor
	 * 
	 * @param m RDF model
	 * @param iri ID
	 * @param prop property
	 */
	public Dao(Model m, IRI iri, IRI prop) {
		id = iri.toString();
		localId = iri.getLocalName();
		title = langMap(m, iri, prop);
	}

	/**
	 * Constructor
	 * 
	 * @param m RDF model
	 * @param iri ID
	 */
	public Dao(Model m, IRI iri) {
		this(m, iri, SKOS.PREF_LABEL);
	}
}
