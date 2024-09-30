package com.newlecmineursprj.util;

import java.util.Random;

public class RandomPasswordGenerator {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();

    public static String generate(int count) {
        StringBuilder stringBuilder = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            stringBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return stringBuilder.toString();
    }
}
