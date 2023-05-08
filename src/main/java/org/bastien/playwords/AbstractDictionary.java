package org.bastien.playwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AbstractDictionary {
    private static final int SEPARATOR = 44;

    private SortedSet<String> words;

    AbstractDictionary(String fileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\" + fileName + ".txt"))) {
            this.words = parseWords(reader);
        } catch (IOException e) {
            System.err.println("An I/O exception occurred. Could not load the " + fileName + " file.");
            e.printStackTrace();
        }
    }

    private SortedSet<String> parseWords(BufferedReader reader) throws IOException {
        SortedSet<String> array = new TreeSet<>();
        String line;
        while ((line = reader.readLine()) != null) {
            int separatorIndex = line.indexOf(SEPARATOR);
            String word = separatorIndex == -1 ? line : line.substring(0, separatorIndex);
            array.add(word);
        }
        return array;
    }
    public SortedSet<String> getWords() {
        return this.words;
    }
}
