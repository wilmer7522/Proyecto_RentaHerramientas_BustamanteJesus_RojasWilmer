package com.renta.herramienta.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "herramienta")
public class Herramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private int costo_dia;

    private boolean disponible;

    @Enumerated(EnumType.STRING)
    private EstadoHerramienta estadoHerramienta;

    private String fotourl;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaHerramienta categoriaHerramienta;

    @OneToMany(mappedBy = "herramienta")
    @JsonIgnore
    private List<DetalleReserva> detalleReservas = new ArrayList<>();
}

