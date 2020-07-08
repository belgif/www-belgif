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

import be.belgif.www.dao.EifPrinciple;
import be.belgif.www.dao.EifRecommendation;
import io.micronaut.context.annotation.Value;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import org.eclipse.rdf4j.model.IRI;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.impl.SimpleIRI;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.SKOS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bart.Hanssens
 */
@Singleton
public class Store implements AutoCloseable {
	private final Logger LOG = LoggerFactory.getLogger(Store.class);

	@Value("${be.belgif.www.data:.}")
	protected String dataPath;

	
	private final ValueFactory f = SimpleValueFactory.getInstance();
	private final IRI principle = f.createIRI("http://www.belgif.be/id/eif3/principle/");
	private final IRI recommendation = f.createIRI("http://www.belgif.be/id/eif3/recommendation/");

	private Map<String,EifPrinciple> principles = new HashMap<>();
	private Map<String,EifRecommendation> recommendations = new HashMap<>();

	public Map<String,EifPrinciple> getPrinciples() {
		return principles;
	}

	public Map<String,EifRecommendation> getRecommendations() {
		return recommendations;
	}

	@PostConstruct
	public void load() throws Exception {
		LOG.info("Loading data from directory {}", dataPath);

		Model m = new LinkedHashModel();
		List<Path> files = Files.list(Path.of(dataPath))
								.filter(f -> f.toFile().isFile())
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
		LOG.info("Statements: {}", m.size());

		principles = m.filter(null, SKOS.IN_SCHEME, principle).subjects().stream()
						.map(IRI.class::cast)
						.collect(Collectors.toMap(IRI::toString, s -> new EifPrinciple(m, s)));
		LOG.info("EIF principles: {}", principles.size());

		recommendations = m.filter(null, SKOS.IN_SCHEME, recommendation).subjects().stream()
						.map(IRI.class::cast)
						.collect(Collectors.toMap(IRI::toString, s -> new EifRecommendation(m, s)));

		LOG.info("EIF recommendations: {} ", recommendations.size());

	}

	@PreDestroy
	@Override
	public void close() throws Exception {

	}
}