package com.renta.herramienta.domain.entities;

import java.time.LocalDate;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alquiler")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private LocalDate fecha_alquiler;

    //@Column(nullable = false)
    private LocalDate fecha_devolucion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_alquiler",nullable = false)
    private EstadoAlquiler estadoAlquiler;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_reserva") // Puedes nombrar explícitamente la columna
    private Reserva reserva;

    @OneToMany(mappedBy = "alquiler", cascade = CascadeType.ALL)
    private List<DetalleAlquiler> detalle;

}
