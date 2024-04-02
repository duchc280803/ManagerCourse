package com.example.managercourse.util;

import java.text.Normalizer;
import java.util.Random;

public class UsernamePasswordGenerator {

    public static String generateUsername(String fullName) {
        String normalizedFullName = Normalizer.normalize(fullName, Normalizer.Form.NFD);
        String username = normalizedFullName.replaceAll("[^\\p{ASCII}]", "").toLowerCase().replaceAll("\\s+", "");
        return username;
    }

    public static String generatePassword() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }
}
