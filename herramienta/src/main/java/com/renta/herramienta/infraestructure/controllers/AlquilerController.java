package com.renta.herramienta.infraestructure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.AlquilerService;
import com.renta.herramienta.domain.dto.AlquilerDTO;
import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.mapper.AlquilerMapper;
import com.renta.herramienta.domain.request.AlquilerRequest;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/service")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    // 1. Crear un nuevo alquiler
    @PostMapping("/alquiler")
    @ResponseStatus(HttpStatus.CREATED)
    public AlquilerDTO crearAlquilerDesdeReserva(@RequestBody AlquilerRequest request) {
        Alquiler alquiler = alquilerService.crearAlquilerDesdeReserva(request);
        return AlquilerMapper.toDTO(alquiler);
    }

    // 2. Obtener todos los alquileres
    @GetMapping("/alquileres")
    public List<AlquilerDTO> listarAlquileres(@RequestParam(required = false) Long clienteId) {
        List<Alquiler> alquileres = (clienteId != null)
                ? alquilerService.getAlquileresPorCliente(clienteId) // "/alquileres?clienteId={id} opcion para buscar por cliente"
                : alquilerService.getAllAlquileres();

        return alquileres.stream()
                .map(AlquilerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 3. Obtener detalles de un alquiler específico
    @GetMapping("/alquiler/{id}")
    public ResponseEntity<AlquilerDTO> obtenerAlquiler(@PathVariable Long id) {
        Alquiler alquiler = alquilerService.getAlquilerById(id);
        return ResponseEntity.ok(AlquilerMapper.toDTO(alquiler));
    }


}

