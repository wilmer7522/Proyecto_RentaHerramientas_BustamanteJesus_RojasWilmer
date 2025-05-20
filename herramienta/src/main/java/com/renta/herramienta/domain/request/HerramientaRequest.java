package com.renta.herramienta.domain.request;

import com.renta.herramienta.domain.entities.EstadoHerramienta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HerramientaRequest {
    private Long id;
    private String nombre;
    private String descripcion;
    private int costo_dia;
    private boolean disponible;
    private EstadoHerramienta estado_Herramienta;
    private Long categoriaId;
}
