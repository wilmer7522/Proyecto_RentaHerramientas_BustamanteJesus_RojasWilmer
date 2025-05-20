package com.renta.herramienta.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.renta.herramienta.domain.entities.Estado_Reserva;

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
    private String nombreCliente;
    private List<HerramientaDTO> herramientas;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Estado_Reserva estadoReserva;

    
}

