package com.renta.herramienta.domain.mapper;

import java.util.stream.Collectors;
import java.util.List;

import com.renta.herramienta.domain.dto.HerramientaCantidadDTO;
import com.renta.herramienta.domain.dto.ReservaDTO;
import com.renta.herramienta.domain.entities.Reserva;
import com.renta.herramienta.domain.entities.DetalleReserva;
import com.renta.herramienta.domain.request.ReservaRequest;

public class ReservaMapper {

    public static ReservaRequest toReservaRequest(Reserva reserva) {
        ReservaRequest dto = new ReservaRequest();

        dto.setIdCliente(reserva.getCliente().getId());

        List<HerramientaCantidadDTO> herramientas = reserva.getDetalleReserva().stream()
            .map((DetalleReserva detalle) -> new HerramientaCantidadDTO(
                detalle.getHerramienta().getId(),
                null, detalle.getCantidad() // aquí sólo el id y cantidad porque ReservaRequest no necesita nombre
            ))
            .collect(Collectors.toList());

        dto.setHerramientas(herramientas);

        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setEstadoReserva(reserva.getEstadoReserva());

        return dto;
    }

    public static ReservaDTO toReservaDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();

        dto.setId(reserva.getId());
        dto.setIdCliente(reserva.getCliente().getId());
        dto.setNombreCliente(reserva.getCliente().getNombre());

        // Aquí usamos HerramientaCantidadDTO para incluir cantidad y nombre
        List<HerramientaCantidadDTO> herramientas = reserva.getDetalleReserva().stream()
            .map(detalle -> new HerramientaCantidadDTO(
                detalle.getHerramienta().getId(),
                detalle.getHerramienta().getNombre(),
                detalle.getCantidad()
            ))
            .collect(Collectors.toList());

        dto.setHerramientas(herramientas);

        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setFechaInicio(reserva.getFechaInicio());
        dto.setFechaFin(reserva.getFechaFin());
        dto.setEstadoReserva(reserva.getEstadoReserva());

        return dto;
    }
}




