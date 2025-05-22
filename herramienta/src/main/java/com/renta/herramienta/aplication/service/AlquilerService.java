package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.request.AlquilerRequest;

public interface AlquilerService {

    Alquiler crearAlquilerDesdeReserva(AlquilerRequest request);

    Alquiler getAlquilerById(Long id);

    List<Alquiler> getAllAlquileres();

    List<Alquiler> getAlquileresPorCliente(Long clienteId);


}

