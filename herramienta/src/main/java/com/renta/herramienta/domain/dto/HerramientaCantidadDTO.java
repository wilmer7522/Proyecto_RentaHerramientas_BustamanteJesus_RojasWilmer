package com.renta.herramienta.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HerramientaCantidadDTO {
    private Long id;
    private String nombre;
    private int cantidad;
}

