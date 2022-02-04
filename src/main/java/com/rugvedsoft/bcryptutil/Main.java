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
        System.out.println("\n\n=========================\n\n");
        if (args.length < 1) {
            System.err.println("\n\nUsage: java -jar bcryptutil-1.0-exec.jar <text-to-encode> <encoder-strength-between-4-and-31> [password-salt]\n\n");
        } else {
            boolean proceed = true;
            Integer encoderStrength = 12;
            if (args.length > 1) {
                try {
                    encoderStrength = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Password Encoder strength must be an integer");
                    proceed = false;
                    System.out.println("\n\n");
                }
                if (encoderStrength < 4 || encoderStrength > 31) {
                    System.err.println("Password Encoder strength must be between 4 and 31");
                    proceed = false;
                    System.out.println("\n\n");
                }
            }
            if (proceed) {
                String passwordSalt = null;
                if (args.length > 2) {
                    passwordSalt = args[2];
                }
                String toEncode = args[0];
                BCryptPasswordEncoder encoder = null;
                if (passwordSalt != null) {
                    encoder = new BCryptPasswordEncoder(encoderStrength, new SecureRandom(passwordSalt.getBytes()));
                } else {
                    encoder = new BCryptPasswordEncoder(encoderStrength);
                }
                System.out.println("Text to encode: \t" + toEncode);
                System.out.println("Encoder strength: \t" + encoderStrength);
                if (passwordSalt != null) {
                    System.out.println("Random Password Salt: \t" + passwordSalt);
                }
                System.out.println("\nEncoded text: [\n");
                System.out.println(encoder.encode(toEncode));
                System.out.println("\n]");
            }

        }
        System.out.println("\n\n=========================\n\n");
    }
}
