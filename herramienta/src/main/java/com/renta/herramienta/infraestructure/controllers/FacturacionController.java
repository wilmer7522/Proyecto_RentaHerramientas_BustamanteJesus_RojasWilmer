package com.renta.herramienta.infraestructure.controllers;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.FacturaPdfGenerator;
import com.renta.herramienta.aplication.service.FacturacionService;
import com.renta.herramienta.domain.dto.FacturacionDTO;
import com.renta.herramienta.domain.entities.Facturacion;
import com.renta.herramienta.domain.mapper.FacturacionMapper;
import com.renta.herramienta.domain.request.FacturacionRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/service")
public class FacturacionController {

    private final FacturacionService facturacionService;

    public FacturacionController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }

    @PostMapping("/factura")
    public ResponseEntity<FacturacionDTO> crearFactura(@RequestBody @Valid FacturacionRequest request) {
        FacturacionDTO facturaCreada = facturacionService.crearFactura(request);
        return new ResponseEntity<>(facturaCreada, HttpStatus.CREATED);
    }

    @GetMapping("/factura/{id}")
    public ResponseEntity<FacturacionDTO> obtenerFactura(@PathVariable Long id) {
        FacturacionDTO factura = facturacionService.obtenerFacturaPorId(id);
        return ResponseEntity.ok(factura);
    }

    @GetMapping("/factura")
    public ResponseEntity<List<FacturacionDTO>> listarFacturas() {
        List<FacturacionDTO> facturas = facturacionService.listarFacturas();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/factura/{id}/pdf")
    public ResponseEntity<byte[]> descargarFacturaPdf(@PathVariable Long id) {
        Facturacion facturacion = facturacionService.obtenerEntidadPorId(id);

        // Mapear a DTO para incluir cliente, herramientas, etc.
        FacturacionDTO dto = FacturacionMapper.toDTO(facturacion);

        ByteArrayOutputStream pdfStream = FacturaPdfGenerator.generarFacturaPdf(dto);

        byte[] pdfBytes = pdfStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura_" + dto.getNumeroFactura() + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
