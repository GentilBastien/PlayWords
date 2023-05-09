package org.bastien.playwords;

import org.bastien.playwords.dictionaries.AbstractDictionary;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Language {

    private static AbstractDictionary dictionary;

    public static SortedSet<String> printDictionary() {
        return findWordsWith(w -> true);
    }

    public static SortedSet<String> findAnagrams(String word) {
        return findWordsWith(w -> StringUtils.containsALlCharactersNormalizedOf(word, w));
    }

    public static SortedSet<String> findExactAnagrams(String word) {
        final char[] standardizedWord = StringUtils.sortToCharArrayNormalized(word);
        return findWordsWith(w -> Arrays.equals(standardizedWord, StringUtils.sortToCharArrayNormalized(w)));
    }

    public static SortedSet<String> findAllPalindromes() {
        return findWordsWith(StringUtils::isSymmetricalNormalizedString);
    }

    public static SortedSet<String> findLargestWords() {
        ensureDictionaryLoaded();
        SortedSet<String> largestWords = new TreeSet<>();
        long maxLength = 0;
        for (String w : dictionary.getWords()) {
            if (w.length() > maxLength) {
                maxLength = w.length();
                largestWords.clear();
                largestWords.add(w);
            } else if (w.length() == maxLength) {
                largestWords.add(w);
            }
        }
        System.out.println(largestWords);
        return largestWords;
    }

    public static SortedSet<String> findAllAnadromes() {
        Map<String, SortedSet<String>> wordVariants = new TreeMap<>();

        int i = 0;
        int size = dictionary.getWords().size();
        for (String w : dictionary.getWords()) {
            wordVariants.computeIfAbsent(w, (k) -> findExactAnagrams(w));
        }
        System.out.println("done");

        SortedSet<String> anadromes = new TreeSet<>();
        System.out.println(anadromes);
        return anadromes;
    }

    public static SortedSet<String> findWordsWith(Predicate<? super String> predicate) {
        ensureDictionaryLoaded();
        SortedSet<String> spaceWords = dictionary.getWords()
                .stream()
                .filter(predicate)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(spaceWords);
        return spaceWords;
    }

    //////////////////////////////
    //     DICTIONARY UTILS     //
    //////////////////////////////

    public static void loadDictionary(Class<? extends AbstractDictionary> dictionaryClass) {
        try {
            dictionary = dictionaryClass.getDeclaredConstructor().newInstance();
            System.out.println(Main.ANSI_GREEN + "Dictionary " + dictionaryClass.getSimpleName() + " loaded successfully!" + Main.ANSI_RESET);
        } catch (ReflectiveOperationException e) {
            System.out.println("Could not instantiate " + dictionaryClass.getSimpleName() + " dictionary.");
            e.printStackTrace();
        }
    }

    private static void ensureDictionaryLoaded() {
        Objects.requireNonNull(dictionary, "No dictionary has been loaded.");
    }
}
