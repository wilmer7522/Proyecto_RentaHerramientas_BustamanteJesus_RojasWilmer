package com.renta.herramienta.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pago")
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int monto;

    private LocalDateTime fecha_pago;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @OneToOne
    @JoinColumn(name = "id_alquiler")
    private Alquiler alquiler;

    public Pago() {}

    public Pago(Long id, int monto, LocalDateTime fecha_pago, EstadoPago estadoPago, MetodoPago metodoPago,
            Alquiler alquiler) {
        this.id = id;
        this.monto = monto;
        this.fecha_pago = fecha_pago;
        this.estadoPago = estadoPago;
        this.metodoPago = metodoPago;
        this.alquiler = alquiler;
    }

    

    

}
