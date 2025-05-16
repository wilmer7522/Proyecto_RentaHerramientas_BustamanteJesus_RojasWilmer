package com.renta.herramienta.security;

/*import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.renta.herramienta.infraestructure.repositories.user.UserDetailServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Obtener el header "Authorization"
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String correoUsuario = null;

        // 2. Verificar si el header no es nulo y comienza con "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // 3. Extraer el token del header
            token = authHeader.substring(7); // elimina "Bearer "

            // 4. Validar el token
            if (jwtTokenProvider.validarToken(token)) {
                // 5. Obtener el correo del usuario desde el token
                correoUsuario = jwtTokenProvider.getCorreoUsuarioDesdeJWT(token);

                // 6. Verificar que no haya ya autenticación activa
                if (correoUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 7. Cargar los detalles del usuario desde la base de datos
                    UserDetails userDetails = userDetailsService.loadUserByUsername(correoUsuario);

                    // 8. Crear un objeto de autenticación
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // 9. Agregar información del request al token
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // 10. Establecer la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        // 11. Continuar con el siguiente filtro
        filterChain.doFilter(request, response);
    }

}*/
