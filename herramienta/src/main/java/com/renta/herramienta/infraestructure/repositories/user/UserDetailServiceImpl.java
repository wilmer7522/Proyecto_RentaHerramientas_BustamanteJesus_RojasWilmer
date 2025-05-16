package com.renta.herramienta.infraestructure.repositories.user;

/*import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.renta.herramienta.domain.entities.Usuario;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyecta el repositorio para buscar usuarios por correo.

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        System.out.println("Buscando usuario: " + correo);

        // Busca al usuario por correo. Si no existe, lanza una excepción.
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        System.out.println("Contraseña en BD: " + usuario.getPassword());

        // Crea y retorna un objeto User de Spring Security con:
        // - username = correo
        // - password = contraseña encriptada (usada para validación)
        // - authorities = lista con el rol del usuario ("ROLE_admin", por ejemplo)
        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()))
        );
        
    }
    
}*/
