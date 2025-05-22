package com.renta.herramienta.aplication.service;

import com.renta.herramienta.domain.dto.DevolucionDTO;
import com.renta.herramienta.domain.request.DevolucionRequest;

public interface DevolucionService {
    void procesarDevolucion(DevolucionRequest request);
    DevolucionDTO obtenerDevolucionConReportes(Long id);
}


