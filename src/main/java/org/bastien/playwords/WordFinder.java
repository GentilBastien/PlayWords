package org.bastien.playwords;

import java.util.SortedSet;

public class WordFinder {
    private final SortedSet<String> words;

    public WordFinder(SortedSet<String> words) {
        this.words = words;
    }

    public WordFinder size(int size) {
        words.removeIf(word -> word.length() != size);
        return this;
    }

    public WordFinder hasCharacter(char c) {
        words.removeIf(word -> !word.contains(String.valueOf(c)));
        return this;
    }

    public WordFinder hasNoCharacter(char c) {
        words.removeIf(word -> word.contains(String.valueOf(c)));
        return this;
    }

    public WordFinder hasCharacterAt(char c, int index) {
        words.removeIf(word -> word.charAt(index) != c);
        return this;
    }

    public WordFinder startsWith(String c) {
        words.removeIf(word -> !word.startsWith(c));
        return this;
    }

    public WordFinder endsWith(String c) {
        words.removeIf(word -> !word.endsWith(c));
        return this;
    }

    public String print() {
        return words.toString();
    }
}
