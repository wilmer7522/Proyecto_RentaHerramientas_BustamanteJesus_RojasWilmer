package com.renta.herramienta.infraestructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.PagoService;
import com.renta.herramienta.domain.dto.PagoDTO;
import com.renta.herramienta.domain.entities.EstadoPago;

import com.renta.herramienta.domain.request.PagoRequest;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/service")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/pago")
    public ResponseEntity<List<PagoDTO>> listarPagos() {
        List<PagoDTO> pagos = pagoService.listarPagos();
        return ResponseEntity.ok(pagos);
    }

    @PostMapping("/pago")
    public ResponseEntity<PagoDTO> crearPago(@RequestBody @Valid PagoRequest pagoRequest) {
        PagoDTO pagoDTO = pagoService.registrarPago(pagoRequest);
        return new ResponseEntity<>(pagoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/pago/pendientes")
    public ResponseEntity<List<PagoDTO>> obtenerPagosPendientes() {
        List<PagoDTO> pagosPendientes = pagoService.obtenerPagosPendientes();
        return ResponseEntity.ok(pagosPendientes);
    }

    @GetMapping("/pago/{id}")
    public ResponseEntity<PagoDTO> obtenerPago(@PathVariable Long id) {
        PagoDTO pagoDTO = pagoService.obtenerPagoPorId(id);
        return ResponseEntity.ok(pagoDTO);

    }

    @PatchMapping("/pago/{id}/confirmar")
    public ResponseEntity<PagoDTO> confirmarPago(@PathVariable Long id) {
        PagoDTO pagoConfirmado = pagoService.confirmarPago(id);
        return ResponseEntity.ok(pagoConfirmado);
    }

    @PatchMapping("/pago/{id}/rechazar")
    public ResponseEntity<PagoDTO> rechazarPago(@PathVariable Long id) {
        PagoDTO resultado = pagoService.actualizarEstadoPago(id, EstadoPago.FALLIDO);
        return ResponseEntity.ok(resultado);
    }

}
