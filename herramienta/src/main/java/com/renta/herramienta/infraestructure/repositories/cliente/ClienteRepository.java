package com.renta.herramienta.infraestructure.repositories.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
