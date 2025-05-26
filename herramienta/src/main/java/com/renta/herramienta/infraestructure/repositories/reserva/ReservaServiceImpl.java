package com.renta.herramienta.infraestructure.repositories.reserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.ReservaService;
import com.renta.herramienta.domain.dto.HerramientaCantidadDTO;
import com.renta.herramienta.domain.entities.Cliente;
import com.renta.herramienta.domain.entities.DetalleReserva;
import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Reserva;
import com.renta.herramienta.domain.request.ReservaRequest;
import com.renta.herramienta.infraestructure.repositories.cliente.ClienteRepository;
import com.renta.herramienta.infraestructure.repositories.herramienta.HerramientaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaServiceImpl implements ReservaService {

    final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final HerramientaRepository herramientaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
            ClienteRepository clienteRepository,
            HerramientaRepository herramientaRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    public Reserva createNewReserva(ReservaRequest reservaRequest) {
        Cliente cliente = clienteRepository.findById(reservaRequest.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Extraer IDs de las herramientas
        List<Long> herramientaIds = reservaRequest.getHerramientas().stream()
                .map(HerramientaCantidadDTO::getId)
                .collect(Collectors.toList());

        // Obtener las herramientas desde la BD
        List<Herramienta> herramientas = herramientaRepository.findAllById(herramientaIds);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setFechaInicio(reservaRequest.getFechaInicio());
        reserva.setFechaFin(reservaRequest.getFechaFin());
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);

        // Crear detalles de reserva con cantidad
        List<DetalleReserva> detalles = reservaRequest.getHerramientas().stream().map(hcDTO -> {
            Herramienta herramienta = herramientas.stream()
                    .filter(h -> h.getId().equals(hcDTO.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Herramienta no encontrada con ID: " + hcDTO.getId()));

            DetalleReserva detalle = new DetalleReserva();
            detalle.setHerramienta(herramienta);
            detalle.setReserva(reserva);
            detalle.setCantidad(hcDTO.getCantidad());
            return detalle;
        }).collect(Collectors.toList());

        reserva.setDetalleReserva(detalles);

        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> getReservasByCliente(Long id_cliente) {
        return reservaRepository.findByClienteId(id_cliente);
    }

    @Override
    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    public List<Reserva> getReservasPendientes() {
        return reservaRepository.findByEstadoReserva(EstadoReserva.PENDIENTE);
    }

    @Override
    public void actualizarEstado(Long id, EstadoReserva nuevoEstado) {
        // 1. Buscar la reserva por ID
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + id));

        // 2. Cambiar el estado
        reserva.setEstadoReserva(nuevoEstado);

        // 3. Guardar la reserva actualizada
        reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> getAllReservasPendientes() {
        return reservaRepository.findAll();
    }

}
