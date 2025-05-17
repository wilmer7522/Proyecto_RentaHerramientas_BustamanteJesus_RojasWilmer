package com.renta.herramienta.infraestructure.security;

    
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desactiva CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite TODO sin autenticación
            )
            .formLogin().disable() // Desactiva el formulario de login
            .httpBasic().disable(); // Desactiva autenticación básica (opcional)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usa el algoritmo BCrypt
    }
}

