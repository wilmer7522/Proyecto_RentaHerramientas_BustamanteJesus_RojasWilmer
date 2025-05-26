package com.renta.herramienta.domain.mapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


import com.renta.herramienta.domain.dto.DetalleFacturaDTO;
import com.renta.herramienta.domain.dto.FacturacionDTO;
import com.renta.herramienta.domain.entities.Alquiler;
import com.renta.herramienta.domain.entities.Facturacion;
import com.renta.herramienta.domain.entities.Pago;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.request.FacturacionRequest;

public class FacturacionMapper {

    public static FacturacionDTO toDTO(Facturacion facturacion) {
        FacturacionDTO dto = new FacturacionDTO();
        dto.setId(facturacion.getId());
        dto.setFechaEmision(facturacion.getFecha_emision());
        dto.setTotal(facturacion.getTotal());
        dto.setNumeroFactura(facturacion.getNumeroFactura());
        dto.setPagoId(facturacion.getPago().getId());
        dto.setProveedor(ProveedorMapper.toDTO(facturacion.getProveedor()));

        
        if (facturacion.getPago().getAlquiler() != null) {
            Alquiler alquiler = facturacion.getPago().getAlquiler();
            dto.setNombreCliente(alquiler.getCliente().getNombre());

            int dias = calcularDias(alquiler.getFecha_alquiler(), alquiler.getFecha_devolucion());

            List<DetalleFacturaDTO> detalles = alquiler.getDetalle().stream().map(h -> {
                DetalleFacturaDTO detalle = new DetalleFacturaDTO();
                detalle.setNombreHerramienta(h.getHerramienta().getNombre()); 
                detalle.setCostoPorDia(h.getPrecio_unitario());
                detalle.setDiasAlquiler(dias);
                detalle.setCantidad(h.getCantidad());  
                detalle.setTotalPorHerramienta(h.getPrecio_unitario() * dias * h.getCantidad()); 
                return detalle;
            }).toList();

            dto.setDetalles(detalles);
        }

        return dto;
    }

    private static int calcularDias(LocalDate inicio, LocalDate fin) {
        return (int) ChronoUnit.DAYS.between(inicio, fin);
    }

    public static Facturacion toEntity(FacturacionRequest request, Pago pago, Proveedor proveedor) {
        Facturacion facturacion = new Facturacion();
        facturacion.setFecha_emision(LocalDate.now());
        facturacion.setTotal(request.getTotal());
        facturacion.setPago(pago);
        facturacion.setProveedor(proveedor);
        facturacion.setNumeroFactura("FACT-" + LocalDate.now().getYear() + "-" + String.format("%04d", request.getPagoId()));
        return facturacion;
    }
}




