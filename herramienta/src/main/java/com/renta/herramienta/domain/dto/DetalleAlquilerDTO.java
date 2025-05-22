package com.renta.herramienta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleAlquilerDTO {

    private Long id;
    private Long idHerramienta;
    private String nombreHerramienta;

    private int cantidadDias;
    private double precioUnitario;
    private double subtotal;
}

