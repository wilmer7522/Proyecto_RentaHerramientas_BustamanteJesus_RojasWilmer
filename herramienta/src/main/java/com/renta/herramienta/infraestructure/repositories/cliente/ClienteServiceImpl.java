package com.renta.herramienta.infraestructure.repositories.cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.ClienteService;
import com.renta.herramienta.domain.entities.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //Get
    @Override
    public List<Cliente> findAllClientesByFilter(String filter, String value){
        return clienteRepository.findAll();
    }

}
