package com.renta.herramienta.domain.mapper;

import com.renta.herramienta.domain.dto.ProveedorDTO;
import com.renta.herramienta.domain.entities.Proveedor;

public class ProveedorMapper {

    public static ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) return null;

        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setTelefono(proveedor.getTelefono());
        dto.setCorreo(proveedor.getCorreo());
        dto.setDireccion(proveedor.getDireccion());
        dto.setNit(proveedor.getNit());

        return dto;
    }

    public static Proveedor toEntity(ProveedorDTO dto) {
        if (dto == null) return null;

        Proveedor proveedor = new Proveedor();
        proveedor.setId(dto.getId());
        proveedor.setNombre(dto.getNombre());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreo(dto.getCorreo());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setNit(dto.getNit());

        return proveedor;
    }
}

