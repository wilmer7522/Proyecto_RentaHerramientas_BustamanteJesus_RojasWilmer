package com.renta.herramienta.domain.mapper;

import com.renta.herramienta.domain.dto.ReporteDanoDTO;
import com.renta.herramienta.domain.entities.ReporteDano;

public class ReporteDanoMapper {

    public static ReporteDanoDTO toDTO(ReporteDano reporte) {
        if (reporte == null) {
            return null;
        }

        ReporteDanoDTO dto = new ReporteDanoDTO();
        dto.setId(reporte.getId());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setFechaReporte(reporte.getFechaReporte());

        if (reporte.getHerramienta() != null) {
            dto.setHerramientaId(reporte.getHerramienta().getId());
            dto.setHerramientaNombre(reporte.getHerramienta().getNombre());
        }

        return dto;
    }
}

