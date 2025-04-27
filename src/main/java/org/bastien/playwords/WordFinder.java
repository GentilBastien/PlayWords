package org.bastien.playwords;

import java.util.SortedSet;
import java.util.stream.IntStream;

public class WordFinder {
    private static final char WILDCARD_CHAR = '*';
    private final SortedSet<String> words;

    public WordFinder(SortedSet<String> words) {
        this.words = words;
    }

    @SuppressWarnings("unused")
    public WordFinder size(int size) {
        words.removeIf(word -> word.length() != size);
        return this;
    }

    @SuppressWarnings("unused")
    public WordFinder hasCharacter(char c) {
        words.removeIf(word -> !word.contains(String.valueOf(c)));
        return this;
    }

    @SuppressWarnings("unused")
    public WordFinder hasNoCharacter(char c) {
        words.removeIf(word -> word.contains(String.valueOf(c)));
        return this;
    }

    @SuppressWarnings("unused")
    public WordFinder hasCharacterAt(char c, int index) {
        words.removeIf(word -> word.length() - 1 < index && word.charAt(index) != c);
        return this;
    }

    @SuppressWarnings("unused")
    public WordFinder startsWith(String c) {
        words.removeIf(word -> !word.startsWith(c));
        return this;
    }

    @SuppressWarnings("unused")
    public WordFinder endsWith(String c) {
        words.removeIf(word -> !word.endsWith(c));
        return this;
    }

    @SuppressWarnings("unused")
    public WordFinder matchPattern(String pattern) {
        words.removeIf(word -> word.length() != pattern.length() || IntStream.range(0, pattern.length())
                                                                             .anyMatch(i -> pattern.charAt(
                                                                                     i) != WILDCARD_CHAR && word.charAt(
                                                                                     i) != pattern.charAt(i)));
        return this;
    }

    @SuppressWarnings("unused")
    public String print() {
        if (words.isEmpty()) {
            return Main.ANSI_RED + "No result found!" + Main.ANSI_RESET;
        } else {
            int size = words.size();
            boolean plural = size > 1;
            String word = plural ? "words" : "word";
            String success = Main.ANSI_GREEN + size + " " + word + " found!" + Main.ANSI_RESET;
            return success + "\n" + words;
        }
    }
}
