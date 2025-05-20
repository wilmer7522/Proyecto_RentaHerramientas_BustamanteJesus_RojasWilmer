package com.renta.herramienta.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorUpdateRequest {
    private String nombre;
    private String telefono;
    private String direccion;
    private String nit;
}
