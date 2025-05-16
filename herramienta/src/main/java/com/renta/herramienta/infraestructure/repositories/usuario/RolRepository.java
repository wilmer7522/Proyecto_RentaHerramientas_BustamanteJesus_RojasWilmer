package com.renta.herramienta.infraestructure.repositories.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
    
}
