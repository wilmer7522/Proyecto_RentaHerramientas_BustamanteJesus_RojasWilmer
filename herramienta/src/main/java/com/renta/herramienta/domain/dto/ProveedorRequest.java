package com.renta.herramienta.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorRequest implements Serializable {
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String correo;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    
    
    private Long idRol;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    private String telefono;
    
    @Size(max = 100, message = "La dirección no puede exceder 100 caracteres")
    private String direccion;
    
    @Size(max = 20, message = "El NIT no puede exceder 20 caracteres")
    private String nit;

}