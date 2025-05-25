package com.renta.herramienta.aplication.service;

import java.util.List;
import java.util.Optional;

import com.renta.herramienta.domain.dto.HerramientaInventarioResumenDTO;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Inventario;
import com.renta.herramienta.domain.entities.Proveedor;

public interface InventarioService {
    void actualizarInventarioPorDevolucion(Herramienta herramienta, boolean estaDanada);

    void descontarInventarioPorAlquiler(Herramienta herramienta);

    Inventario agregarHerramientaAlInventario(Herramienta herramienta, Proveedor proveedor, int cantidad);

    List<HerramientaInventarioResumenDTO> obtenerCantidadTotalPorNombreHerramienta();

	Optional<Inventario> obtenerInventarioPorHerramienta(Herramienta h);



}
