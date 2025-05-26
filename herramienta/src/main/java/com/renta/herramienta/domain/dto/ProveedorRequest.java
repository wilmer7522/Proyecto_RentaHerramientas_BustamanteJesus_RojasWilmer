package com.renta.herramienta.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorRequest {
    private Long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String nit;
    
    public ProveedorRequest() {
    }

    public ProveedorRequest(Long id, String nombre, String telefono, String direccion, String nit) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
    }

}
