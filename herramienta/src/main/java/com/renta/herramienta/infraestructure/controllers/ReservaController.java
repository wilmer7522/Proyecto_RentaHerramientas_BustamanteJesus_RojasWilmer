package com.renta.herramienta.infraestructure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.ReservaService;
import com.renta.herramienta.domain.dto.ReservaDTO;
import com.renta.herramienta.domain.entities.Reserva;
import com.renta.herramienta.domain.mapper.ReservaMapper;
import com.renta.herramienta.domain.request.EstadoReservaRequest;
import com.renta.herramienta.domain.request.ReservaRequest;

@RestController
@RequestMapping("/service")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    //Crear una reserva
    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO createNewReserva(@RequestBody ReservaRequest request) {
        Reserva reserva = reservaService.createNewReserva(request);
        return ReservaMapper.toReservaDTO(reserva);
    }

    //Ver todas las reservas de un cliente
    @GetMapping("/all/reserve/cliente/{id}")
    public List<ReservaDTO> listarReservasPorCliente(@PathVariable Long id) {
        List<Reserva> reservas = reservaService.getReservasByCliente(id);
        return reservas.stream()
                .map(ReservaMapper::toReservaDTO)
                .collect(Collectors.toList());
    }

    //ver Reserva por ID
    @GetMapping("/reserve/{id}")
    public ResponseEntity<ReservaDTO> getReserva(@PathVariable Long id) {
        Reserva reserva = reservaService.getReservaById(id);
        ReservaDTO dto = ReservaMapper.toReservaDTO(reserva);
        return ResponseEntity.ok(dto);
    }

    //Reservas Pendientes
    @GetMapping("/reserves/pendientes")
public List<ReservaDTO> listarReservasPendientes() {
    List<Reserva> reservas = reservaService.getReservasPendientes();
    return reservas.stream()
            .map(ReservaMapper::toReservaDTO)
            .collect(Collectors.toList());
}



    //Todas las reservas
    @GetMapping("/reserves/all")
    public ResponseEntity<List<ReservaDTO>> getAllReservasPendientes() {
        List<Reserva> reservas = reservaService.getAllReservasPendientes();
        List<ReservaDTO> dtos = reservas.stream()
                .map(ReservaMapper::toReservaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    
    //Actualizar Reservas
    @PatchMapping("/reserves/{id}/estado")
public ResponseEntity<String> actualizarEstadoReserva(
        @PathVariable Long id,
        @RequestBody EstadoReservaRequest estadoRequest
) {
    reservaService.actualizarEstado(id, estadoRequest.getEstado());
    return ResponseEntity.ok("Estado actualizado correctamente");
}


}
