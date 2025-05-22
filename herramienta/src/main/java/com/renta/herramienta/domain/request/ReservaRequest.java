package com.renta.herramienta.domain.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.renta.herramienta.domain.entities.EstadoReserva;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class ReservaRequest {

    public static final String LocalDateTime = null;

    
    private Long idCliente;

    @NotEmpty(message = "Debe seleccionar al menos una herramienta")
    private List<Long> id_herramientas;

    @NotNull(message = "La fecha de reserva es obligatoria")
    private LocalDateTime fechaReserva;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotNull(message = "El estado de la reserva no debe ser nulo")
    private EstadoReserva estadoReserva;
}


