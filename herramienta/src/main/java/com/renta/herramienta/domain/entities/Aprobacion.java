package com.renta.herramienta.domain.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    private Proveedor proveedor;
    
    private LocalDate fecha_aprobacion;
    private boolean estado;

    public Aprobacion(){}


    public Aprobacion(Long id, Reserva reserva, Proveedor proveedor, LocalDate fecha_aprobacion, boolean estado) {
        this.id = id;
        this.reserva = reserva;
        this.proveedor = proveedor;
        this.fecha_aprobacion = fecha_aprobacion;
        this.estado = estado;
    }

    
}
