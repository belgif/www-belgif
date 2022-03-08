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

import be.belgif.www.generators.DirectoryCopier;
import be.belgif.www.generators.EifGenerator;
import be.belgif.www.generators.HomeGenerator;
import be.belgif.www.generators.PageGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bart Hanssens
 */
public class Main {
	private final static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("Usage: belgif.jar data_dir output_dir");
			System.exit(-1);
		}

		Path indir = Paths.get(args[0]);
		if (!Files.exists(indir)) {
			LOG.error("Input dir {} does not exist", indir);
			System.exit(-2);
		}

		Path outdir = Paths.get(args[1]);

		if (!Files.exists(outdir)) {
			LOG.info("Creating directory {}", outdir);
			Files.createDirectories(outdir);
		}

		// Copy download files, css, etc
		DirectoryCopier copier = new DirectoryCopier();
		copier.copy(indir, outdir);

		Store store = new Store(indir);
		
		// Generate EIF levels, principles, ... based on data in the store
		EifGenerator eifGenerator = new EifGenerator(store, outdir);
		eifGenerator.generate();

		// Generate home page
		HomeGenerator homeGenerator = new HomeGenerator(store, outdir);
		homeGenerator.generate();

		// Generate other pages
		PageGenerator pageGenerator = new PageGenerator(store, outdir);
		pageGenerator.generate();
    }
}
