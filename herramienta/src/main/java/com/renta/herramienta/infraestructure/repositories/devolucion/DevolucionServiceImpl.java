package com.renta.herramienta.infraestructure.repositories.devolucion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.DevolucionService;
import com.renta.herramienta.domain.dto.DevolucionDTO;
import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.entities.DetalleAlquiler;
import com.renta.herramienta.domain.entities.DetalleDevolucion;
import com.renta.herramienta.domain.entities.Devolucion;
import com.renta.herramienta.domain.entities.EstadoAlquiler;
import com.renta.herramienta.domain.entities.EstadoHerramienta;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.ReporteDano;
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

    public DevolucionServiceImpl(
        AlquilerRepository alquilerRepository,
        DevolucionRepository devolucionRepository,
        HerramientaRepository herramientaRepository,
        ReporteDanoRepository reporteDanoRepository
    ) {
        this.alquilerRepository = alquilerRepository;
        this.devolucionRepository = devolucionRepository;
        this.herramientaRepository = herramientaRepository;
        this.reporteDanoRepository = reporteDanoRepository;
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
    
        List<ReporteDano> reportes = new ArrayList<>();
    
        for (DetalleAlquiler detalle : alquiler.getDetalle()) {
            Herramienta herramienta = detalle.getHerramienta();
    
            DetalleDevolucion detalleDevuelta = request.getHerramientasDevueltas().get(herramienta.getId());
            if (detalleDevuelta != null && detalleDevuelta.isDanada()) {
                herramienta.setEstadoHerramienta(EstadoHerramienta.DAÑADA);
    
                ReporteDano reporte = new ReporteDano();
                reporte.setHerramienta(herramienta);
                reporte.setDevolucion(devolucion);
                reporte.setFechaReporte(LocalDate.now());
    
                // Aquí usamos la descripción enviada
                reporte.setDescripcion(detalleDevuelta.getDescripcion() != null 
                    ? detalleDevuelta.getDescripcion() 
                    : "Herramienta dañada sin descripción");
    
                reportes.add(reporte);
            } else {
                herramienta.setEstadoHerramienta(EstadoHerramienta.BUENA);
            }
    
            herramientaRepository.save(herramienta);
        }
    
        // Asignar la lista de reportes a la devolución para que persista la relación
        devolucion.setReportesDano(reportes);
    
        devolucionRepository.save(devolucion);
        reporteDanoRepository.saveAll(reportes);
    
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



