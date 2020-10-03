/*
 * Copyright (c) 2020 syany
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */
package org.jd.cli;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

public class Parameter {
    /** ヘルプの表示 */
    @Option(help = true, name = "-h", aliases = "--help", usage = "Show helps.")
    private boolean showHelp;

    /** クラスのフルパスの表示 */
    @Option(help = true, name = "-l", aliases = "--loc", usage = "Show full classpath.")
    private boolean showLocation;

    /** 行番号を再調整します */
    @Option(help = true, name = "-r", aliases = "--real", usage = "Realignment Line Number.")
    private boolean realignmentLineNumber;

    /** Unicode文字をエスケープします */
    @Option(help = true, name = "-u", aliases = "--unicode", usage = "Escapes Unicode Charset.")
    private boolean unicodeEscape;

    /** 行番号を表示します */
    @Option(help = true, name = "-n", aliases = "--line", usage = "Show Line Number.")
    private boolean showLineNumbers;

    /** メタデータを表示します */
    @Option(help = true, name = "-m", aliases = "--meta", usage = "Show metadatas.(e.g. Filename, Java versions..)")
    private boolean showMetaData;

    /** 基準ディレクトリもしくはJarファイルを指定します */
    @Option(help = true, name = "-b", aliases = "--base", usage = "Specify the reference directory or Jar file.")
    private String basePath;

    // フィールドが配列なので1...n番目の引数が入る
    @Argument(index = 0, metaVar = "classes...", required = true, usage = "Specify the class files...")
    private static String[] classes;
	/**
	 * @return showHelp
	 */
	public synchronized final boolean isShowHelp() {
		return showHelp;
	}

	/**
	 * @param showHelp セットする showHelp
	 */
	public synchronized final void setShowHelp(boolean showHelp) {
		this.showHelp = showHelp;
	}

	/**
	 * @return showLocation
	 */
	public synchronized final boolean isShowLocation() {
		return showLocation;
	}

	/**
	 * @param showLocation セットする showLocation
	 */
	public synchronized final void setShowLocation(boolean showLocation) {
		this.showLocation = showLocation;
	}

	/**
	 * @return realignmentLineNumber
	 */
	public synchronized final boolean isRealignmentLineNumber() {
		return realignmentLineNumber;
	}

	/**
	 * @param realignmentLineNumber セットする realignmentLineNumber
	 */
	public synchronized final void setRealignmentLineNumber(boolean realignmentLineNumber) {
		this.realignmentLineNumber = realignmentLineNumber;
	}

	/**
	 * @return unicodeEscape
	 */
	public synchronized final boolean isUnicodeEscape() {
		return unicodeEscape;
	}

	/**
	 * @param unicodeEscape セットする unicodeEscape
	 */
	public synchronized final void setUnicodeEscape(boolean unicodeEscape) {
		this.unicodeEscape = unicodeEscape;
	}

	/**
	 * @return showLineNumbers
	 */
	public synchronized final boolean isShowLineNumbers() {
		return showLineNumbers;
	}

	/**
	 * @param showLineNumbers セットする showLineNumbers
	 */
	public synchronized final void setShowLineNumbers(boolean showLineNumbers) {
		this.showLineNumbers = showLineNumbers;
	}

	/**
	 * @return showMetaData
	 */
	public synchronized final boolean isShowMetaData() {
		return showMetaData;
	}

	/**
	 * @param showMetaData セットする showMetaData
	 */
	public synchronized final void setShowMetaData(boolean showMetaData) {
		this.showMetaData = showMetaData;
	}

	/**
	 * @return basePath
	 */
	public synchronized final String getBasePath() {
		return basePath;
	}

	/**
	 * @param basePath セットする basePath
	 */
	public synchronized final void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * @return classes
	 */
	public synchronized final String[] getClasses() {
		return classes;
	}

	/**
	 * @param classes セットする classes
	 */
	public synchronized final void setClasses(String[] classes) {
		Parameter.classes = classes;
	}
}
