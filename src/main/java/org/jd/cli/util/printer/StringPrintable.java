/*
 * Copyright (c) 2020 syany
 * This project is distributed under the GPLv3 license.
 * This is a Copyleft license that gives the user the right to use,
 * copy and modify the code freely for non-commercial purposes.
 */
package org.jd.cli.util.printer;

import org.jd.core.v1.api.printer.Printer;

public interface StringPrintable extends Printer {
    public void setRealignmentLineNumber(boolean isRealignmentLineNumber);
    public void setUnicodeEscape(boolean isUnicodeEscape);
    public void setShowLineNumbers(boolean isShowLineNumbers);
    public int getMajorVersion();
    public int getMinorVersion();
    public StringBuilder getStringBuffer();
}
