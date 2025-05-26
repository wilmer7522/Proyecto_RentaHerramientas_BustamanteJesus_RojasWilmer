package com.renta.herramienta.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDevolucion {

    private int cantidad; 
    private boolean danada;
    private String descripcion;
}

