package com.renta.herramienta.aplication.service;

import java.util.List;



import com.renta.herramienta.domain.entities.Cliente;



public interface ClienteService {

    public List<Cliente> findAllClientesByFilter(String filter, String value);

}
