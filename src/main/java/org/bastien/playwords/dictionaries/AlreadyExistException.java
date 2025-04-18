package org.bastien.playwords.dictionaries;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String word) {
        super("The word " + word + " already exists in the dictionary.");
    }
}
