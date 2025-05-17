package com.renta.herramienta.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest implements Serializable {







    @NotBlank(message = "El nombre Usuario-Cliente es requerido")
    @Size(min = 3, max = 15)
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;
    
    @NotBlank(message = "El teléfono es requerido")
    private String telefono;
    
    @NotBlank(message = "La dirección es requerida")
    private String direccion;
    
    @NotBlank(message = "La cédula es requerida")
    private String cedula;

    @NotBlank
    private String correo;
    @NotBlank
    private String password;
   
    @NotNull(message = "El ID del rol es requerido")
    private Long idRol;


    
}

