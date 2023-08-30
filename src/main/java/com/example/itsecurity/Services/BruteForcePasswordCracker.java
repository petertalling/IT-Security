package com.example.itsecurity.Services;

import com.example.itsecurity.Controllers.LoginController;
import org.springframework.ui.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;

public class BruteForcePasswordCracker {

    private static long iterations = 0;

    public BruteForcePasswordCracker() throws SQLException, IOException {

        int minLength = 2;
        int maxLength = 9;
        int minAscii = 32;
        int maxAscii = 126;

        bruteForcePassword(minLength, maxLength, minAscii, maxAscii);
    }

    public static void bruteForcePassword(int minLength, int maxLength, int minAscii, int maxAscii) throws SQLException, IOException {
        for (int length = minLength; length <= maxLength; length++) {

            long startTime = System.currentTimeMillis(); // Record starting time
            bruteForceRecursion(new char[length], 0, minAscii, maxAscii, startTime);
        }
        System.out.println("Password not found.");
    }

    public static void bruteForceRecursion(char[] currentAttempt, int position, int minAscii, int maxAscii, long startTime) throws SQLException, IOException {
        if (position == currentAttempt.length) {
            iterations++;
            String currentAttemptStr = new String(currentAttempt);

            LoginController loginController = new LoginController();
            Model model = new Model() {
                @Override
                public Model addAttribute(String attributeName, Object attributeValue) {
                    return null;
                }

                @Override
                public Model addAttribute(Object attributeValue) {
                    return null;
                }

                @Override
                public Model addAllAttributes(Collection<?> attributeValues) {
                    return null;
                }

                @Override
                public Model addAllAttributes(Map<String, ?> attributes) {
                    return null;
                }

                @Override
                public Model mergeAttributes(Map<String, ?> attributes) {
                    return null;
                }

                @Override
                public boolean containsAttribute(String attributeName) {
                    return false;
                }

                @Override
                public Object getAttribute(String attributeName) {
                    return null;
                }

                @Override
                public Map<String, Object> asMap() {
                    return null;
                }
            };


            if (Objects.equals(loginController.verifyUser("Barbie", currentAttemptStr, model), "successful-login")) {
                long endTime = System.currentTimeMillis(); // Record ending time
                long elapsedTime = endTime - startTime;

                System.out.println("Password found: " + currentAttemptStr);
                System.out.println("Iterations: " + formatIterations(iterations));
                System.out.println("Time taken: " + formatElapsedTime(elapsedTime));

                System.exit(0);
            }
            return;
        }

        for (int ascii = minAscii; ascii <= maxAscii; ascii++) {
            currentAttempt[position] = (char) ascii;
            bruteForceRecursion(currentAttempt, position + 1, minAscii, maxAscii, startTime);
        }
    }

    public static Set<String> loadKnownPasswords(String filename) {
        Set<String> passwords = new HashSet<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                passwords.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return passwords;
    }

    public static String formatIterations(long number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(number);
    }

    public static String formatElapsedTime(long timeMillis) {
        long seconds = timeMillis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%d minutes, %d seconds", minutes, seconds);
    }
}