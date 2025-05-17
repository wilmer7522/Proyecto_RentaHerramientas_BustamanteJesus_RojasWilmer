package com.renta.herramienta.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alquiler")
@Getter
@Setter
public class Alquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha_alquiler;

    @Column(nullable = false)
    private LocalDateTime fecha_devolucion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado_Alquiler estado_alquiler;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_reserva") // Puedes nombrar explícitamente la columna
    private Reserva reserva;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL)
    private List<Detalle_Alquiler> detalles;

    public Alquiler() {}

    public Alquiler(Long id, LocalDateTime fecha_alquiler, LocalDateTime fecha_devolucion,
                    Estado_Alquiler estado_alquiler, Cliente cliente, Reserva reserva) {
        this.id = id;
        this.fecha_alquiler = fecha_alquiler;
        this.fecha_devolucion = fecha_devolucion;
        this.estado_alquiler = estado_alquiler;
        this.cliente = cliente;
        this.reserva = reserva;
    }
}

