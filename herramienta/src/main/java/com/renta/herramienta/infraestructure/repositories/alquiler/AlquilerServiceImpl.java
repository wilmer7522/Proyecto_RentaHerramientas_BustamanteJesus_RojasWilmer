package com.renta.herramienta.infraestructure.repositories.alquiler;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.AlquilerService;
import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.entities.DetalleAlquiler;
import com.renta.herramienta.domain.entities.EstadoAlquiler;
import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Reserva;
import com.renta.herramienta.domain.request.AlquilerRequest;
import com.renta.herramienta.infraestructure.repositories.reserva.ReservaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final ReservaRepository reservaRepository;
    

    public AlquilerServiceImpl(AlquilerRepository alquilerRepository, ReservaRepository reservaRepository) {
        this.alquilerRepository = alquilerRepository;
        this.reservaRepository = reservaRepository;
        
    }

    @Override
    public Alquiler crearAlquilerDesdeReserva(AlquilerRequest request) {
        Reserva reserva = reservaRepository.findById(request.getIdReserva())
            .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));

        if (reserva.getEstadoReserva() != EstadoReserva.ACEPTADA) {
            throw new IllegalStateException("La reserva no está aceptada y no puede convertirse en alquiler");
        }

        Alquiler alquiler = new Alquiler();
        alquiler.setReserva(reserva);
        alquiler.setCliente(reserva.getCliente());
        alquiler.setFecha_alquiler(reserva.getFecha_inicio());
        alquiler.setFecha_devolucion(reserva.getFecha_fin());
        alquiler.setEstadoAlquiler(EstadoAlquiler.ACTIVO);

        // Mapear herramientas desde la reserva a detalles de alquiler
        List<DetalleAlquiler> detalles = reserva.getHerramientas().stream().map(h -> {
            DetalleAlquiler detalle = new DetalleAlquiler();
            detalle.setHerramienta(h);
            detalle.setCantidad_dias(
                (int) ChronoUnit.DAYS.between(reserva.getFecha_inicio(), reserva.getFecha_fin())
            );
            detalle.setPrecio_unitario(h.getCosto_dia()); // Asegúrate que Herramienta tenga precio
            detalle.setAlquiler(alquiler);
            detalle.setSubtotal(detalle.getCantidad_dias() * detalle.getPrecio_unitario());
            return detalle;
        }).collect(Collectors.toList());

        alquiler.setDetalle(detalles);

        return alquilerRepository.save(alquiler);
    }

    @Override
    public Alquiler getAlquilerById(Long id) {
        return alquilerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alquiler no encontrado"));
    }

    @Override
    public List<Alquiler> getAllAlquileres() {
        return alquilerRepository.findAll();
    }

    @Override
public List<Alquiler> getAlquileresPorCliente(Long clienteId) {
    return alquilerRepository.findByClienteId(clienteId);
}

   

}

