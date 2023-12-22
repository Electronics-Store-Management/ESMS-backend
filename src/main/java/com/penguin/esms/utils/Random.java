package com.penguin.esms.utils;

import java.security.SecureRandom;
public class Random {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String random() {
        int length = 10;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char randomChar = CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
