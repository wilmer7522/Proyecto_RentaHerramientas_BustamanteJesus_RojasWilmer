package com.renta.herramienta.domain.mapper;

import com.renta.herramienta.domain.dto.ProveedorRequest;
import com.renta.herramienta.domain.entities.Proveedor;

public class ProveedorMapper {
    
    public static ProveedorRequest toDTO(Proveedor proveedor) {
        ProveedorRequest dto = new ProveedorRequest();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setTelefono(proveedor.getTelefono());
        dto.setDireccion(proveedor.getDireccion());
        dto.setNit(proveedor.getNit());

        return dto;
    }

}
