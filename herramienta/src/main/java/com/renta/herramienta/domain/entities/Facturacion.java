package com.renta.herramienta.domain.entities;


import java.time.LocalDate;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facturacion")
public class Facturacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha_emision;

    @Column(nullable = false)
    private double total;

    
    @OneToOne
    @JoinColumn(name = "id_pago", nullable = false, unique = true)
    private Pago pago;

    
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    @JsonBackReference
    private Proveedor proveedor;

    // Opcional: número de factura, por temas de trazabilidad
    @Column(name = "numero_factura", unique = true, nullable = false, length = 30)
    private String numeroFactura;

    
}

