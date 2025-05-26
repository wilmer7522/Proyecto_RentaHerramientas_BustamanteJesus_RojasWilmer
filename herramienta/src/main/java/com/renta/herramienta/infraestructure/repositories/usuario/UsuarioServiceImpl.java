package com.renta.herramienta.infraestructure.repositories.usuario;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.UsuarioService;
import com.renta.herramienta.domain.entities.Rol;
import com.renta.herramienta.domain.entities.Usuario;
import com.renta.herramienta.domain.request.UsuarioRequest;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public Usuario registrar(UsuarioRequest request) {
        Rol rol = rolRepository.findById(request.getIdRol())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setCorreo(request.getCorreo());
        // Guardamos la contraseña tal cual (sin encriptar)
        usuario.setPassword(request.getPassword());
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}


