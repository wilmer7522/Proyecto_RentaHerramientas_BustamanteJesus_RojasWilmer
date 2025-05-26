package com.renta.herramienta.domain.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



import com.renta.herramienta.domain.dto.DevolucionDTO;
import com.renta.herramienta.domain.dto.ReporteDanoDTO;
import com.renta.herramienta.domain.entities.Devolucion;



public class DevolucionMapper {

    public static DevolucionDTO toDTO(Devolucion devolucion) {
        if (devolucion == null) {
            return null;
        }

        DevolucionDTO dto = new DevolucionDTO();
        dto.setId(devolucion.getId());
        dto.setFechaDevolucion(devolucion.getFechaDevolucion());
        dto.setObservaciones(devolucion.getObservaciones());
        dto.setConfirmacionProveedor(devolucion.isConfirmacionProveedor());

        List<ReporteDanoDTO> reportes = devolucion.getReportesDano() != null
                ? devolucion.getReportesDano().stream()
                        .map(ReporteDanoMapper::toDTO)
                        .collect(Collectors.toList())
                : Collections.emptyList();

        dto.setReportesDano(reportes);
        return dto;
    }
}


