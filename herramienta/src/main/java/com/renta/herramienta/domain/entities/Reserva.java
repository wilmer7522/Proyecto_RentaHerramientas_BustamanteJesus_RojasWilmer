package com.renta.herramienta.domain.entities;

import java.time.LocalDate;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate fecha_inicio;

    @Column(nullable = false)
    private LocalDate fecha_fin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estadoReserva;

    
}

