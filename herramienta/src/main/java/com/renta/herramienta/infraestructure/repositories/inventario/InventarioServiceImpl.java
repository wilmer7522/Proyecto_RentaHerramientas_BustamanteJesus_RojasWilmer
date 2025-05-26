package com.renta.herramienta.infraestructure.repositories.inventario;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.InventarioService;
import com.renta.herramienta.domain.dto.HerramientaInventarioResumenDTO;
import com.renta.herramienta.domain.entities.Herramienta;
import com.renta.herramienta.domain.entities.Inventario;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.infraestructure.repositories.herramienta.HerramientaRepository;
import com.renta.herramienta.infraestructure.repositories.proveedor.ProveedorRepository;

import jakarta.persistence.EntityNotFoundException;





@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final HerramientaRepository herramientaRepository;
    private final ProveedorRepository proveedorRepository;

    public InventarioServiceImpl(
            InventarioRepository inventarioRepository,
            HerramientaRepository herramientaRepository,
            ProveedorRepository proveedorRepository) {
        this.inventarioRepository = inventarioRepository;
        this.herramientaRepository = herramientaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public void actualizarInventarioPorDevolucion(Herramienta herramienta, boolean estaDanada) {
        Inventario inventario = inventarioRepository.findByHerramienta(herramienta)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Inventario no encontrado para la herramienta: " + herramienta.getNombre()));

        if (!estaDanada) {
            inventario.setCantidadDisponible(inventario.getCantidadDisponible() + 1);
            inventarioRepository.save(inventario);
        }
    }

    @Override
    public void descontarInventarioPorAlquiler(Herramienta herramienta) {
        Inventario inventario = inventarioRepository.findByHerramienta(herramienta)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Inventario no encontrado para la herramienta: " + herramienta.getNombre()));

        if (inventario.getCantidadDisponible() <= 0) {
            throw new IllegalStateException(
                    "No hay unidades disponibles de la herramienta: " + herramienta.getNombre());
        }

        inventario.setCantidadDisponible(inventario.getCantidadDisponible() - 1);
        inventarioRepository.save(inventario);
    }

    @Override
    public Inventario agregarHerramientaAlInventario(Herramienta herramienta, Proveedor proveedorInput, int cantidad) {
        Proveedor proveedor = proveedorRepository.findById(proveedorInput.getId())
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + proveedorInput.getId()));

        Optional<Herramienta> herramientaExistenteOpt = herramientaRepository.findByNombre(herramienta.getNombre());
        Herramienta herramientaFinal = herramientaExistenteOpt.orElseGet(() -> herramientaRepository.save(herramienta));

        Optional<Inventario> inventarioExistente = inventarioRepository.findByHerramientaAndProveedor(herramientaFinal, proveedor);

        Inventario inventario = inventarioExistente.orElseGet(() -> {
            Inventario nuevo = new Inventario();
            nuevo.setHerramienta(herramientaFinal);
            nuevo.setProveedor(proveedor);
            nuevo.setCantidadTotal(0);
            nuevo.setCantidadDisponible(0);
            return nuevo;
        });

        inventario.setCantidadTotal(inventario.getCantidadTotal() + cantidad);
        inventario.setCantidadDisponible(inventario.getCantidadDisponible() + cantidad);

        return inventarioRepository.save(inventario);
    }

    @Override
public List<HerramientaInventarioResumenDTO> obtenerCantidadTotalPorNombreHerramienta() {
    List<Inventario> inventarios = inventarioRepository.findAll();

    // Agrupar herramientas por nombre para calcular cantidades
    Map<String, List<Inventario>> inventariosAgrupados = inventarios.stream()
            .collect(Collectors.groupingBy(inv -> inv.getHerramienta().getNombre()));

    return inventariosAgrupados.entrySet().stream()
            .map(entry -> {
                String nombreHerramienta = entry.getKey();
                List<Inventario> listaInventario = entry.getValue();

                int totalDisponible = listaInventario.stream()
                        .mapToInt(Inventario::getCantidadDisponible)
                        .sum();

                int total = listaInventario.stream()
                        .mapToInt(Inventario::getCantidadTotal)
                        .sum();

                Optional<Inventario> inventarioEjemplo = listaInventario.stream().findFirst();

                HerramientaInventarioResumenDTO dto = new HerramientaInventarioResumenDTO();
                dto.setNombre(nombreHerramienta);
                dto.setCantidad(totalDisponible);     // ✅ Cantidad disponible actual
                dto.setCantidadTotal(total);          // ✅ Cantidad total
                inventarioEjemplo.ifPresent(inv -> dto.setId(inv.getHerramienta().getId()));

                return dto;
            })
            .collect(Collectors.toList());
}




    @Override
public Optional<Inventario> obtenerInventarioPorHerramienta(Herramienta herramienta) {
    return inventarioRepository.findByHerramienta(herramienta);
}

}
