package com.renta.herramienta.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
public class Proveedor extends Usuario{
    
    @Column(nullable = false)
    private String nombre;
    
    private String telefono;
    private String direccion;
    private String nit;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Facturacion> facturas = new ArrayList<>();

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Inventario> inventarios = new ArrayList<>();

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Notificacion> notificacion = new ArrayList<>();

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private List<Aprobacion> aprobacion;

    public Proveedor() {
    }

    public Proveedor(String nombre, String telefono, String direccion, String nit, List<Facturacion> facturas,
            List<Inventario> inventarios, List<Notificacion> notificacion, List<Aprobacion> aprobacion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        this.facturas = facturas;
        this.inventarios = inventarios;
        this.notificacion = notificacion;
        this.aprobacion = aprobacion;
    }

    public Proveedor(Long id, String correo, String password, Rol rol, String nombre, String telefono, String direccion,
            String nit, List<Facturacion> facturas, List<Inventario> inventarios, List<Notificacion> notificacion,
            List<Aprobacion> aprobacion) {
        super(id, correo, password, rol);
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        this.facturas = facturas;
        this.inventarios = inventarios;
        this.notificacion = notificacion;
        this.aprobacion = aprobacion;
    }

    
}
