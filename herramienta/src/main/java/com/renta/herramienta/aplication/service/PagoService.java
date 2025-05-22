package com.renta.herramienta.aplication.service;

import com.renta.herramienta.domain.dto.PagoDTO;
import com.renta.herramienta.domain.entities.EstadoPago;

import com.renta.herramienta.domain.request.PagoRequest;

public interface PagoService {
    PagoDTO registrarPago(PagoRequest request);
    PagoDTO obtenerPagoPorId(Long id);
    PagoDTO confirmarPago(Long id);

   
    PagoDTO actualizarEstadoPago(Long id, EstadoPago fallido);


  
    
}

