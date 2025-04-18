package org.bastien.playwords.dictionaries;

public class BlankLineException extends Exception {
    public BlankLineException(int lineNumber) {
        super("Blank line found at " + lineNumber);
    }
}
