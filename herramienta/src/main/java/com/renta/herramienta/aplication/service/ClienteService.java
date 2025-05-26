package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.entities.Cliente;
import com.renta.herramienta.domain.request.ClienteRequest;
import com.renta.herramienta.domain.request.ClienteUpdateRequest;




public interface ClienteService {

    public List<Cliente> findAllClientesByFilter(String filter, String value);

	public Cliente createNewCliente(ClienteRequest clienteRequest);

    public Cliente saveCliente(Cliente cliente);

    public Cliente removeCliente(Long id);

    public Cliente updateCliente(Long id, ClienteUpdateRequest clienteUpdateRequest);



    

}
