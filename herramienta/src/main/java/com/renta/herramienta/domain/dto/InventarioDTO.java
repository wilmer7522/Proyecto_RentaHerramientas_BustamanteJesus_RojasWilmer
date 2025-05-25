package com.renta.herramienta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {
    private Long id;
    private String nombreHerramienta;
    private int cantidadTotal;
    private int cantidadDisponible;
    private String nombreProveedor;
}
