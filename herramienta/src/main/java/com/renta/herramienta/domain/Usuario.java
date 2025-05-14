package com.renta.herramienta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String correo;
    
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Admin administrador;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Proveedor proveedor;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Cliente cliente;

    public Usuario() {
    }

    public Usuario(Long id, String correo, String password, Rol rol, Admin administrador, Proveedor proveedor,
            Cliente cliente) {
        this.id = id;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.administrador = administrador;
        this.proveedor = proveedor;
        this.cliente = cliente;
    }

}
