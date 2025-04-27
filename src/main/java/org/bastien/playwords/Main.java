package org.bastien.playwords;

import org.bastien.playwords.dictionaries.Dictionary;
import org.bastien.playwords.dictionaries.FrenchDictionary;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        Dictionary.load(FrenchDictionary.class);
        WordFinder finder = Dictionary.use().wordFinder();
        String res = finder.size(8)
                           .startsWith("déc")
                           .endsWith("e")
                           .hasCharacter('c')
                           .hasCharacter('a')
                           .hasNoCharacter('i')
                           .hasNoCharacter('n')
                           .hasNoCharacter('u')
                           .hasNoCharacter('l')
                           .hasNoCharacter('p')
                           .hasNoCharacter('o')
                           .hasNoCharacter('t')
                           .print();
        System.out.println(res);
    }
}