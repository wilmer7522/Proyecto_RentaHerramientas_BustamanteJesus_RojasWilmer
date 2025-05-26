package com.renta.herramienta.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HerramientaInventarioResumenDTO {

    private Long id;
    private String nombre;
    private int cantidad;
    private int cantidadTotal;
}

