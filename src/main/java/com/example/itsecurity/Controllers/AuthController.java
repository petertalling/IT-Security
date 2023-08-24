package com.example.itsecurity.Controllers;

import com.example.itsecurity.Model.Login;
import com.example.itsecurity.Services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {


        private final PasswordService passwordService;

        @Autowired
        public AuthController(PasswordService passwordService) {
            this.passwordService = passwordService;
        }

        @PostMapping("/authenticate")
        public String authenticate(@RequestBody Login login) {

            System.out.println(login.getPassword());
            if (passwordService.isSecureValidPassword(login.getPassword())) {
                return "Autentisering lyckades!";
            } else {
                return "Autentisering misslyckades. Ogiltigt lösenord.";
            }
        }
    }

