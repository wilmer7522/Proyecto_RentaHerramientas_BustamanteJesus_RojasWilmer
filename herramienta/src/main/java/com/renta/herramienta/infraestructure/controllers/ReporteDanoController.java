package com.renta.herramienta.infraestructure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.domain.dto.ReporteDanoDTO;
import com.renta.herramienta.infraestructure.repositories.reporteDano.ReporteDanoRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/service/reportes-dano")
@RequiredArgsConstructor
public class ReporteDanoController {

    private final ReporteDanoRepository reporteDanoRepository;

    @GetMapping
    public List<ReporteDanoDTO> obtenerTodos() {
        return reporteDanoRepository.findAll().stream()
                .map(reporte -> new ReporteDanoDTO(
                        reporte.getId(),
                        reporte.getDescripcion(),
                        reporte.getFechaReporte(),
                        reporte.getHerramienta().getId(),
                        reporte.getHerramienta().getNombre()
                ))
                .collect(Collectors.toList());
    }
}

