package com.renta.herramienta.infraestructure.controllers;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.HerramientaService;
import com.renta.herramienta.aplication.service.InventarioService;
import com.renta.herramienta.domain.dto.HerramientaDTO;
import com.renta.herramienta.domain.dto.HerramientaInventarioDTO;
import com.renta.herramienta.domain.dto.HerramientaInventarioResumenDTO;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.mapper.HerramientaMapper;
import com.renta.herramienta.domain.request.HerramientaInventarioRequest;



@RestController
@RequestMapping("/service")
public class InventarioController {

    private final HerramientaService herramientaService;
    private final HerramientaMapper herramientaMapper;
    private final InventarioService inventarioService;

    public InventarioController(HerramientaService herramientaService,
                               HerramientaMapper herramientaMapper,
                               InventarioService inventarioService) {
        this.herramientaService = herramientaService;
        this.herramientaMapper = herramientaMapper;
        this.inventarioService = inventarioService;
    }

    // Ver herramientas agrupadas por nombre con cantidades (total según inventario)
    @GetMapping("/inventario/resumen")
    public ResponseEntity<List<HerramientaInventarioResumenDTO>> obtenerResumenInventario() {
        List<HerramientaInventarioResumenDTO> resumen = inventarioService.obtenerCantidadTotalPorNombreHerramienta();
        return ResponseEntity.ok(resumen);
    }
    


    // Ver herramientas disponibles (solo id y nombre)
    @GetMapping("/inventario/disponibles")
    public ResponseEntity<List<HerramientaDTO>> obtenerHerramientasDisponibles() {
        List<Herramienta> disponibles = herramientaService.getHerramientasDisponibles();
        List<HerramientaDTO> dtos = disponibles.stream()
                .map(herramientaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ver todas las herramientas con detalles completos
    @GetMapping("/inventario/todas")
    public ResponseEntity<List<HerramientaInventarioDTO>> obtenerTodasHerramientasConDetalles() {
        List<Herramienta> herramientas = herramientaService.getAllHerramientas();
        List<HerramientaInventarioDTO> dtos = herramientas.stream()
                .map(herramientaMapper::toInventarioDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Agregar nueva herramienta al inventario
    @PostMapping("/inventario/agregar")
    public ResponseEntity<String> agregarHerramientaAlInventario(@RequestBody HerramientaInventarioRequest request) {
        Herramienta herramienta = herramientaMapper.toEntity(request.getHerramienta());
        Proveedor proveedor = new Proveedor();
        proveedor.setId(request.getId_proveedor());

        inventarioService.agregarHerramientaAlInventario(herramienta, proveedor, request.getCantidad());
        return ResponseEntity.ok("Herramienta agregada al inventario correctamente.");
    }
}
