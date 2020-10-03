/*
 * Copyright (c) 2020 syany
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */
package org.jd.cli.util.decompile;

import java.io.File;

import org.jd.cli.util.printer.StringPrintable;

public interface DecompilePrinter {
	public StringPrintable getPrinter(final File base, final String basePath, final String internalTypeName);
}
