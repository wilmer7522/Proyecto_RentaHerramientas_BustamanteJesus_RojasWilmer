package com.renta.herramienta.aplication.service;

import java.util.Optional;

import com.renta.herramienta.domain.entities.Usuario;
import com.renta.herramienta.domain.request.UsuarioRequest;

public interface UsuarioService {
    
    
    Usuario registrar(UsuarioRequest request);

    Optional<Usuario> buscarPorCorreo(String correo);

}

