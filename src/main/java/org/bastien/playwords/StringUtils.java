package org.bastien.playwords;

import org.apache.commons.lang3.ArrayUtils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class StringUtils {

    public static String stripAccents(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }

    public static String normalize(String str) {
        return stripAccents(str).toLowerCase();
    }

    public static char[] sortToCharArray(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return chars;
    }

    public static char[] sortToCharArrayNormalized(String str) {
        return sortToCharArray(normalize(str));
    }

    public static boolean containsALlCharactersOf(String str1, String str2) {
        assert str1.length() >= str2.length();
        StringBuilder sb1 = new StringBuilder(str1);
        StringBuilder sb2 = new StringBuilder(str2);
        while (!sb2.isEmpty()) {
            char c = sb2.charAt(0);
            int idx1 = sb1.indexOf(String.valueOf(c));
            if (idx1 == -1)
                return false;
            sb1.deleteCharAt(idx1);
            sb2.deleteCharAt(0);
        }
        return true;
    }

    public static boolean containsALlCharactersNormalizedOf(String str1, String str2) {
        return containsALlCharactersOf(normalize(str1), normalize(str2));
    }

    public static boolean isSymmetricalString(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean areSymmetricalStrings(String str1, String str2) {
        StringBuilder sb1 = new StringBuilder(str1);
        StringBuilder sb2 = new StringBuilder(str2);
        while (sb1.isEmpty() || sb2.isEmpty()) {
            int sb2LastCharIdx = sb2.length() - 1;
            char sb1char = sb1.charAt(0);
            char sb2char = sb2.charAt(sb2LastCharIdx);
            if (Character.isWhitespace(sb1char)) {
                sb1.deleteCharAt(0);
                continue;
            }
            if (Character.isWhitespace(sb2char)) {
                sb2.deleteCharAt(sb2LastCharIdx);
                continue;
            }
            if (sb1char != sb2char)
                return false;
            sb1.deleteCharAt(0);
            sb2.deleteCharAt(sb2LastCharIdx);
        }
        return sb1.isEmpty() && sb2.isEmpty();
    }

    public static SortedSet<String> buildNormalizedVariants(String str) {
        char[] chars = sortToCharArrayNormalized(str);
        return buildVariants(chars, chars.length, new TreeSet<>());
    }

    public static SortedSet<String> buildVariants(String str) {
        char[] chars = sortToCharArray(str);
        return buildVariants(chars, chars.length, new TreeSet<>());
    }

    public static SortedSet<String> buildVariants(char[] chars, int size, SortedSet<String> variantsAccumulator) {
        if (size == 1) {
            variantsAccumulator.add(new String(chars));
        } else {
            for (int i = 0; i < size; i++) {
                buildVariants(chars, size - 1, variantsAccumulator);
                int j = (size % 2 == 0) ? i : 0;
                ArrayUtils.swap(chars, j, size - 1);
            }
        }
        return variantsAccumulator;
    }

    public static boolean areSymmetricalNormalizedStrings(String str1, String str2) {
        return areSymmetricalStrings(normalize(str1), normalize(str2));
    }

    public static boolean isSymmetricalNormalizedString(String str) {
        return isSymmetricalString(normalize(str));
    }

    public static void main(String[] args) {
        char[] arr = {'b', 'a', 's', 't', 'i', 'e', 'n'};
        System.out.println(buildVariants(arr, arr.length, new TreeSet<>()));
    }
}
