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
    private final long startTime = System.currentTimeMillis();

    public BruteForcePasswordCracker() throws SQLException, IOException {

        int minLength = 2;
        int maxLength = 9;
        int minAscii = 32;
        int maxAscii = 255;
        Set<String> knownPwds = loadKnownPasswords("src/main/resources/10k-worst-passwords.txt");

        knownPwds.forEach(p -> {
            try {
                if (pwdCheck(p)) {
                    pwdFound(p);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("NU kom vi hit");
        bruteForcePassword(minLength, maxLength, minAscii, maxAscii);

    }

    public void bruteForcePassword(int minLength, int maxLength, int minAscii, int maxAscii) throws SQLException, IOException {
        for (int length = minLength; length <= maxLength; length++) {

            bruteForceRecursion(new char[length], 0, minAscii, maxAscii);
        }
        System.out.println("Password not found.");
    }

    public void bruteForceRecursion(char[] currentAttempt, int position, int minAscii, int maxAscii) throws SQLException, IOException {
        if (position == currentAttempt.length) {
            iterations++;
            String currentAttemptStr = new String(currentAttempt);

            if (pwdCheck(currentAttemptStr)) {
                pwdFound(currentAttemptStr);
            }
            return;
        }

        for (int ascii = minAscii; ascii <= maxAscii; ascii++) {
            currentAttempt[position] = (char) ascii;
            bruteForceRecursion(currentAttempt, position + 1, minAscii, maxAscii);
        }
    }

    private void pwdFound(String pwd) {
        long endTime = System.currentTimeMillis(); // Record ending time
        long elapsedTime = endTime - startTime;

        System.out.println("Password found: " + pwd);
        System.out.println("Iterations: " + formatIterations(iterations));
        System.out.println("Time taken: " + formatElapsedTime(elapsedTime));

        System.exit(0);
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

    public boolean pwdCheck(String pwd) throws SQLException, IOException {
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
        LoginController loginController = new LoginController();
        return Objects.equals(loginController.verifyUser("Bob", pwd, model), "successful-login");
    }
}