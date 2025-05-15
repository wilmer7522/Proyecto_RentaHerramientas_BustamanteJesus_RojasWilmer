package com.renta.herramienta.domain.entities;


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
@Table(name = "facturacion")
public class Facturacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha_emision;

    private double total;


    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    public Facturacion() {}

    public Facturacion(Long id, LocalDateTime fecha_emision, double total, Cliente cliente, Proveedor proveedor) {
        this.id = id;
        this.fecha_emision = fecha_emision;
        this.total = total;
        this.cliente = cliente;
        this.proveedor = proveedor;
    }

    
    


    
}
