package com.renta.herramienta.domain.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturacionDTO {

    private Long id;
    private LocalDate fechaEmision;
    private double total;
    private String numeroFactura;

    private Long pagoId;
    private ProveedorDTO proveedor;

    private String nombreCliente;
    private List<DetalleFacturaDTO> detalles;
}


