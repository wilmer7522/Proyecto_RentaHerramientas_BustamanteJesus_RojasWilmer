package com.renta.herramienta.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HerramientaInventarioRequest {
    private HerramientaRequest herramienta;
    private Long id_proveedor;
    private int cantidad;


}
