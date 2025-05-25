package com.renta.herramienta.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "detalle_alquiler")
public class DetalleAlquiler {

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
    private int cantidad; // NUEVO: Cantidad de unidades alquiladas

    @Column(nullable = false)
    private double precio_unitario;

    @Column(nullable = false)
    private double subtotal;

    @PrePersist
    @PreUpdate
    public void calcularSubtotal() {
        this.subtotal = this.precio_unitario * this.cantidad_dias * this.cantidad;
    }
}
