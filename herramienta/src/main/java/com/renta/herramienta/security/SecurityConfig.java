package com.renta.herramienta.security;

/*import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.renta.herramienta.infraestructure.repositories.user.UserDetailServiceImpl;

@Configuration // Indica que esta clase define configuraciones de Spring (como beans).
@EnableWebSecurity // Habilita la seguridad web con Spring Security.
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
    private UserDetailServiceImpl userDetailService;
    // Inyectamos nuestro servicio personalizado que carga los usuarios desde la base de datos.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Define cómo se comporta la seguridad HTTP: qué rutas están protegidas, cómo se autentica, etc.

        return http
                .csrf(csrf -> csrf.disable())
                // Desactiva la protección CSRF (ya que estamos usando JWT, no formularios).

                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("*"));
                    config.setAllowedMethods(List.of("*"));
                    config.setAllowedHeaders(List.of("*"));
                    return config;
                }))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        // Permite el acceso sin autenticación a rutas bajo /auth (como login o registro).

                        .anyRequest().authenticated()
                        // Requiere autenticación para cualquier otra ruta.
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        // Desactiva la sesión del servidor. Todo se maneja por tokens (JWT).
                )
                .authenticationProvider(authenticationProvider())
                // Se registra el proveedor de autenticación personalizado (usando nuestro userDetailsService y passwordEncoder).

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build(); // Devuelve el filtro de seguridad completo.
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Spring Boot se encarga de construir el AuthenticationManager usando la configuración de seguridad.
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define el encoder que se usa para cifrar/validar contraseñas. BCrypt es el más recomendado.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Proveedor de autenticación basado en DAO (acceso a base de datos con UserDetailsService).

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService); // Usamos nuestro servicio personalizado.
        authProvider.setPasswordEncoder(passwordEncoder()); // Asocia el encoder para validar contraseñas cifradas.
        return authProvider;
    }

}*/
