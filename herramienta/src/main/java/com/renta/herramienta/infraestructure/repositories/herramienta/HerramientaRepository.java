package com.renta.herramienta.infraestructure.repositories.herramienta;



import com.renta.herramienta.domain.entities.Herramienta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
    
   
}

