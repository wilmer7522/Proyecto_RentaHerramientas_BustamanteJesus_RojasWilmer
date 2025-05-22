package com.renta.herramienta.domain.request;

import com.renta.herramienta.domain.entities.MetodoPago;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequest {

    @NotNull(message = "El ID del alquiler es obligatorio")
    private Long alquilerId;

    @Positive(message = "El monto debe ser mayor a cero")
    private int monto;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;
}


