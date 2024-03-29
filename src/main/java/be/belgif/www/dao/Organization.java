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

/**
 *
 * @author Bart.Hanssens
 */
public class Organization extends Dao {
	private final Map<String, String> description;
	private final String logo;
	private final Map<String, String>  website;

	/**
	 * Get description in a specific language
	 * 
	 * @param lang language code
	 * @return 
	 */
	public String getDescription(String lang) {
		return description.get(lang);
	}

	/**
	 * Get filename of the website logo
	 * 
	 * @return 
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Get language-specific URL of the website
	 * 
	 * @return 
	 */
	public String getWebsite(String lang) {
		return website.getOrDefault(lang, website.get(""));
	}

	/**
	 * Constructor
	 * 
	 * @param m RDF model
	 * @param iri ID
	 */
	public Organization(Model m, IRI iri) {
		super(m, iri);

		description = langMap(m, iri, DCTERMS.DESCRIPTION);
		website = langMapIRI(m, iri, FOAF.PAGE);

		logo = m.filter(iri, FOAF.DEPICTION, null).objects().stream().findFirst()
			.map(IRI.class::cast)
			.map(IRI::stringValue)
			.map(s -> s.replaceFirst("file:/", ""))
			.orElse("");
	}
}
