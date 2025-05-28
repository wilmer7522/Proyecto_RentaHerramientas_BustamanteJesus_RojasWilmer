package com.renta.herramienta.infraestructure.repositories.proveedor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.ProveedorService;
import com.renta.herramienta.domain.dto.ProveedorDTO;
import com.renta.herramienta.domain.entities.Cliente;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.entities.Rol;
import com.renta.herramienta.domain.mapper.ProveedorMapper;
import com.renta.herramienta.domain.request.ClienteRequest;
import com.renta.herramienta.domain.request.ProveedorRequest;
import com.renta.herramienta.domain.request.ProveedorUpdateRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    // Delete
    @Override
    @Transactional
    public Proveedor removeProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));

        proveedorRepository.delete(proveedor);
        return proveedor;

    }

    @Override
    public List<ProveedorDTO> findAllProveedoresDTOByFilter(String filter, String value) {
        List<Proveedor> proveedores = findAllProveedoresByFilter(filter, value); // usa el método existente
        return proveedores.stream()
                .map(ProveedorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Proveedor> findAllProveedoresByFilter(String filter, String value) {
        if (filter.isEmpty() || value.isEmpty()) {
            return proveedorRepository.findAll();
        }

        switch (filter.toLowerCase()) {
            case "name":
                return proveedorRepository.findByNombreContainingIgnoreCase(value);
            default:
                return proveedorRepository.findAll();
        }
    }


    @Override
    public ProveedorDTO createNewProveedorDTO(ProveedorRequest request) {

        // Obtener rol
        Rol rolProveedor = new Rol();
        rolProveedor.setId(request.getIdRol());
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.getNombre());
        proveedor.setPassword(request.getPassword());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setCorreo(request.getCorreo());
        proveedor.setDireccion(request.getDireccion());
        proveedor.setRol(rolProveedor);

        Proveedor guardado = proveedorRepository.save(proveedor);

        return ProveedorMapper.toDTO(guardado);
    }

    @Transactional
    public Proveedor createNewProveedor(ProveedorRequest request) {
        // Validar correo único
        if (proveedorRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Obtener rol
        Rol rolProveedor = new Rol();
        rolProveedor.setId(2L);

        // Crear proveedor
        Proveedor proveedor = new Proveedor();
        proveedor.setCorreo(request.getCorreo());
        proveedor.setPassword(passwordEncoder.encode(request.getPassword()));
        proveedor.setRol(rolProveedor);

        proveedor.setNombre(request.getNombre());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setDireccion(request.getDireccion());
        proveedor.setNit(request.getNit());

        return proveedorRepository.save(proveedor);
    }

    @Override
    @Transactional
    public Proveedor update(Long id, ProveedorUpdateRequest updates) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));

        if (updates.getNombre() != null) {
            proveedor.setNombre(updates.getNombre());
        }
        if (updates.getTelefono() != null) {
            proveedor.setTelefono(updates.getTelefono());
        }
        if (updates.getDireccion() != null) {
            proveedor.setDireccion(updates.getDireccion());
        }
        if (updates.getNit() != null) {
            proveedor.setNit(updates.getNit());
        }
        if (updates.getCorreo() != null) {
            proveedor.setCorreo(updates.getCorreo());
        }

        return proveedorRepository.save(proveedor);
    }

}
