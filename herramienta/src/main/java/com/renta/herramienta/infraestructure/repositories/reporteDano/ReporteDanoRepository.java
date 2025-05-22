package com.renta.herramienta.infraestructure.repositories.reporteDano;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renta.herramienta.domain.entities.ReporteDano;

@Repository
public interface ReporteDanoRepository extends JpaRepository<ReporteDano, Long> {
    
    // Opcional: obtener reportes por herramienta
    List<ReporteDano> findByHerramientaId(Long herramientaId);

    // Opcional: obtener reportes por devolución
    List<ReporteDano> findByDevolucionId(Long devolucionId);
}


