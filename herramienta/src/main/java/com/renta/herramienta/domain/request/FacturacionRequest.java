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

    
    @NotNull
    private double total;

    
    @NotNull
    private Long pagoId;

    
    @NotNull
    private Long proveedorId;

    
}


