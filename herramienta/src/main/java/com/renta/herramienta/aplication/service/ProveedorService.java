package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.dto.ProveedorRequest;
import com.renta.herramienta.domain.entities.Proveedor;

public interface ProveedorService {
    
    public List<Proveedor> findAllProveedoresByFilter(String filter, String value);
    public Proveedor createNewProveedor(ProveedorRequest request);
    public Proveedor removeProveedor(Long id);

}
