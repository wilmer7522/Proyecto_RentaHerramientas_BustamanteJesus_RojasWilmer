package com.renta.herramienta.aplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renta.herramienta.domain.entities.CategoriaHerramienta;
import com.renta.herramienta.domain.entities.EstadoAlquiler;
import com.renta.herramienta.domain.entities.EstadoHerramienta;
import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.infraestructure.repositories.alquiler.AlquilerRepository;
import com.renta.herramienta.infraestructure.repositories.herramienta.CategoriaHerramientaRepository;
import com.renta.herramienta.infraestructure.repositories.herramienta.HerramientaRepository;
import com.renta.herramienta.infraestructure.utils.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HerramientaService {

    @Autowired
    private HerramientaRepository herramientaRepository;

    @Autowired
    private CategoriaHerramientaRepository categoriaRepository;

    @Autowired
    private AlquilerRepository alquilerRepository;

    public Herramienta createHerramienta(Herramienta herramienta) {
        

        CategoriaHerramienta categoria = categoriaRepository.findById(herramienta.getCategoriaHerramienta().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con id: " + herramienta.getCategoriaHerramienta().getId()));

        herramienta.setCategoriaHerramienta(categoria);

        if (herramienta.getEstadoHerramienta() == null) {
            herramienta.setEstadoHerramienta(EstadoHerramienta.BUENA);
        }

        actualizarDisponibilidadPorEstado(herramienta);

        return herramientaRepository.save(herramienta);
    }

    public List<Herramienta> getAllHerramientas() {
        return herramientaRepository.findAll();
    }

    public Herramienta getHerramientaById(Long id) {
        return herramientaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Herramienta no encontrada con id: " + id));
    }

    public List<Herramienta> getHerramientasByCategoria(Long categoriaId) {
        return herramientaRepository.findByCategoriaHerramientaId(categoriaId);
    }

    public List<Herramienta> getHerramientasDisponibles() {
        return herramientaRepository.findByDisponibleTrue();
    }

    public List<Herramienta> searchHerramientasByNombre(String nombre) {
        return herramientaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Herramienta updateHerramienta(Long id, Herramienta herramientaDetails) {
        Herramienta herramienta = getHerramientaById(id);

       

        herramienta.setNombre(herramientaDetails.getNombre());
        herramienta.setDescripcion(herramientaDetails.getDescripcion());
        herramienta.setCosto_dia(herramientaDetails.getCosto_dia());
        herramienta.setEstadoHerramienta(herramientaDetails.getEstadoHerramienta());
        actualizarDisponibilidadPorEstado(herramienta);

        if (herramientaDetails.getCategoriaHerramienta() != null &&
                !herramientaDetails.getCategoriaHerramienta().getId()
                        .equals(herramienta.getCategoriaHerramienta().getId())) {
            CategoriaHerramienta nuevaCategoria = categoriaRepository
                    .findById(herramientaDetails.getCategoriaHerramienta().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Categoría no encontrada con id: " + herramientaDetails.getCategoriaHerramienta().getId()));
            herramienta.setCategoriaHerramienta(nuevaCategoria);
        }

        return herramientaRepository.save(herramienta);
    }

    public void deleteHerramienta(Long id) {
        Herramienta herramienta = getHerramientaById(id);

        List<Herramienta> herramientasConReservas = herramientaRepository
                .findHerramientasConReservasActivas(EstadoReserva.ACEPTADA);
        ;
        if (herramientasConReservas.contains(herramienta)) {
            throw new IllegalStateException("No se puede eliminar la herramienta porque tiene reservas activas");
        }

        herramientaRepository.delete(herramienta);
    }

    public List<Herramienta> getHerramientasByEstado(EstadoHerramienta estado) {
        return herramientaRepository.findByEstadoHerramienta(estado);
    }

    public void actualizarDisponibilidadPorEstado(Herramienta herramienta) {
        EstadoHerramienta estado = herramienta.getEstadoHerramienta();
    
        boolean alquiladaActualmente = alquilerRepository
                .existsByDetalleHerramientaAndEstadoAlquiler(herramienta, EstadoAlquiler.ACTIVO);
    
        if (estado == EstadoHerramienta.DAÑADA || estado == EstadoHerramienta.REPARACION || alquiladaActualmente) {
            herramienta.setDisponible(false);
        } else {
            herramienta.setDisponible(true);
        }
    
        herramientaRepository.save(herramienta); 
    }
    

}
