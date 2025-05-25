package com.renta.herramienta.infraestructure.repositories.devolucion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.DevolucionService;
import com.renta.herramienta.aplication.service.HerramientaService;
import com.renta.herramienta.aplication.service.InventarioService;
import com.renta.herramienta.domain.dto.DevolucionDTO;
import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.entities.DetalleDevolucionPorHerramienta;
import com.renta.herramienta.domain.entities.Devolucion;
import com.renta.herramienta.domain.entities.EstadoAlquiler;
import com.renta.herramienta.domain.entities.EstadoHerramienta;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.ReporteDano;
import com.renta.herramienta.domain.entities.UnidadDevuelta;
import com.renta.herramienta.domain.mapper.DevolucionMapper;
import com.renta.herramienta.domain.request.DevolucionRequest;
import com.renta.herramienta.infraestructure.repositories.alquiler.AlquilerRepository;
import com.renta.herramienta.infraestructure.repositories.herramienta.HerramientaRepository;
import com.renta.herramienta.infraestructure.repositories.reporteDano.ReporteDanoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DevolucionServiceImpl implements DevolucionService {

    private final AlquilerRepository alquilerRepository;
    private final DevolucionRepository devolucionRepository;
    private final HerramientaRepository herramientaRepository;
    private final ReporteDanoRepository reporteDanoRepository;
    private final HerramientaService herramientaService;
    private final InventarioService inventarioService;

    public DevolucionServiceImpl(
            AlquilerRepository alquilerRepository,
            DevolucionRepository devolucionRepository,
            HerramientaRepository herramientaRepository,
            ReporteDanoRepository reporteDanoRepository,
            HerramientaService herramientaService,
            InventarioService inventarioService) {
        this.alquilerRepository = alquilerRepository;
        this.devolucionRepository = devolucionRepository;
        this.herramientaRepository = herramientaRepository;
        this.reporteDanoRepository = reporteDanoRepository;
        this.herramientaService = herramientaService;
        this.inventarioService = inventarioService;
    }

    @Override
    public void procesarDevolucion(DevolucionRequest request) {
        Alquiler alquiler = alquilerRepository.findById(request.getAlquilerId())
                .orElseThrow(() -> new EntityNotFoundException("Alquiler no encontrado"));

        Devolucion devolucion = new Devolucion();
        devolucion.setFechaDevolucion(LocalDate.now());
        devolucion.setObservaciones("Devolución generada automáticamente");
        devolucion.setAlquiler(alquiler);
        devolucion.setConfirmacionProveedor(false);

        devolucionRepository.save(devolucion);

        List<ReporteDano> reportes = new ArrayList<>();

        if (request.getHerramientas() != null) {
            for (DetalleDevolucionPorHerramienta detalleDevuelto : request.getHerramientas()) {
                Herramienta herramienta = herramientaRepository.findById(detalleDevuelto.getHerramientaId())
                        .orElseThrow(() -> new EntityNotFoundException("Herramienta no encontrada"));

                for (UnidadDevuelta unidad : detalleDevuelto.getDevoluciones()) {
                    boolean estaDanada = unidad.isDanada();

                    if (estaDanada) {
                        herramienta.setEstadoHerramienta(EstadoHerramienta.DAÑADA);

                        ReporteDano reporte = new ReporteDano();
                        reporte.setHerramienta(herramienta);
                        reporte.setDevolucion(devolucion);
                        reporte.setFechaReporte(LocalDate.now());
                        reporte.setDescripcion(unidad.getDescripcion() != null
                                ? unidad.getDescripcion()
                                : "Herramienta dañada sin descripción");

                        reportes.add(reporte);
                    } else {
                        herramienta.setEstadoHerramienta(EstadoHerramienta.BUENA);
                    }

                    herramientaService.actualizarDisponibilidadPorEstado(herramienta);
                    herramientaRepository.save(herramienta);
                    inventarioService.actualizarInventarioPorDevolucion(herramienta, estaDanada);
                }
            }
        }

        reporteDanoRepository.saveAll(reportes);
        devolucion.getReportesDano().clear();
        devolucion.getReportesDano().addAll(reportes);
        devolucionRepository.save(devolucion);

        alquiler.setEstadoAlquiler(EstadoAlquiler.FINALIZADO);
        alquilerRepository.save(alquiler);
    }

    @Override
    public DevolucionDTO obtenerDevolucionConReportes(Long id) {
        Devolucion devolucion = devolucionRepository.findByIdWithReportes(id)
                .orElseThrow(() -> new EntityNotFoundException("Devolución no encontrada"));
        return DevolucionMapper.toDTO(devolucion);
    }
}
