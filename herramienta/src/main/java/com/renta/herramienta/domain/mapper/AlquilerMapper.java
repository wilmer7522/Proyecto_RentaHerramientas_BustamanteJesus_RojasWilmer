package com.renta.herramienta.domain.mapper;

import java.util.stream.Collectors;

import com.renta.herramienta.domain.dto.AlquilerDTO;
import com.renta.herramienta.domain.dto.DetalleAlquilerDTO;
import com.renta.herramienta.domain.dto.HerramientaDTO;
import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.entities.DetalleAlquiler;

public class AlquilerMapper {

    public static AlquilerDTO toDTO(Alquiler alquiler) {
        if (alquiler == null) return null;

        AlquilerDTO dto = new AlquilerDTO();
        dto.setId(alquiler.getId());
        dto.setFechaAlquiler(alquiler.getFecha_alquiler());
        dto.setFechaDevolucion(alquiler.getFecha_devolucion());
        dto.setEstadoAlquiler(alquiler.getEstadoAlquiler().name());

        if (alquiler.getCliente() != null) {
            dto.setIdCliente(alquiler.getCliente().getId());
            dto.setNombreCliente(alquiler.getCliente().getNombre());
        }

        if (alquiler.getReserva() != null) {
            dto.setIdReserva(alquiler.getReserva().getId());
            dto.setHerramientas(
                alquiler.getReserva().getHerramientas()
                        .stream()
                        .map(h -> new HerramientaDTO(h.getId(), h.getNombre()))
                        .collect(Collectors.toList())
            );
        }

        if (alquiler.getDetalle() != null) {
            dto.setDetalles(
                alquiler.getDetalle().stream()
                        .map(AlquilerMapper::toDetalleDTO)
                        .collect(Collectors.toList())
            );
        }

        return dto;
    }

    private static DetalleAlquilerDTO toDetalleDTO(DetalleAlquiler detalle) {
        DetalleAlquilerDTO dto = new DetalleAlquilerDTO();
        dto.setId(detalle.getId());
        dto.setCantidadDias(detalle.getCantidad_dias());
        dto.setPrecioUnitario(detalle.getPrecio_unitario());
        dto.setSubtotal(detalle.getSubtotal());

        if (detalle.getHerramienta() != null) {
            dto.setIdHerramienta(detalle.getHerramienta().getId());
            dto.setNombreHerramienta(detalle.getHerramienta().getNombre());
        }

        return dto;
    }
}

