package com.renta.herramienta.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDanoDTO {
    private Long id;
    private String descripcion;
    private LocalDate fechaReporte;
    private Long herramientaId;
    private String herramientaNombre;
}

/*
  {
  "id": 1,
  "descripcion": "Herramienta dañada al devolver",
  "fechaReporte": "2025-05-21",
  "herramientaId": 2,
  "herramientaNombre": "Taladro Bosch"
}
 */

