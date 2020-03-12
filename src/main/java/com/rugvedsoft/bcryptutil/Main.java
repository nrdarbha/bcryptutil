package com.rugvedsoft.bcryptutil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\n=======================================");
        if (args.length < 1) {
            System.err.println("Provide text to encrypt");
        } else {
            String toEncode = args[0];
            String passwordSalt = null;
            if (args.length > 1) {
                passwordSalt = args[1];
            }
            BCryptPasswordEncoder encoder = null;
            if (passwordSalt != null) {
                encoder = new BCryptPasswordEncoder(12, new SecureRandom(passwordSalt.getBytes()));
            } else {
                encoder = new BCryptPasswordEncoder(12);
            }
            System.out.println("Text to encode: " + toEncode);
            System.out.println("Encoded text: [");
            System.out.println(encoder.encode(toEncode));
            System.out.println("]");
        }
        System.out.println("\n\n=======================================\n\n");
    }
}
