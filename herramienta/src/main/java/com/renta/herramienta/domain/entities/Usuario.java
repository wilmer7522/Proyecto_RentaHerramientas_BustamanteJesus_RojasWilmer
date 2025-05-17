package com.renta.herramienta.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
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
    @JsonBackReference
    @JoinColumn(name = "id_rol", nullable = false)
    @JsonBackReference
    private Rol rol;
    
   

    public Usuario() {
    }

    public Usuario(Long id, String correo, String password, Rol rol) {
    public Usuario(Long id, String correo, String password, Rol rol) {
        this.id = id;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
       
    }

}
