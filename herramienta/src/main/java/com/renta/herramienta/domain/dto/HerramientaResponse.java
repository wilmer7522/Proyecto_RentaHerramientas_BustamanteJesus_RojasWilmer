package com.renta.herramienta.domain.dto;

import com.renta.herramienta.domain.request.HerramientaRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HerramientaResponse extends HerramientaRequest{
    private String categoriaNombre;
}
