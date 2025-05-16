package com.renta.herramienta.infraestructure.repositories.proveedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByCorreo(String correo); // Heredado de Usuario
}
