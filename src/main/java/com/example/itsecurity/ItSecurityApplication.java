package com.example.itsecurity;

import com.example.itsecurity.Services.BruteForcePasswordCracker;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
//@EnableWebSecurity
public class ItSecurityApplication {

	public static void main(String[] args) throws SQLException, IOException {
		SpringApplication.run(ItSecurityApplication.class, args);
		//String salt = BCrypt.hashpw("abcd", BCrypt.gensalt(12));
		//String salt = BCrypt.hashpw("1234", BCrypt.gensalt());
		//System.out.println(hashed);

		/*
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String result = encoder.encode("1234");
		System.out.println(result);

		 */

		BruteForcePasswordCracker bfpc = new BruteForcePasswordCracker();







	}

}
