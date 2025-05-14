package com.renta.herramienta.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reporte_dano")
public class Reporte_Dano {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private LocalDateTime fecha_reporte;

    @ManyToOne
    @JoinColumn(name = "id_devolucion", nullable = false)
    private Devolucion devolucion;

    @ManyToOne
    @JoinColumn(name = "id_herramienta", nullable = false)
    private Herramienta herramienta;

    public Reporte_Dano() {}

    public Reporte_Dano(Long id, String descripcion, LocalDateTime fecha_reporte, Devolucion devolucion,
            Herramienta herramienta) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha_reporte = fecha_reporte;
        this.devolucion = devolucion;
        this.herramienta = herramienta;
    }

    
}
