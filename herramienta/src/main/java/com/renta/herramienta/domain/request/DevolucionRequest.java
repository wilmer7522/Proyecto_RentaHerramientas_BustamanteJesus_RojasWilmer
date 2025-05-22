package com.renta.herramienta.domain.request;

import java.util.Map;

import com.renta.herramienta.domain.entities.DetalleDevolucion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionRequest {
    private Long alquilerId;
    private Map<Long, DetalleDevolucion> herramientasDevueltas; // idHerramienta -> estáDañada (true = dañada)
}

