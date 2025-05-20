package com.renta.herramienta.aplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renta.herramienta.domain.entities.CategoriaHerramienta;
import com.renta.herramienta.infraestructure.repositories.herramienta.CategoriaHerramientaRepository;
import com.renta.herramienta.infraestructure.utils.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaHerramientaService {

    @Autowired
    private CategoriaHerramientaRepository categoriaRepository;

    // Crear nueva categoría
    public CategoriaHerramienta createCategoria(CategoriaHerramienta categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new IllegalStateException("Ya existe una categoría con ese nombre");
        }
        return categoriaRepository.save(categoria);
    }

    // Obtener todas las categorías
    public List<CategoriaHerramienta> getAllCategorias() {
        return categoriaRepository.findAllByOrderByNombreAsc();
    }

    // Obtener categoría por ID
    public CategoriaHerramienta getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    // Buscar categorías por nombre
    public List<CategoriaHerramienta> searchCategoriasByNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Actualizar categoría
    public CategoriaHerramienta updateCategoria(Long id, CategoriaHerramienta categoriaDetails) {
        CategoriaHerramienta categoria = getCategoriaById(id);

        if (categoriaDetails.getNombre() == null || categoriaDetails.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }

        if (!categoria.getNombre().equalsIgnoreCase(categoriaDetails.getNombre())) {
            if (categoriaRepository.existsByNombreIgnoreCase(categoriaDetails.getNombre())) {
                throw new IllegalStateException(
                        "Ya existe una categoría con el nombre: " + categoriaDetails.getNombre());
            }
            categoria.setNombre(categoriaDetails.getNombre().trim());
        }

        if (categoriaDetails.getDescripcion() != null) {
            categoria.setDescripcion(categoriaDetails.getDescripcion().trim());
        }

        return categoriaRepository.save(categoria);
    }

    // Eliminar categoría
    public void deleteCategoria(Long id) {
        CategoriaHerramienta categoria = getCategoriaById(id);

        Long count = categoriaRepository.countHerramientasByCategoriaId(id);
        if (count > 0) {
            throw new IllegalStateException("No se puede eliminar la categoría porque tiene herramientas asociadas");
        }

        categoriaRepository.delete(categoria);
    }

    // Obtener categoría por nombre exacto
    public CategoriaHerramienta getCategoriaByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con nombre: " + nombre));
    }
}
