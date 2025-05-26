package com.renta.herramienta.domain.mapper;

import com.renta.herramienta.domain.dto.PagoDTO;
import com.renta.herramienta.domain.entities.Pago;

public class PagoMapper {

    public static PagoDTO toDTO(Pago pago) {
        if (pago == null) {
            return null;
        }

        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setEstadoPago(pago.getEstadoPago());
        dto.setMetodoPago(pago.getMetodoPago());

        if (pago.getAlquiler() != null) {
            dto.setAlquilerId(pago.getAlquiler().getId());
        }

        return dto;
    }
}
