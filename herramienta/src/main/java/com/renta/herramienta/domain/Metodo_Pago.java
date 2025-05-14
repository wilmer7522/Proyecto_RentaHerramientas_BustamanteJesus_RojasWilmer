package com.renta.herramienta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "metodo_pago")
public class Metodo_Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public Metodo_Pago(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //@OneToMany(mappedBy = "metodo_pago", cascade = CascadeType.ALL)
    //private Pago pago;

    public Metodo_Pago() {}

    
    
}
