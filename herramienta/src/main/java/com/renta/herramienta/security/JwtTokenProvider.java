package com.renta.herramienta.security;

/*import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
    
    private Key jwtSecretKey;  // Clave segura generada automáticamente

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationInMs;

    public JwtTokenProvider() {
        this.jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Genera una clave de 512 bits segura
    }

    public String generateToken(String correoUsuario) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(correoUsuario)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtSecretKey) // Usa la clave generada
                .compact();
    }

    public String getCorreoUsuarioDesdeJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            System.out.println("Error al validar token: " + ex.getMessage());
            return false;
        }
    }
}*/
