package com.renta.herramienta.infraestructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.UsuarioService;
import com.renta.herramienta.domain.entities.Usuario;
import com.renta.herramienta.domain.request.UsuarioRequest;


@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody UsuarioRequest request) {
        Usuario usuario = usuarioService.registrar(request);
        return ResponseEntity.ok(usuario);
    }
}



