package com.renta.herramienta.aplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renta.herramienta.domain.entities.CategoriaHerramienta;
import com.renta.herramienta.domain.entities.EstadoHerramienta;
import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Herramienta;
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

    public Herramienta createHerramienta(Herramienta herramienta) {
        if (herramientaRepository.existsByNombre(herramienta.getNombre())) {
            throw new IllegalStateException("Ya existe una herramienta con ese nombre");
        }

        CategoriaHerramienta categoria = categoriaRepository.findById(herramienta.getCategoriaHerramienta().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + herramienta.getCategoriaHerramienta().getId()));

        herramienta.setCategoriaHerramienta(categoria);
        herramienta.setDisponible(true);

        if (herramienta.getEstadoHerramienta() == null) {
            herramienta.setEstadoHerramienta(EstadoHerramienta.BUENA);
        }

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

        if (!herramienta.getNombre().equals(herramientaDetails.getNombre()) &&
                herramientaRepository.existsByNombre(herramientaDetails.getNombre())) {
            throw new IllegalStateException("Ya existe una herramienta con ese nombre");
        }

        herramienta.setNombre(herramientaDetails.getNombre());
        herramienta.setDescripcion(herramientaDetails.getDescripcion());
        herramienta.setCosto_dia(herramientaDetails.getCosto_dia());
        herramienta.setDisponible(herramientaDetails.isDisponible());
        herramienta.setEstadoHerramienta(herramientaDetails.getEstadoHerramienta());

        if (herramientaDetails.getCategoriaHerramienta() != null &&
                !herramientaDetails.getCategoriaHerramienta().getId().equals(herramienta.getCategoriaHerramienta().getId())) {
            CategoriaHerramienta nuevaCategoria = categoriaRepository.findById(herramientaDetails.getCategoriaHerramienta().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + herramientaDetails.getCategoriaHerramienta().getId()));
            herramienta.setCategoriaHerramienta(nuevaCategoria);
        }

        return herramientaRepository.save(herramienta);
    }

    public void deleteHerramienta(Long id) {
        Herramienta herramienta = getHerramientaById(id);

        List<Herramienta> herramientasConReservas = herramientaRepository.findHerramientasConReservasActivas(EstadoReserva.ACEPTADA);
;
        if (herramientasConReservas.contains(herramienta)) {
            throw new IllegalStateException("No se puede eliminar la herramienta porque tiene reservas activas");
        }

        herramientaRepository.delete(herramienta);
    }

    public List<Herramienta> getHerramientasByEstado(EstadoHerramienta estado) {
        return herramientaRepository.findByEstadoHerramienta(estado);
    }
}
