package com.royarn.mini.java8;

public class Splitetator {

    static final String SENTENCE = "Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " che la dritta via                    era smarrita ";

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
                continue;
            }
            if (lastSpace) counter++;
            lastSpace = false;
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println("Found : " + countWordsIteratively(SENTENCE) + " words");
    }
}