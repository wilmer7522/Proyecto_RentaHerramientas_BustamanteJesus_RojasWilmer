package com.renta.herramienta.infraestructure.repositories.herramienta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renta.herramienta.domain.entities.CategoriaHerramienta;
import com.renta.herramienta.domain.entities.EstadoHerramienta;
import com.renta.herramienta.domain.entities.EstadoReserva;
import com.renta.herramienta.domain.entities.Herramienta;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    
    // Método para encontrar herramientas por categoría
    List<Herramienta> findByCategoriaHerramienta(CategoriaHerramienta categoriaHerramienta);
    
    // Método para encontrar herramientas disponibles
    List<Herramienta> findByDisponibleTrue();
    
    // Método para encontrar herramientas por estado
    List<Herramienta> findByEstadoHerramienta(EstadoHerramienta estado);
    
    // Método para verificar si existe una herramienta con nombre específico
    //boolean existsByNombre(String nombre);
    
    // Método para encontrar herramientas por nombre (búsqueda parcial)
    List<Herramienta> findByNombreContainingIgnoreCase(String nombre);
    
    // Método para encontrar herramientas con reservas activas
    @Query("SELECT DISTINCT h FROM Herramienta h JOIN h.detalleReservas dr JOIN dr.reserva r WHERE r.estadoReserva = :estado")
    List<Herramienta> findHerramientasConReservasActivas(@Param("estado") EstadoReserva estado);


    // Método corregido para buscar herramientas por ID de categoría
    List<Herramienta> findByCategoriaHerramientaId(Long categoriaId);

    Optional<Herramienta> findByNombre(String nombre);

}