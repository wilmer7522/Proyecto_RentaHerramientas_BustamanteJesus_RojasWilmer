package com.renta.herramienta.infraestructure.repositories.inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Inventario;
import com.renta.herramienta.domain.entities.Proveedor;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByHerramienta(Herramienta herramienta);

    Optional<Inventario> findByHerramientaAndProveedor(Herramienta herramienta, Proveedor proveedor);

    @Query("SELECT i.herramienta.nombre AS nombre, SUM(i.cantidadDisponible) AS cantidad " +
       "FROM Inventario i " +
       "GROUP BY i.herramienta.nombre")
    List<Object[]> obtenerCantidadDisponiblePorNombreHerramienta();

}

