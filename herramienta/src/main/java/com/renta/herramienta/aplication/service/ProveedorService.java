package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.dto.ProveedorDTO;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.request.ProveedorRequest;
import com.renta.herramienta.domain.request.ProveedorUpdateRequest;



public interface ProveedorService {
    
    public List<Proveedor> findAllProveedoresByFilter(String filter, String value);
    public Proveedor createNewProveedor(ProveedorRequest request);
    public Proveedor removeProveedor(Long id);
    public Proveedor update(Long id, ProveedorUpdateRequest updates);
    public List<ProveedorDTO> findAllProveedoresDTOByFilter(String filter, String value);
    public ProveedorDTO createNewProveedorDTO(ProveedorRequest request);

}
