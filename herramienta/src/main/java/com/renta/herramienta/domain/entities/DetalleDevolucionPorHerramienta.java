package com.renta.herramienta.domain.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleDevolucionPorHerramienta {
    private Long herramientaId;
    private List<UnidadDevuelta> devoluciones;
}

