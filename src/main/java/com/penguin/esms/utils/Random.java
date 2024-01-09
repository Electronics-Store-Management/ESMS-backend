package com.penguin.esms.utils;

import java.security.SecureRandom;
public class Random {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();
    private static int length;
    public static String random(int length, String characters) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(secureRandom.nextInt(characters.length()));
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
