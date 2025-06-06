package com.renta.herramienta.infraestructure.repositories.reserva;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Reserva;

import java.util.List;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByClienteId(Long idcliente);

    List<Reserva> findByEstadoReserva(EstadoReserva estado);


}

