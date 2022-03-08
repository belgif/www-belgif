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

import java.util.Map;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.vocabulary.DCTERMS;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.OWL;

/**
 * Technical or semantic specification
 * 
 * @author Bart Hanssens
 */
public class Specification extends Dao {
	private final Map<String, String> description;
	private final Map<String, String> longdesc;
	private final Map<String, String> subject;
	private final String version;
	private final String website;

	/**
	 * Get full description in a specific language
	 * 
	 * @param lang language code
	 * @return 
	 */
	public String getDescription(String lang) {
		return description.get(lang);
	}

	/**
	 * Get short description in a specific language
	 * 
	 * @param lang language code
	 * @return 
	 */
	public String getAbstract(String lang) {
		return longdesc.get(lang);
	}

	/**
	 * Get type of specification in a specific language
	 * 
	 * @param lang language code
	 * @return 
	 */
	public String getSubject(String lang) {
		return subject.get(lang);
	}

	/**
	 * Get version of the specification
	 * 
	 * @return 
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Get website to the specification
	 *
	 * @return 
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * Constructor
	 * 
	 * @param m RDF model
	 * @param iri ID
	 */
	public Specification(Model m, IRI iri) {
		super(m, iri, DCTERMS.TITLE);

		description = langMap(m, iri, DCTERMS.DESCRIPTION);
		longdesc = langMap(m, iri, DCTERMS.ABSTRACT);
		subject = langMap(m, iri, DCTERMS.SUBJECT);
		version = firstString(m, iri, OWL.VERSIONINFO);
		website = firstString(m, iri, FOAF.PAGE);
	}
}
