package com.renta.herramienta.infraestructure.repositories.alquiler;



import com.renta.herramienta.domain.entities.Alquiler;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByClienteId(Long clienteId);

}

