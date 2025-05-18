package com.renta.herramienta.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente extends Usuario {

    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String cedula;

    @OneToMany(mappedBy = "cliente")
    @JsonBackReference
    private List<Reserva> reserva;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String telefono, String direccion, String cedula,
            List<Reserva> reserva) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cedula = cedula;
        this.reserva = reserva;
    }

    public Cliente(Long id, String correo, String password, Rol rol, String nombre, String apellido, String telefono,
            String direccion, String cedula) {
        super(id, correo, password, rol);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cedula = cedula;
    }

    public Cliente(Long id, String correo, String password, Rol rol, String nombre, String apellido, String telefono,
            String direccion, String cedula, List<Reserva> reserva) {
        super(id, correo, password, rol);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cedula = cedula;
        this.reserva = reserva;
    }

}
