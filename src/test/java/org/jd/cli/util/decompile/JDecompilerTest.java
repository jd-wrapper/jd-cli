/*
 * Copyright (c) 2020 syany.
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */
package org.jd.cli.util.decompile;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JDecompilerTest {

	private static final String CLASSS_DIR_PATH = "/build/classes/java/test";
//	private static final String CLASSS_DIR_PATH = "/bin/test";
	private static final String RES_DIR_PATH = "/build/resources/test";
	private static final String CLASS_NAME = ".class";
	private static final String CLASSPATH = "org.jd.cli.util.decompile.JDecompilerTest";
	private static final String JAR_CLASSPATH = "Test/Library";
	private static final String CLASSPATH_FULL = CLASSS_DIR_PATH + "/org.jd.cli.util.decompile.JDecompilerTest" + CLASS_NAME;

	private static final File DEFAULT_DIR = new File("." + CLASSS_DIR_PATH + "/.").getAbsoluteFile().getParentFile();
	private static final File TARGET_JAR = new File("." + RES_DIR_PATH + "/org/jd/cli/util/decompile/Test.jar").getAbsoluteFile();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * {@link org.jd.cli.util.decompile.JDecompiler#findSource(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testFindSourceString() {
		assertNotNull(new JDecompiler().findSource(CLASSPATH_FULL));
	}

	/**
	 * {@link org.jd.cli.util.decompile.JDecompiler#findSource(java.io.File, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testFindSourceFileString() {
		assertNotNull(new JDecompiler().findSource(DEFAULT_DIR, CLASSPATH + CLASS_NAME));
	}

	/**
	 * {@link org.jd.cli.util.decompile.JDecompiler#decompile(java.lang.String, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testDecompile() {
		String res = null;
		try {
			res = new JDecompiler().decompile(DEFAULT_DIR.getPath(), CLASSPATH.replace('.', '/'));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(res);
	}

	@Test
	public void testDecompileJar() {
		String res = null;
		try {
			res = new JDecompiler().decompile(TARGET_JAR.getPath(), JAR_CLASSPATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(res);
	}

}
