package com.renta.herramienta.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aprobacion {
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;
    
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
    
    private LocalDate fechaAprobacion;
    private boolean estado;


    public Aprobacion(Long id, Reserva reserva, Proveedor proveedor, LocalDate fechaAprobacion, boolean estado) {
        this.id = id;
        this.reserva = reserva;
        this.proveedor = proveedor;
        this.fechaAprobacion = fechaAprobacion;
        this.estado = estado;
    }

    
}
