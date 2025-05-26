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
public class DevolucionDTO {
    private Long id;
    private LocalDate fechaDevolucion;
    private String observaciones;
    private boolean confirmacionProveedor;
    private List<ReporteDanoDTO> reportesDano;
}

