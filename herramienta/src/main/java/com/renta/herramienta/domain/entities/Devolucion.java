package com.renta.herramienta.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "devolucion")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha_devolucion;
    @Enumerated(EnumType.STRING)
    private EstadoHerramienta estado_Herramienta;

    private String observaciones;

    private boolean confirmacion_proveedor;

    public Devolucion() {}

    public Devolucion(Long id, LocalDateTime fecha_devolucion, EstadoHerramienta estado_Herramienta,
            String observaciones, boolean confirmacion_proveedor) {
        this.id = id;
        this.fecha_devolucion = fecha_devolucion;
        this.estado_Herramienta = estado_Herramienta;
        this.observaciones = observaciones;
        this.confirmacion_proveedor = confirmacion_proveedor;
    }

    

    
}
