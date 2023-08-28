package com.example.itsecurity;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItSecurityApplication.class, args);
		//String salt = BCrypt.hashpw("abcd", BCrypt.gensalt(12));
		//String salt = BCrypt.hashpw("1234", BCrypt.gensalt());
		//System.out.println(hashed);

	}

}
