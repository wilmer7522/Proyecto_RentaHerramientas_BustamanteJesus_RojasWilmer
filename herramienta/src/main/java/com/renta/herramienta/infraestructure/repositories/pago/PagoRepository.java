package com.renta.herramienta.infraestructure.repositories.pago;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    boolean existsByAlquiler(Alquiler alquiler);
}

