package com.renta.herramienta.domain.dto;

import java.time.LocalDate;

import com.renta.herramienta.domain.entities.EstadoPago;
import com.renta.herramienta.domain.entities.MetodoPago;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private Long id;
    private int monto;
    private LocalDate fechaPago;
    private EstadoPago estadoPago;
    private MetodoPago metodoPago;
    private Long alquilerId;
}
