package com.renta.herramienta.infraestructure.repositories.herramienta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renta.herramienta.domain.entities.CategoriaHerramienta;

public interface CategoriaHerramientaRepository extends JpaRepository<CategoriaHerramienta, Long> {
    
    // Buscar categoría por nombre exacto
    Optional<CategoriaHerramienta> findByNombre(String nombre);
    
    // Buscar categorías cuyo nombre contenga una cadena (insensible a mayúsculas)
    List<CategoriaHerramienta> findByNombreContainingIgnoreCase(String nombre);
    
    // Verificar si existe una categoría con determinado nombre
    boolean existsByNombre(String nombre);
    
    // Obtener categorías ordenadas por nombre
    List<CategoriaHerramienta> findAllByOrderByNombreAsc();
    
    // Contar herramientas asociadas a una categoría (usando query JPQL)
    @Query("SELECT COUNT(h) FROM Herramienta h WHERE h.categoriaHerramienta.id = :categoriaId")
    Long countHerramientasByCategoriaId(@Param("categoriaId") Long categoriaId);

    // Método para verificar existencia por nombre (case insensitive)
    boolean existsByNombreIgnoreCase(String nombre);
}