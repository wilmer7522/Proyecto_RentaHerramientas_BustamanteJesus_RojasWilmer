package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.dto.FacturacionDTO;
import com.renta.herramienta.domain.entities.Facturacion;
import com.renta.herramienta.domain.request.FacturacionRequest;

public interface FacturacionService {
    FacturacionDTO crearFactura(FacturacionRequest request);

    FacturacionDTO obtenerFacturaPorId(Long id);
    
    List<FacturacionDTO> listarFacturas();
    Facturacion obtenerEntidadPorId(Long id);
}

