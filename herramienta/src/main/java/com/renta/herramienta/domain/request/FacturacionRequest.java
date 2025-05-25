package com.renta.herramienta.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NotNull
public class FacturacionRequest {

    // El total se recibe directamente, aunque podría calcularse desde el pago asociado
    @NotNull
    private double total;

    // ID del pago asociado a la factura
    @NotNull
    private Long pagoId;

    // ID del proveedor que emite la factura
    @NotNull
    private Long proveedorId;

    
}


