package com.example.managercourse.util;

import java.text.Normalizer;
import java.util.Random;

public class UsernamePasswordGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    private UsernamePasswordGenerator() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static String generateUsername(String fullName) {
        String normalizedFullName = Normalizer.normalize(fullName, Normalizer.Form.NFD);
        return normalizedFullName.replaceAll("[^\\p{ASCII}]", "").toLowerCase().replaceAll("\\s+", "");
    }

    public static String generatePassword() {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return password.toString();
    }
}
