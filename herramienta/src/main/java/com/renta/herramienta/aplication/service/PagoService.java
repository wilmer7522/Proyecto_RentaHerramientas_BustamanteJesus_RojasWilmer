package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.dto.PagoDTO;
import com.renta.herramienta.domain.entities.EstadoPago;

import com.renta.herramienta.domain.request.PagoRequest;

public interface PagoService {
    PagoDTO registrarPago(PagoRequest request);
    PagoDTO obtenerPagoPorId(Long id);
    PagoDTO confirmarPago(Long id);

    List<PagoDTO> obtenerPagosPendientes();


   
    PagoDTO actualizarEstadoPago(Long id, EstadoPago fallido);
    List<PagoDTO> listarPagos();


  
    
}

