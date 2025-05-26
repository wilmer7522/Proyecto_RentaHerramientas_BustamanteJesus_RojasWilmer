package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Reserva;
import com.renta.herramienta.domain.request.ReservaRequest;

public interface ReservaService {

    Reserva createNewReserva(ReservaRequest request);

    List<Reserva> getReservasByCliente(Long id);

    Reserva getReservaById(Long id);

    public List<Reserva> getReservasPendientes();

    void actualizarEstado(Long id, EstadoReserva estado);

    List<Reserva> getAllReservasPendientes();



}
