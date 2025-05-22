package com.renta.herramienta.infraestructure.repositories.pago;

import java.time.LocalDate;


import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.PagoService;
import com.renta.herramienta.domain.dto.PagoDTO;
import com.renta.herramienta.domain.entities.EstadoPago;
import com.renta.herramienta.domain.entities.Pago;
import com.renta.herramienta.domain.mapper.PagoMapper;
import com.renta.herramienta.domain.request.PagoRequest;
import com.renta.herramienta.infraestructure.repositories.alquiler.AlquilerRepository;
import com.renta.herramienta.infraestructure.utils.exceptions.ResourceNotFoundException;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final AlquilerRepository alquilerRepository;

    public PagoServiceImpl(PagoRepository pagoRepository, AlquilerRepository alquilerRepository) {
        this.pagoRepository = pagoRepository;
        this.alquilerRepository = alquilerRepository;
    }

    @Override
    public PagoDTO registrarPago(PagoRequest request) {
        var alquilerOpt = alquilerRepository.findById(request.getAlquilerId());
        if (alquilerOpt.isEmpty()) {
            throw new RuntimeException("Alquiler no encontrado con id: " + request.getAlquilerId());
        }

        var alquiler = alquilerOpt.get();

        // Opcional: validar si ya existe un pago para este alquiler
        if (pagoRepository.existsByAlquiler(alquiler)) {
            throw new RuntimeException("Ya existe un pago registrado para este alquiler");
        }

        Pago pago = new Pago();
        pago.setAlquiler(alquiler);
        pago.setMonto(request.getMonto());
        pago.setMetodoPago(request.getMetodoPago());
        pago.setEstadoPago(EstadoPago.PENDIENTE);
        pago.setFechaPago(LocalDate.now());

        Pago pagoGuardado = pagoRepository.save(pago);
        return PagoMapper.toDTO(pagoGuardado);
    }

    @Override
    public PagoDTO obtenerPagoPorId(Long id) {
        var pagoOpt = pagoRepository.findById(id);
        if (pagoOpt.isEmpty()) {
            throw new RuntimeException("Pago no encontrado con id: " + id);
        }
        return PagoMapper.toDTO(pagoOpt.get());
    }

    //Confirmar pago
    @Override
public PagoDTO confirmarPago(Long id) {
    Pago pago = pagoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + id));

    if (pago.getEstadoPago() != EstadoPago.PENDIENTE) {
        throw new IllegalStateException("El pago ya fue procesado o falló.");
    }

    pago.setEstadoPago(EstadoPago.COMPLETADO);
    pagoRepository.save(pago);

    return PagoMapper.toDTO(pago);
}


@Override
public PagoDTO actualizarEstadoPago(Long id, EstadoPago nuevoEstado) {
    Pago pago = pagoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + id));

    if (pago.getEstadoPago() != EstadoPago.PENDIENTE) {
        throw new IllegalStateException("El estado del pago ya fue procesado.");
    }

    pago.setEstadoPago(nuevoEstado);
    pagoRepository.save(pago);

    return PagoMapper.toDTO(pago);
}



}

