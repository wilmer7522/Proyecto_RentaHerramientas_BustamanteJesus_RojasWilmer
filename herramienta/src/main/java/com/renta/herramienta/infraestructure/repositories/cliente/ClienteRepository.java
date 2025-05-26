package com.renta.herramienta.infraestructure.repositories.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNombre(String nombre);

}
