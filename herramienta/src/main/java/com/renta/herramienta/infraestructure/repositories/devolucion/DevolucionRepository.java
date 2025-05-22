package com.renta.herramienta.infraestructure.repositories.devolucion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.renta.herramienta.domain.entities.Devolucion;



@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {
    
    // Opcional: buscar devoluciones por algún criterio
    List<Devolucion> findByConfirmacionProveedor(boolean confirmacion);

    @Query("SELECT d FROM Devolucion d LEFT JOIN FETCH d.reportesDano WHERE d.id = :id")
    Optional<Devolucion> findByIdWithReportes(Long id);

}


