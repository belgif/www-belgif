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
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bart Hanssens
 */
public abstract class Generator {
	protected final static Logger LOG = LoggerFactory.getLogger(Generator.class);
		
	protected final Store store;
	protected final String outdir;

	private static final Configuration cfg;
	
	static {
		cfg = new Configuration(Configuration.getVersion());
        cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateLoader(new ClassTemplateLoader(be.belgif.www.Main.class, "/templates"));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
	}

	public abstract void generate(String lang) throws IOException;

	/**
	 * Generate static HTML into output directory
	 * 
	 * @throws IOException 
	 */
	public void generate() throws IOException {
		for (String lang: new String[] {"nl","fr","de","en"}) {
			generate(lang);
		}
	}

	/**
	 * Write the filled-out template to an output file
	 * 
	 * @param ftl name of the template
	 * @param map map with data
	 * @param out name of output file
	 * @throws IOException 
	 */
	protected void write(String ftl, Map<String,Object> map, String out) throws IOException {
		Template template = cfg.getTemplate(ftl + ".ftl");
		
		Path p = Paths.get(outdir, out);
		Files.createDirectories(p.getParent());
		
		try (FileOutputStream fout = new FileOutputStream(p.toFile());
			Writer w = new OutputStreamWriter(fout, StandardCharsets.UTF_8)) {
			LOG.info("Writing to {}", p);
			template.process(map, w);
		} catch (TemplateException te) {
			throw new IOException(te);
		}
	}
	
	/**
	 * Constructor
	 * 
	 * @param store
	 * @param outdir 
	 */
	public Generator(Store store, Path outdir) {
		this.store = store;
		this.outdir = outdir.toString();
	}
}
