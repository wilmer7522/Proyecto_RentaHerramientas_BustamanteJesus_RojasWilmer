package com.renta.herramienta.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.HerramientaService;
import com.renta.herramienta.domain.entities.EstadoHerramienta;
import com.renta.herramienta.domain.entities.Herramienta;

@CrossOrigin(origins = "http://localhost:5173") // Puerto de Vite
@RestController
@RequestMapping(value = "/tools", produces = MediaType.APPLICATION_JSON_VALUE)
public class HerramientaController {

    @Autowired
    private HerramientaService herramientaService;

    // Crear nueva herramienta
    @PostMapping
    public ResponseEntity<Herramienta> createHerramienta(@RequestBody Herramienta herramienta) {
        Herramienta nuevaHerramienta = herramientaService.createHerramienta(herramienta);
        return new ResponseEntity<>(nuevaHerramienta, HttpStatus.CREATED);
    }

    // Obtener todas las herramientas
    @GetMapping
    public ResponseEntity<List<Herramienta>> getAllHerramientas() {
        List<Herramienta> herramientas = herramientaService.getAllHerramientas();
        return new ResponseEntity<>(herramientas, HttpStatus.OK);
    }

    // Obtener herramienta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Herramienta> getHerramientaById(@PathVariable Long id) {
        Herramienta herramienta = herramientaService.getHerramientaById(id);
        return new ResponseEntity<>(herramienta, HttpStatus.OK);
    }

    // Actualizar herramienta
    @PutMapping("/{id}")
    public ResponseEntity<Herramienta> updateHerramienta(@PathVariable Long id,
            @RequestBody Herramienta herramientaDetails) {
        Herramienta updatedHerramienta = herramientaService.updateHerramienta(id, herramientaDetails);
        return new ResponseEntity<>(updatedHerramienta, HttpStatus.OK);
    }

    // Eliminar herramienta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHerramienta(@PathVariable Long id) {
        herramientaService.deleteHerramienta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener herramientas por categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Herramienta>> getHerramientasByCategoria(@PathVariable Long categoriaId) {
        List<Herramienta> herramientas = herramientaService.getHerramientasByCategoria(categoriaId);
        return ResponseEntity.ok(herramientas);
    }

    // Obtener herramientas disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Herramienta>> getHerramientasDisponibles() {
        List<Herramienta> herramientas = herramientaService.getHerramientasDisponibles();
        return ResponseEntity.ok(herramientas);
    }

    // Buscar herramientas por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Herramienta>> searchHerramientas(@RequestParam String nombre) {
        List<Herramienta> herramientas = herramientaService.searchHerramientasByNombre(nombre);
        return ResponseEntity.ok(herramientas);
    }

    // Obtener herramientas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Herramienta>> getHerramientasByEstado(@PathVariable String estado) {
        EstadoHerramienta estadoEnum;
        try {
            estadoEnum = EstadoHerramienta.valueOf(estado.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        List<Herramienta> herramientas = herramientaService.getHerramientasByEstado(estadoEnum);
        return ResponseEntity.ok(herramientas);
    }

}
