package com.renta.herramienta.infraestructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.ProveedorService;
import com.renta.herramienta.domain.dto.ProveedorDTO;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.request.ProveedorRequest;
import com.renta.herramienta.domain.request.ProveedorUpdateRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<ProveedorDTO>> findAllProveedores(
            @RequestParam(name = "filter", defaultValue = "") String filter,
            @RequestParam(name = "value", defaultValue = "") String value) {

        List<ProveedorDTO> results = proveedorService.findAllProveedoresDTOByFilter(filter, value);

        return ResponseEntity.ok(results);
    }

    @PostMapping("/suppliers")
    @ResponseStatus(HttpStatus.CREATED)
    public ProveedorDTO crearProveedor(@Valid @RequestBody ProveedorRequest request) {
        return proveedorService.createNewProveedorDTO(request); 
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<?> removeProveedor(@PathVariable Long id) {
        try {
            Proveedor eliminado = proveedorService.removeProveedor(id);
            return ResponseEntity.ok(eliminado);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error interno: " + ex.getMessage());
        }
    }

    @PatchMapping("/suppliers/{id}")
    public ResponseEntity<Proveedor> partiallyUpdate(
            @PathVariable Long id,
            @RequestBody ProveedorUpdateRequest updates) {
        Proveedor actualizado = proveedorService.update(id, updates);
        return ResponseEntity.ok(actualizado);
    }

}
