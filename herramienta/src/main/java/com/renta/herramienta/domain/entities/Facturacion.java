package com.renta.herramienta.domain.entities;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @Column(nullable = false)
    private LocalDateTime fecha_emision;

    @Column(nullable = false)
    private double total;

    // Relación uno a uno con Pago (una factura por pago)
    @OneToOne
    @JoinColumn(name = "id_pago", nullable = false, unique = true)
    private Pago pago;

    // Relación muchos a uno con Proveedor (un proveedor puede tener varias facturas)
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    @JsonBackReference
    private Proveedor proveedor;

    // Opcional: número de factura, por temas de trazabilidad
    @Column(name = "numero_factura", unique = true, nullable = false, length = 30)
    private String numeroFactura;

    public Facturacion() {}

    public Facturacion(LocalDateTime fecha_emision, double total, Pago pago, Proveedor proveedor, String numeroFactura) {
        this.fecha_emision = fecha_emision;
        this.total = total;
        this.pago = pago;
        this.proveedor = proveedor;
        this.numeroFactura = numeroFactura;
    }
}

