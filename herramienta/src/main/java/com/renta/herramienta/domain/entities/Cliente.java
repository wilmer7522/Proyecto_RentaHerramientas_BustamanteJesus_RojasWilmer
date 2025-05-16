package com.renta.herramienta.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente extends Usuario{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellido;
    
    private String telefono;
    private String direccion;
    private String cedula;
    
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    
    public Cliente() {
    }


    public Cliente(Long id, String nombre, String apellido, String telefono, String direccion, String cedula,
            Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cedula = cedula;
        this.usuario = usuario;
    }


    public Cliente(Long id, String correo, String password, Rol rol, Admin administrador, Proveedor proveedor,
            Cliente cliente, Long id2, String nombre, String apellido, String telefono, String direccion, String cedula,
            Usuario usuario) {
        super(id, correo, password, rol, administrador, proveedor, cliente);
        id = id2;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cedula = cedula;
        this.usuario = usuario;
    }

}*/

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

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String telefono, String direccion, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cedula = cedula;
    }
}

