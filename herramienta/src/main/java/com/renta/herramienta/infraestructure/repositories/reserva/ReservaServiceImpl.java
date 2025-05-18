package com.renta.herramienta.infraestructure.repositories.reserva;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.ReservaService;
import com.renta.herramienta.domain.dto.ReservaRequest;
import com.renta.herramienta.domain.entities.Cliente;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Reserva;
import com.renta.herramienta.infraestructure.repositories.cliente.ClienteRepository;
import com.renta.herramienta.infraestructure.repositories.herramienta.HerramientaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
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

        List<Herramienta> herramientas = herramientaRepository.findAllById(reservaRequest.getId_herramientas());

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setHerramientas(herramientas);
        reserva.setFecha_reserva(LocalDateTime.now()); // o LocalDateTime.now()
        reserva.setFecha_inicio(reservaRequest.getFechaInicio());
        reserva.setFecha_fin(reservaRequest.getFechaFin());
        reserva.setEstado_reserva(reservaRequest.getEstadoReserva()); // o Estado_Reserva.PENDIENTE

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

}
