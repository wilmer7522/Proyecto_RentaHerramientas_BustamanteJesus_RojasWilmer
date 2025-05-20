package com.renta.herramienta.domain.mapper;

import com.renta.herramienta.domain.dto.ClienteDTO;
import com.renta.herramienta.domain.entities.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setCorreo(cliente.getCorreo());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setCedula(cliente.getCedula());
        return dto;
    }
}

