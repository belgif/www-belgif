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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copy a directory
 * 
 * @author Bart Hanssens
 */
public class DirectoryCopier {
	private final static Logger LOG = LoggerFactory.getLogger(DirectoryCopier.class);

	/**
	 * Copy all files in a subdirectory to a new subdirectory
	 * 
	 * @param rootsrc
	 * @param rootdest
	 * @param subdir
	 * @throws IOException 
	 */
	private void copySubdir(String rootsrc, String rootdest, String subdir) throws IOException {
		List<Path> sources;
		try(Stream<Path> files = Files.walk(Paths.get(rootsrc, subdir))) {
			sources = files.filter(p -> p.toFile().isFile()).toList();
		}

		for(Path src: sources) {
			Path dest = Paths.get(rootdest, src.toString().substring(rootsrc.length()));
			Files.createDirectories(dest.getParent());
			LOG.info("Copying file {} to {}", src, dest);
			Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	/**
	 * Recursively copy source directory to destination directory
	 * 
	 * @param srcdir source directory
	 * @param destdir destination directory
	 * @throws IOException 
	 */
	public void copy(Path srcdir, Path destdir) throws IOException {
		String from = srcdir.toString();
		String to = destdir.toString();
		
		for (String subdir: new String[] {"css", "js", "downloads", "images"}) {
			copySubdir(from, to, subdir);
		}
		
	}
}