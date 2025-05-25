package com.renta.herramienta.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renta.herramienta.domain.dto.HerramientaDTO;
import com.renta.herramienta.domain.dto.HerramientaInventarioDTO;
import com.renta.herramienta.domain.entities.CategoriaHerramienta;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.request.HerramientaRequest;
import com.renta.herramienta.infraestructure.repositories.herramienta.CategoriaHerramientaRepository;
import com.renta.herramienta.infraestructure.utils.exceptions.ResourceNotFoundException;

@Component
public class HerramientaMapper {

    @Autowired
    private CategoriaHerramientaRepository categoriaRepository;

    // Para vistas simples como reserva
    public HerramientaDTO toDTO(Herramienta herramienta) {
        return new HerramientaDTO(herramienta.getId(), herramienta.getNombre());
    }

    // Para vistas detalladas en inventario
    public HerramientaInventarioDTO toInventarioDTO(Herramienta herramienta) {
        HerramientaInventarioDTO dto = new HerramientaInventarioDTO();
        dto.setId(herramienta.getId());
        dto.setNombre(herramienta.getNombre());
        dto.setDescripcion(herramienta.getDescripcion());
        dto.setCosto_dia(herramienta.getCosto_dia());
        dto.setDisponible(herramienta.isDisponible());
        dto.setEstadoHerramienta(herramienta.getEstadoHerramienta());

        if (herramienta.getCategoriaHerramienta() != null) {
            dto.setNombreCategoria(herramienta.getCategoriaHerramienta().getNombre());
        }

        return dto;
    }

    // Para creación de herramientas desde el request
    public Herramienta toEntity(HerramientaRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("HerramientaRequest no puede ser null");
        }
        Herramienta herramienta = new Herramienta();
        herramienta.setNombre(request.getNombre());
        herramienta.setDescripcion(request.getDescripcion());
        herramienta.setCosto_dia(request.getCosto_dia());
        herramienta.setDisponible(request.isDisponible());
        herramienta.setEstadoHerramienta(request.getEstadoHerramienta());

        CategoriaHerramienta categoria = categoriaRepository.findById(request.getCategoriaId())
            .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + request.getCategoriaId()));

        herramienta.setCategoriaHerramienta(categoria);
        return herramienta;
    }
}


