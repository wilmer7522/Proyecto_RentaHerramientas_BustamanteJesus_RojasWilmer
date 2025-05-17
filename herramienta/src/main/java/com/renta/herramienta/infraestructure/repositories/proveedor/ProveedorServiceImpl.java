package com.renta.herramienta.infraestructure.repositories.proveedor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.ProveedorService;
import com.renta.herramienta.domain.dto.ProveedorRequest;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.entities.Rol;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyecta el bean

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    @Transactional
    public Proveedor removeProveedor(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));

        proveedorRepository.delete(proveedor);
        return proveedor;

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

    @Transactional
    public Proveedor createNewProveedor(ProveedorRequest request) {
        // 1. Validar correo único
        if (proveedorRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // 2. Obtener rol
        Rol rolProveedor = new Rol();
        rolProveedor.setId(2L); // Solo establece el ID sin consultar la BD

        // 3. Crear proveedor
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

}
