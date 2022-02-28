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
import be.belgif.www.dao.Page;
import java.io.IOException;

import java.util.Map;

/**
 * Language page and homepage controller
 * 
 * @author Bart Hanssens
 */
public class HomeGenerator extends Generator {
	private final Store store;

	/**
	 * Homepage
	 * 
	 * @param lang language code
	 * @throws IOException 
	 */
	private void index(String lang) throws IOException {
		Page eif = store.getPages().get("home-eif");
		Page activities = store.getPages().get("home-activities");
		Page legal = store.getPages().get("home-legal");
		Page specs = store.getPages().get("home-specs");

		write("home", Map.of("lang", lang, "path", "/index", 
			"eif", eif, "activities", activities, "legal", legal, "specs", specs),
			"/index." + lang + ".html");
	}

	@Override
	public void generate(String lang) throws IOException {
		index(lang);
	}

	/**
	 * Constructor
	 * 
	 * @param store 
	 */
	public HomeGenerator(Store store) {
		this.store = store;
	}
}
