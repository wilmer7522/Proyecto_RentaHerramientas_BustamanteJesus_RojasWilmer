package com.renta.herramienta.infraestructure.repositories.alquiler;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.AlquilerService;
import com.renta.herramienta.aplication.service.InventarioService;
import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.entities.DetalleAlquiler;
import com.renta.herramienta.domain.entities.DetalleReserva;
import com.renta.herramienta.domain.entities.EstadoAlquiler;
import com.renta.herramienta.domain.entities.EstadoHerramienta;
import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Inventario;
import com.renta.herramienta.domain.entities.Reserva;
import com.renta.herramienta.domain.request.AlquilerRequest;
import com.renta.herramienta.infraestructure.repositories.herramienta.HerramientaRepository;
import com.renta.herramienta.infraestructure.repositories.reserva.ReservaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final ReservaRepository reservaRepository;
    private final HerramientaRepository herramientaRepository;
    private final InventarioService inventarioService; 

    public AlquilerServiceImpl(
            AlquilerRepository alquilerRepository,
            ReservaRepository reservaRepository,
            HerramientaRepository herramientaRepository,
            InventarioService inventarioService) {
        this.alquilerRepository = alquilerRepository;
        this.reservaRepository = reservaRepository;
        this.herramientaRepository = herramientaRepository;
        this.inventarioService = inventarioService;
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
        alquiler.setFecha_alquiler(reserva.getFechaInicio());
        alquiler.setFecha_devolucion(reserva.getFechaFin());
        alquiler.setEstadoAlquiler(EstadoAlquiler.ACTIVO);

        List<DetalleAlquiler> detalles = new ArrayList<>();

        for (DetalleReserva detalleReserva : reserva.getDetalleReserva()) {
            Herramienta h = detalleReserva.getHerramienta();
            int cantidadSolicitada = detalleReserva.getCantidad();

            // Validar disponibilidad antes de descontar
            Optional<Inventario> inventarioOpt = inventarioService
                    .obtenerInventarioPorHerramienta(h);

            if (inventarioOpt.isEmpty() ||
                inventarioOpt.get().getCantidadDisponible() < cantidadSolicitada) {
                throw new IllegalStateException("No hay suficientes unidades disponibles para la herramienta: " + h.getNombre());
            }

            // Descontar inventario usando el servicio
            for (int i = 0; i < cantidadSolicitada; i++) {
                inventarioService.descontarInventarioPorAlquiler(h);
            }

            // Cambiar estado de la herramienta
            h.setEstadoHerramienta(EstadoHerramienta.ALQUILADA);
            h.setDisponible(false);
            herramientaRepository.save(h);

            // Crear detalle de alquiler
            DetalleAlquiler detalle = new DetalleAlquiler();
            detalle.setHerramienta(h);
            detalle.setCantidad_dias((int) ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin()));
            detalle.setPrecio_unitario(h.getCosto_dia());
            detalle.setAlquiler(alquiler);
            detalle.setCantidad(cantidadSolicitada);
            detalle.setSubtotal(detalle.getCantidad_dias() * detalle.getPrecio_unitario() * cantidadSolicitada);

            detalles.add(detalle);
        }

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

