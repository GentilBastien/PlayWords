package org.bastien.playwords;

import java.util.SortedSet;

public class Language {
    private final SortedSet<String> words;
    private final WordFinder wordFinder;

    public Language(SortedSet<String> words) {
        this.words = words;
        this.wordFinder = new WordFinder(words);
    }

    public WordFinder wordFinder() {
        return wordFinder;
    }
}
