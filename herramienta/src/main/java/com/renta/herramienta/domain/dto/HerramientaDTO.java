package com.renta.herramienta.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HerramientaDTO {
    private Long id;
    private String nombre;

    public HerramientaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


}

