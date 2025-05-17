package com.renta.herramienta.domain.entities;

import jakarta.persistence.Column;
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
@Table(name = "detalle_alquiler")
@Getter
@Setter
public class Detalle_Alquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_herramienta", nullable = false)
    private Herramienta herramienta;

    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false)
    private Alquiler alquiler;

    @Column(nullable = false)
    private int cantidad_dias;

    @Column(nullable = false)
    private double precio_unitario;

    @Column(nullable = false)
    private double subtotal = precio_unitario * cantidad_dias;

    public Detalle_Alquiler() {
    }

    public Detalle_Alquiler(Long id, Herramienta herramienta, Alquiler alquiler, int cantidad_dias,
            double precio_unitario, double subtotal) {
        this.id = id;
        this.herramienta = herramienta;
        this.alquiler = alquiler;
        this.cantidad_dias = cantidad_dias;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
    }

}
