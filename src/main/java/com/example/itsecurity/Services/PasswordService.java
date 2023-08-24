package com.example.itsecurity.Services;

import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public boolean isValidPassword(String password) {
        return password.matches("(\\d{4})");
    }

    public boolean isSecureValidPassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }


        boolean containsDigit = password.matches(".*\\d.*");
        boolean containsLetter = password.matches(".*[a-zA-Z].*");
        boolean containsOtherCharacters = password.matches(".*[^\\d\\sA-Za-z].*");

        return containsDigit && containsLetter && containsOtherCharacters;
    }
}
//\d{4}

