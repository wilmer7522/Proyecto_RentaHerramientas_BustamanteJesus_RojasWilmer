package com.renta.herramienta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFacturaDTO {
    private String nombreHerramienta;
    private double costoPorDia;
    private int diasAlquiler;
    private int cantidad;
    private double totalPorHerramienta;
}

