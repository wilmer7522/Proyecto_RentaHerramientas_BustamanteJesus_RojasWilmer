package com.renta.herramienta.domain.request;

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
    private String correo;
}
