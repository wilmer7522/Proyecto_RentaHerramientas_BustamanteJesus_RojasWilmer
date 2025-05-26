package com.renta.herramienta.infraestructure.repositories.facturacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renta.herramienta.domain.entities.Facturacion;
import com.renta.herramienta.domain.entities.Pago;

public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {

    Optional<Facturacion> findByNumeroFactura(String numeroFactura);

    boolean existsByPago(Pago pago);

    Optional<Facturacion> findByPagoId(Long pagoId);
}

