package com.renta.herramienta.infraestructure.repositories.cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renta.herramienta.aplication.service.ClienteService;
import com.renta.herramienta.domain.entities.Cliente;
import com.renta.herramienta.domain.entities.Rol;
import com.renta.herramienta.domain.request.ClienteRequest;
import com.renta.herramienta.domain.request.ClienteUpdateRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAllClientesByFilter(String filter, String value) {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente createNewCliente(ClienteRequest clienteRequest) {
        Rol rol = new Rol();
        rol.setId(clienteRequest.getIdRol());

        Cliente cliente = new Cliente(
                null,
                clienteRequest.getCorreo(),
                clienteRequest.getPassword(),
                rol,
                clienteRequest.getNombre(),
                clienteRequest.getApellido(),
                clienteRequest.getTelefono(),
                clienteRequest.getDireccion(),
                clienteRequest.getCedula());

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente removeCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        clienteRepository.delete(cliente);
        return cliente;

    }

    @Override
    public Cliente updateCliente(Long id, ClienteUpdateRequest clienteUpdateRequest) {
        Cliente clienteup = clienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

    if (clienteUpdateRequest.getNombre() != null) {
    clienteup.setNombre(clienteUpdateRequest.getNombre());
    }
    if (clienteUpdateRequest.getApellido() != null) {
    clienteup.setApellido(clienteUpdateRequest.getApellido());
    }
    if (clienteUpdateRequest.getTelefono() != null) {
    clienteup.setTelefono(clienteUpdateRequest.getTelefono());
    }
    if (clienteUpdateRequest.getDireccion() != null) {
    clienteup.setDireccion(clienteUpdateRequest.getDireccion());
    }
    if (clienteUpdateRequest.getCedula() != null) {
    clienteup.setCedula(clienteUpdateRequest.getCedula());
    }
    if (clienteUpdateRequest.getCorreo() != null) {
    clienteup.setCorreo(clienteUpdateRequest.getCorreo());
    }

return clienteRepository.save(clienteup);
    }

   

}
