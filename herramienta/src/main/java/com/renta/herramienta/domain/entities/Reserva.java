package com.renta.herramienta.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reserva")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnore
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "reserva_herramienta", 
        joinColumns = @JoinColumn(name = "id_reserva"), 
        inverseJoinColumns = @JoinColumn(name = "id_herramienta")
    )
    
    private List<Herramienta> herramientas;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    private Aprobacion aprobacion;

    @Column(nullable = false)
    private LocalDateTime fecha_reserva;

    @Column(nullable = false)
    private LocalDateTime fecha_inicio;

    @Column(nullable = false)
    private LocalDateTime fecha_fin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado_Reserva estado_reserva;

    public Reserva() {}

    public Reserva(Long id, Cliente cliente, List<Herramienta> herramientas, LocalDateTime fecha_reserva,
                   LocalDateTime fecha_inicio, LocalDateTime fecha_fin, Estado_Reserva estado_reserva) {
        this.id = id;
        this.cliente = cliente;
        this.herramientas = herramientas;
        this.fecha_reserva = fecha_reserva;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado_reserva = estado_reserva;
    }
}

