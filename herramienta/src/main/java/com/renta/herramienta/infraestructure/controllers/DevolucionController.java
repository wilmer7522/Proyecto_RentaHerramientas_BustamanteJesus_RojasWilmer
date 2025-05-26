package com.renta.herramienta.infraestructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.DevolucionService;
import com.renta.herramienta.domain.dto.DevolucionDTO;
import com.renta.herramienta.domain.request.DevolucionRequest;

@RestController
@RequestMapping("/service")
public class DevolucionController {

    private final DevolucionService devolucionService;

    public DevolucionController(DevolucionService devolucionService) {
        this.devolucionService = devolucionService;
    }

    @PatchMapping("/devolucion")
    public ResponseEntity<String> procesarDevolucion(@RequestBody DevolucionRequest request) {
        devolucionService.procesarDevolucion(request);
        return ResponseEntity.ok("Devolución y reporte de daños procesados correctamente.");
    }

    @GetMapping("/devolucion/{id}")
    public ResponseEntity<DevolucionDTO> obtenerDevolucion(@PathVariable Long id) {
        DevolucionDTO dto = devolucionService.obtenerDevolucionConReportes(id);
        return ResponseEntity.ok(dto);
    }
}



