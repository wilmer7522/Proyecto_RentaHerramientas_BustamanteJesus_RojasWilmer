package com.renta.herramienta.domain.entities;

import java.time.LocalDateTime;

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
@Getter
@Setter
@Table(name = "notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fecha;

    private boolean leido;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    public Notificacion(){}

    public Notificacion(Long id, String mensaje, LocalDateTime fecha, boolean leido, Cliente cliente,
            Proveedor proveedor) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leido = leido;
        this.cliente = cliente;
        this.proveedor = proveedor;
    }

    

    
    
}
