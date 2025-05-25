package com.renta.herramienta.domain.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerDTO {
    
    private Long id;
    private Long idCliente;
    private String nombreCliente;

    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;

    private String estadoAlquiler;

    private Long idReserva;

    private List<HerramientaDTO> herramientas;


    private List<DetalleAlquilerDTO> detalles;
}

