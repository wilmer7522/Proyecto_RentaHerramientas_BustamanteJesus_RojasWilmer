package com.renta.herramienta.infraestructure.repositories.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    boolean existsByCorreo(String correo); // Para validar correo único

    Optional<Usuario> findByCorreo(String correo);
}
