package com.renta.herramienta.domain.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerRequest {

    private Long idReserva;

    private LocalDate fechaAlquiler;

    private LocalDate fechaDevolucion;

   
}

