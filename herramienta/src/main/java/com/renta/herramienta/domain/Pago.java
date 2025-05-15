package com.renta.herramienta.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "pago")
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int monto;

    private LocalDateTime fecha_pago;

    @Enumerated(EnumType.STRING)
    private Estado_Pago estado_Pago;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private Metodo_Pago metodo_Pago;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_alquiler")
    private Alquiler alquiler;

    public Pago() {}

    public Pago(Long id, int monto, LocalDateTime fecha_pago, Estado_Pago estado_Pago, Metodo_Pago metodo_Pago) {
        this.id = id;
        this.monto = monto;
        this.fecha_pago = fecha_pago;
        this.estado_Pago = estado_Pago;
        this.metodo_Pago = metodo_Pago;
    }

    

}
