package com.renta.herramienta.aplication.service;

import java.util.List;

import com.renta.herramienta.domain.dto.ClienteRequest;
import com.renta.herramienta.domain.entities.Cliente;



public interface ClienteService {

    public List<Cliente> findAllClientesByFilter(String filter, String value);

	public Cliente createNewCliente(ClienteRequest clienteRequest);

    public Cliente saveCliente(Cliente cliente);

}
