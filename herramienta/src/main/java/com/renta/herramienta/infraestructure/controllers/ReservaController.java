package com.renta.herramienta.infraestructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.ReservaService;
import com.renta.herramienta.domain.dto.ReservaRequest;
import com.renta.herramienta.domain.entities.Reserva;

@RestController
@RequestMapping("/service")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // 1. Crear una reserva (el cliente)
    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva createNewReserva(@RequestBody ReservaRequest request) {
        return reservaService.createNewReserva(request);
    }

    // 2. Ver todas las reservas de un cliente
    @GetMapping("/all/reserve/cliente/{id}")
    public List<Reserva> listarReservasPorCliente(@PathVariable Long id) {
        return reservaService.getReservasByCliente(id);
    }

    // 3. Ver detalles de una reserva específica
    @GetMapping("/reserve/{id}")
    public Reserva obtenerDetalleReserva(@PathVariable Long id) {
        return reservaService.getReservaById(id);
    }
}

