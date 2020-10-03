/*
 * Copyright (c) 2020 syany
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */
package org.jd.cli;

import java.io.File;

import org.jd.cli.util.decompile.JDecompiler;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionHandlerFilter;

public class App {
	// The plug-in IDs
	public  static final String PLUGIN_ID = "jd.ide.cli";
	// Versions
	public static final String VERSION_JD_CORE    = "1.1.3";

	private static void showHelp(CmdLineParser parser) {
        // 標準出力
        System.out.println("example:");
        System.out.print("jd-cli[.exe] Example.class");
        System.out.print("jd-cli[.exe] -b example.jar org.aaa.Example.class");
        System.out.print("jd-cli[.exe] -b org\\aaa Example.class");
        System.out.println(parser.printExample(OptionHandlerFilter.REQUIRED));
        System.out.println();
        System.out.println("usage:");
        (new CmdLineParser(new Parameter())).printUsage(System.out);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Parameter parameter = new Parameter();

        CmdLineParser parser = new CmdLineParser(parameter);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            // 標準出力にはArgs4jのメッセージを出力
            System.out.println(e.getMessage());
            showHelp(parser);
            return;
        }

        // -hがあったらヘルプを表示して終了
        if (parameter.isShowHelp()) {
            showHelp(parser);
            return;
        }

		new App().showDecomple(parameter);
	}

	protected void showDecomple(final Parameter parameter) {

		JDecompiler jd = new JDecompiler()
				.setRealignmentLineNumber(parameter.isRealignmentLineNumber())
				.setShowLineNumbers(parameter.isShowLineNumbers())
				.setShowLocation(parameter.isShowLocation())
				.setShowMetaData(parameter.isShowMetaData())
				.setUnicodeEscape(parameter.isUnicodeEscape());

		final String basePath = parameter.getBasePath();

		if (basePath != null && basePath.length() > 0) {
			final File basePathFile = new File(basePath);
			for (final String jpath : parameter.getClasses()) {
				System.out.println(jd.findSource(basePathFile, jpath));
			}
		} else {
			for (final String jpath : parameter.getClasses()) {
				System.out.println(jd.findSource(jpath));
			}
		}
	}
}
