package com.renta.herramienta.infraestructure.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        String rawPassword = "jesus1412";
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println("Contraseña cifrada: " + encodedPassword);
    }
}
