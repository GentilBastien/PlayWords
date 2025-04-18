package org.bastien.playwords.dictionaries;

import org.bastien.playwords.Language;
import org.bastien.playwords.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class Dictionary {
    private static Dictionary dictionary;
    private final SortedSet<String> words;

    protected Dictionary(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\" + fileName + ".txt"));
        this.words = parseWords(reader);
    }

    public static void load(Class<? extends Dictionary> dictionaryClass) {
        String outputString = "";
        try {
            dictionary = dictionaryClass.getDeclaredConstructor().newInstance();
            outputString = buildOutputString(
                    dictionaryClass.getSimpleName() + " loaded successfully! (" + dictionary.words.size() + " words)",
                    true);
        } catch (Exception ex) {
            String exMsg = ex.getCause().getMessage();
            outputString = buildOutputString(dictionaryClass.getSimpleName() + " could not be loaded: " + exMsg, false);
        } finally {
            System.out.println(outputString);
        }
    }

    public static Language use() {
        return new Language(dictionary.words);
    }

    private static String buildOutputString(String msg, boolean goodResult) {
        return (goodResult ? Main.ANSI_GREEN : Main.ANSI_RED) + msg + Main.ANSI_RESET;
    }

    private SortedSet<String> parseWords(BufferedReader reader) throws Exception {
        SortedSet<String> arr = new TreeSet<>();
        String word;
        int lineNumber = 0;
        while ((word = reader.readLine()) != null) {
            if (!word.isEmpty()) {
                if (!arr.add(word)) {
                    throw new AlreadyExistException(word);
                }
            } else {
                throw new BlankLineException(lineNumber);
            }
            lineNumber++;
        }
        return arr;
    }

    public SortedSet<String> getWords() {
        return this.words;
    }
}
