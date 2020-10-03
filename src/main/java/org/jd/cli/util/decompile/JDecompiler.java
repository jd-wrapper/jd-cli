/*
 * Copyright (c) 2020 syany
 * Copyright (c) 2008, 2019 Emmanuel Dupuy.
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */
package org.jd.cli.util.decompile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.jd.cli.App;
import org.jd.cli.util.loader.DirectoryLoader;
import org.jd.cli.util.loader.ZipLoader;
import org.jd.cli.util.printer.LineNumberStringBuilderPrinter;
import org.jd.cli.util.printer.StringPrintable;
import org.jd.core.v1.ClassFileToJavaSourceDecompiler;
import org.jd.core.v1.api.loader.Loader;
import org.jd.core.v1.api.loader.LoaderException;

public class JDecompiler implements Decompiler {
	private final static String JAVA_CLASS_SUFFIX         = ".class";
	private final static int    JAVA_CLASS_SUFFIX_LENGTH = JAVA_CLASS_SUFFIX.length();
	private final static String JAVA_SOURCE_SUFFIX        = ".java";
	private final static int    JAVA_SOURCE_SUFFIX_LENGTH = JAVA_SOURCE_SUFFIX.length();

	private final static ClassFileToJavaSourceDecompiler DECOMPILER = new ClassFileToJavaSourceDecompiler();

	private StringPrintable printer;
	private Loader loader;
	private DecompilePrinter decompilePrinter = this;
	private DecompileLoader decompileLoader = this;

	private boolean showLocation = false;
	private boolean realignmentLineNumber = false;
	private boolean unicodeEscape = false;
	private boolean showLineNumbers = false;
	private boolean showMetaData = false;

	/**
	 * @param decompilePrinter
	 */
	public JDecompiler setDecompilePrinter(DecompilePrinter decompilePrinter) {
		this.decompilePrinter = decompilePrinter;
		return this;
	}

	/**
	 * @param decompileLoader
	 */
	public JDecompiler setDecompileLoader(DecompileLoader decompileLoader) {
		this.decompileLoader = decompileLoader;
		return this;
	}

	/**
	 * @param showLocation Set showLocation.
	 * @return JDecompiler instance.
	 */
	public final JDecompiler setShowLocation(boolean showLocation) {
		this.showLocation = showLocation;
		return this;
	}

	/**
	 * @param realignmentLineNumber Set realignmentLineNumber.
	 * @return JDecompiler instance.
	 */
	public final JDecompiler setRealignmentLineNumber(boolean realignmentLineNumber) {
		this.realignmentLineNumber = realignmentLineNumber;
		return this;
	}

	/**
	 * @param unicodeEscape Set unicodeEscape.
	 * @return JDecompiler instance.
	 */
	public final JDecompiler setUnicodeEscape(boolean unicodeEscape) {
		this.unicodeEscape = unicodeEscape;
		return this;
	}

	/**
	 * @param showLineNumbers Set showLineNumbers.
	 * @return JDecompiler instance.
	 */
	public final JDecompiler setShowLineNumbers(boolean showLineNumbers) {
		this.showLineNumbers = showLineNumbers;
		return this;
	}

	/**
	 * @param showMetaData Set showMetaData.
	 * @return JDecompiler instance.
	 */
	public final JDecompiler setShowMetaData(boolean showMetaData) {
		this.showMetaData = showMetaData;
		return this;
	}

	/**
	 * @param javaSourcePath internal name of the class.(with extension)
	 * @return Decompiled class text.
	 */
	public String findSource(String javaSourcePath) {
		return findSource(new File(".").getAbsoluteFile().getParentFile(), javaSourcePath);
	}

    /**
     * @param basePath          Path to the root of the classpath, either a
     *                          path to a directory or a path to a jar file.
     * @param javaSourcePath internal name of the class.(with extension)
     * @return Decompiled class text.
     */
	public String findSource(File basePath, String javaSourcePath) {
		String source = null;

		if ((source == null) && javaSourcePath.toLowerCase().endsWith(JAVA_SOURCE_SUFFIX)) {
			String internalTypeName = javaSourcePath.substring(0, javaSourcePath.length()-JAVA_SOURCE_SUFFIX_LENGTH);

			// Decompile class file
			try {
				source = decompile(basePath.getAbsolutePath(), internalTypeName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ((source == null) && javaSourcePath.toLowerCase().endsWith(JAVA_CLASS_SUFFIX)) {

			String internalTypeName = javaSourcePath.substring(0, javaSourcePath.length()-JAVA_CLASS_SUFFIX_LENGTH).replace('.', '/');

			// Decompile class file
			try {
				source = decompile(basePath.getAbsolutePath(), internalTypeName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String internalTypeName = javaSourcePath.replace('.', '/');

			// Decompile class file
			try {
				source = decompile(basePath.getAbsolutePath(), internalTypeName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return source;
	}

	@Override
	public Loader getLoader(final File base, final String basePath, final String internalTypeName) throws LoaderException {
		Loader res = null;
        if (base.isFile()) {
        	if (basePath.toLowerCase().endsWith(".jar") || basePath.toLowerCase().endsWith(".zip")) {
        		res = new ZipLoader(base);
        	} else {
        		throw new LoaderException("Unexpected container type file: " + basePath);
        	}
        } else {
        	res = new DirectoryLoader(base);
        }
		return res;
	}

	@Override
	public StringPrintable getPrinter(final File base, final String basePath, final String internalTypeName) {
		return new LineNumberStringBuilderPrinter();
	}

	protected void addMetaLocation(final StringBuilder stringBuffer, final File base,final String internalTypeName) {
        stringBuffer.append("\n * Location:              ");
        String classPath = internalTypeName + JAVA_CLASS_SUFFIX;
    	String location = null;
        if (this.showLocation) {
        	location = base.isFile() ? base.getPath() + "!/" + classPath : new File(base, classPath).getPath();
        } else {
        	location = base.isFile() ? "!/" + classPath : new File(classPath).getPath();
        }
        // Escape "\ u" sequence to prevent "Invalid unicode" errors
        stringBuffer.append(location.replaceAll("(^|[^\\\\])\\\\u([0-9a-fA-F]{4})", "$1\\\\\\\\u$2"));
	}

	protected void addMetaJavaVersion(final StringBuilder stringBuffer, final File base,final String internalTypeName) {
        int majorVersion = printer.getMajorVersion();
        if (majorVersion >= 45) {
            stringBuffer.append("\n * Java compiler version: ");

            if (majorVersion >= 49) {
                stringBuffer.append(majorVersion - (49 - 5));
            } else {
                stringBuffer.append(majorVersion - (45 - 1));
            }

            stringBuffer.append(" (");
            stringBuffer.append(majorVersion);
            stringBuffer.append('.');
            stringBuffer.append(printer.getMinorVersion());
            stringBuffer.append(')');
        }
	}

	protected void addMetaJDVersion(final StringBuilder stringBuffer, final File base,final String internalTypeName) {
        stringBuffer.append("\n * JD-Core Version:       ");
        stringBuffer.append(App.VERSION_JD_CORE);
	}


	protected void addMetadata(final StringBuilder stringBuffer, final File base,final String internalTypeName) {
        stringBuffer.append("\n\n/*");
        this.addMetaLocation(stringBuffer, base, internalTypeName);
        this.addMetaJavaVersion(stringBuffer, base, internalTypeName);
        this.addMetaJDVersion(stringBuffer, base, internalTypeName);
        stringBuffer.append("\n */");
	}
    /**
     * @param basePath          Path to the root of the classpath, either a
     *                          path to a directory or a path to a jar file.
     * @param internalClassName internal name of the class.(without extension)
     * @return Decompiled class text.
     */
	protected String decompile(String basePath, String internalTypeName) throws Exception {

        // Initialize loader
        File base = new File(basePath);

        if (this.loader == null) {
            this.loader = this.decompileLoader.getLoader(base, basePath, internalTypeName);
        }

        // Initialize printer
        if (this.printer == null) {
        	this.printer = this.decompilePrinter.getPrinter(base, basePath, internalTypeName);
        }
        printer.setRealignmentLineNumber(this.realignmentLineNumber);
        printer.setUnicodeEscape(this.unicodeEscape);
        printer.setShowLineNumbers(this.showLineNumbers);

        Map<String, Object> configuration = new HashMap<>();
        configuration.put("realignLineNumbers", this.realignmentLineNumber);

        try {
            // Decompile class file
            DECOMPILER.decompile(loader, printer, internalTypeName, configuration);
        } catch (NullPointerException nex) {
        	if (base.isFile()) {
        		throw new RuntimeException("The class file may not be the full path, (e.g. Specify org.bbb.ccc.AA (.class) instead of AA (.class)).", nex);
        	}
        	throw nex;
        }

        StringBuilder stringBuffer = printer.getStringBuffer();

        // Metadata
        if (this.showMetaData) {
            this.addMetadata(stringBuffer, base, internalTypeName);
        }

		return stringBuffer.toString();
	}
}
