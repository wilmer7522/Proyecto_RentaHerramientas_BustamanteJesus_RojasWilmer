package com.renta.herramienta.infraestructure.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.ClienteService;
import com.renta.herramienta.domain.dto.ClienteDTO;
import com.renta.herramienta.domain.entities.Cliente;
import com.renta.herramienta.domain.entities.Rol;
import com.renta.herramienta.domain.mapper.ClienteMapper;
import com.renta.herramienta.domain.request.ClienteRequest;
import com.renta.herramienta.domain.request.ClienteUpdateRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    // GET
    @GetMapping("/client")
    public List<ClienteDTO> findAllClientes(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue = "") String value
    ) {
        List<Cliente> results = clienteService.findAllClientesByFilter(filter, value);
        return results.stream().map(ClienteMapper::toDTO).collect(Collectors.toList());
    }

    // POST
    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO newCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
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
            clienteRequest.getCedula()
        );

        Cliente saved = clienteService.saveCliente(cliente);
        return ClienteMapper.toDTO(saved);
    }

    // DELETE
    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> removeCliente(@PathVariable Long id) {
        try {
            Cliente eliminado = clienteService.removeCliente(id);
            return ResponseEntity.ok(ClienteMapper.toDTO(eliminado));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error interno: " + ex.getMessage());
        }
    }

    // PATCH
    @PatchMapping("/client/{id}")
    public ResponseEntity<ClienteDTO> partiallyUpdateCliente(
            @PathVariable Long id,
            @RequestBody ClienteUpdateRequest updateCliente) {
        Cliente clienteActualizado = clienteService.updateCliente(id, updateCliente);
        return ResponseEntity.ok(ClienteMapper.toDTO(clienteActualizado));
    }
}

