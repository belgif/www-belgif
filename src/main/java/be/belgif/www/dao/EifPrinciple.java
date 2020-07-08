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
package be.belgif.www.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.eclipse.rdf4j.model.IRI;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.vocabulary.SKOS;

/**
 *
 * @author Bart.Hanssens
 */
public class EifPrinciple extends Dao {
	private final int seq;
	private final Map<String, String> title;
	private final List<String> recommendations;

	public int getSequence() {
		return seq;
	}

	public List<String> getRecommendations() {
		return recommendations;
	}

	public String getTitle(String lang) {
		return title.getOrDefault(lang, "");
	}

	public EifPrinciple(Model m, IRI iri) {
		super(m, iri);

		seq = m.filter(iri, SKOS.NOTATION, null).objects().stream().findFirst()
			.map(Literal.class::cast)
			.map(Literal::intValue).orElse(0);

		title = langMap(m, iri, SKOS.PREF_LABEL);

		recommendations = m.filter(iri, SKOS.RELATED, null).objects().stream()
			.map(IRI.class::cast)
			.map(i -> i.getLocalName())
			.collect(Collectors.toList());
	}
}
