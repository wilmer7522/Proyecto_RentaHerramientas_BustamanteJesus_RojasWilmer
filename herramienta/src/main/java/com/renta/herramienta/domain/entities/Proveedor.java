package com.renta.herramienta.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
public class Proveedor extends Usuario{
    
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    
    @Column(nullable = false)
    private String nombre;
    
    private String telefono;
    private String direccion;
    private String nit;
    
    /*@OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;*/

    public Proveedor() {
    }

    public Proveedor(Long id, String nombre, String telefono, String direccion, String nit, Usuario usuario) {
        //this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        //this.usuario = usuario;
    }

   /*  public Proveedor(Long id, String correo, String password, Rol rol, Admin administrador, Proveedor proveedor,
            Cliente cliente, Long id2, String nombre, String telefono, String direccion, String nit, Usuario usuario) {
        super(id, correo, password, rol, administrador, proveedor, cliente);
        id = id2;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        this.usuario = usuario;
    }*/
    
}
