package com.renta.herramienta.domain.request;

import java.util.List;

import com.renta.herramienta.domain.entities.DetalleDevolucionPorHerramienta;

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
    private List<DetalleDevolucionPorHerramienta> herramientas; // Cada herramienta con sus unidades devueltas
}


