package com.renta.herramienta.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.domain.dtos.AuthResponse;
import com.renta.herramienta.domain.dtos.LoginRequest;
import com.renta.herramienta.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Intentando autenticar: " + loginRequest.getCorreo());
            // 1. Autentica las credenciales usando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getCorreo(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Establece la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3. Extrae el correo del usuario autenticado
            String correoUsuario = authentication.getName();

            // 4. Genera el token JWT usando el correo
            String token = jwtTokenProvider.generateToken(correoUsuario);

            // 5. Retorna el token en una respuesta
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (Exception ex) {
            System.out.println("Error durante autenticación: " + ex.getMessage());
            ex.printStackTrace(); // Esto mostrará el stack trace completo
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @GetMapping("/publico")
    public ResponseEntity<String> rutaPublica() {
        return ResponseEntity.ok("Acceso público permitido");
    }


}
