package com.renta.herramienta.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.renta.herramienta.domain.entities.EstadoReserva;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private Long id;
    private Long idCliente;
    private String nombreCliente;
    private List<HerramientaCantidadDTO> herramientas;
    
    private LocalDateTime fechaReserva;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estadoReserva;

    
}

