package com.renta.herramienta.domain.dto;

import java.util.stream.Collectors;

import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Reserva;


public class ReservaMapper {

    // Mapper que convierte Reserva en ReservaRequest (para devolver una reserva creada o encontrada)
    public static ReservaRequest toReservaRequest(Reserva reserva) {
        ReservaRequest dto = new ReservaRequest();

        dto.setIdCliente(reserva.getCliente().getId());

        dto.setId_herramientas(
            reserva.getHerramientas().stream()
                .map(Herramienta::getId)
                .collect(Collectors.toList())
        );

        dto.setFechaReserva(reserva.getFecha_reserva());
        dto.setFechaInicio(reserva.getFecha_inicio());
        dto.setFechaFin(reserva.getFecha_fin());
        dto.setEstadoReserva(reserva.getEstado_reserva());

        return dto;
    }

    // Mapper que convierte Reserva en ReservaDTO (para mostrar información detallada al cliente)
    public static ReservaDTO toReservaDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();

        dto.setId(reserva.getId());
        dto.setNombreCliente(reserva.getCliente().getNombre());

        dto.setHerramientas(
            reserva.getHerramientas().stream()
                .map(h -> new HerramientaDTO(h.getId(), h.getNombre()))
                .collect(Collectors.toList())
        );

        dto.setFechaReserva(reserva.getFecha_reserva());
        dto.setFechaInicio(reserva.getFecha_inicio());
        dto.setFechaFin(reserva.getFecha_fin());
        dto.setEstadoReserva(reserva.getEstado_reserva());

        return dto;
    }
}

