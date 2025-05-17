package com.renta.herramienta.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteUpdateRequest {
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String cedula;
}
