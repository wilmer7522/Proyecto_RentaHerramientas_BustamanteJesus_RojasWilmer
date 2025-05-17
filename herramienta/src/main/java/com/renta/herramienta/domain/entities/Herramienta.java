package com.renta.herramienta.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    private Estado_Herramienta estado_Herramienta;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria_Herramienta categoria_Herramienta;

    @ManyToMany(mappedBy = "herramientas")
    private List<Reserva> reservas;



    public Herramienta() {}

    public Herramienta(Long id, String nombre, String descripcion, int costo_dia, boolean disponible,
            Estado_Herramienta estado_Herramienta, Categoria_Herramienta categoria_Herramienta) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo_dia = costo_dia;
        this.disponible = disponible;
        this.estado_Herramienta = estado_Herramienta;
        this.categoria_Herramienta = categoria_Herramienta;
    }

    


}
