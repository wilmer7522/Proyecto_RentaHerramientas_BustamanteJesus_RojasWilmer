package com.renta.herramienta.infraestructure.repositories.facturacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.FacturacionService;
import com.renta.herramienta.domain.dto.FacturacionDTO;
import com.renta.herramienta.domain.entities.EstadoPago;
import com.renta.herramienta.domain.entities.Facturacion;
import com.renta.herramienta.domain.entities.Pago;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.mapper.FacturacionMapper;
import com.renta.herramienta.domain.request.FacturacionRequest;
import com.renta.herramienta.infraestructure.repositories.pago.PagoRepository;
import com.renta.herramienta.infraestructure.repositories.proveedor.ProveedorRepository;


@Service
public class FacturacionServiceImpl implements FacturacionService {

    private final FacturacionRepository facturacionRepository;
    private final PagoRepository pagoRepository;
    private final ProveedorRepository proveedorRepository;

    public FacturacionServiceImpl(FacturacionRepository facturacionRepository,
                                  PagoRepository pagoRepository,
                                  ProveedorRepository proveedorRepository) {
        this.facturacionRepository = facturacionRepository;
        this.pagoRepository = pagoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public FacturacionDTO crearFactura(FacturacionRequest request) {
        Pago pago = pagoRepository.findById(request.getPagoId())
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + request.getPagoId()));

        Proveedor proveedor = proveedorRepository.findById(request.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + request.getProveedorId()));

        // Validar que el pago esté COMPLETADO antes de facturar
        if (pago.getEstadoPago() != EstadoPago.COMPLETADO) {
            throw new RuntimeException("No se puede facturar un pago que no esté COMPLETADO");
        }

        // Validar si ya existe una factura para este pago
        facturacionRepository.findByPagoId(request.getPagoId()).ifPresent(f -> {
            throw new RuntimeException("Ya existe una factura para este pago");
        });

        Facturacion factura = FacturacionMapper.toEntity(request, pago, proveedor);
        Facturacion facturaGuardada = facturacionRepository.save(factura);

        // Log para verificar que la factura se guardó
        System.out.println("Factura creada con ID: " + facturaGuardada.getId());

        return FacturacionMapper.toDTO(facturaGuardada);
    }

    @Override
    public FacturacionDTO obtenerFacturaPorId(Long id) {
        Facturacion factura = facturacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
        return FacturacionMapper.toDTO(factura);
    }

    @Override
    public List<FacturacionDTO> listarFacturas() {
        List<Facturacion> facturas = facturacionRepository.findAll();
        return facturas.stream().map(FacturacionMapper::toDTO).toList();
    }

    public Facturacion obtenerEntidadPorId(Long id) {
        return facturacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
    }

    public FacturacionDTO obtenerFacturaPorPagoId(Long pagoId) {
        Facturacion factura = facturacionRepository.findByPagoId(pagoId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada para pagoId: " + pagoId));
        return FacturacionMapper.toDTO(factura);
    }
}
