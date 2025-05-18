package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.dto.ReservaRequest;
import com.renta.herramienta.domain.entities.Reserva;

public interface ReservaService {

    Reserva createNewReserva(ReservaRequest request);


    List<Reserva> getReservasByCliente(Long id);


    Reserva getReservaById(Long id);

}




