package com.renta.herramienta.infraestructure.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta.herramienta.aplication.service.ClienteService;
import com.renta.herramienta.domain.dto.ClienteRequest;
import com.renta.herramienta.domain.entities.Cliente;
import com.renta.herramienta.domain.entities.Proveedor;
import com.renta.herramienta.domain.entities.Rol;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }


    @GetMapping("/client")
    public List<Cliente> findAllClientes(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue = "") String value
    ) {
        List<Cliente> results = clienteService.findAllClientesByFilter(filter, value);

        return results;
    }



    //Post
    @PostMapping("/client")
@ResponseStatus(HttpStatus.CREATED)
public Cliente newCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
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

    return clienteService.saveCliente(cliente);
}

//Delete 
@DeleteMapping("/client/{id}")
    public ResponseEntity<?> removeCliente(@PathVariable Long id) {
        try {
            Cliente eliminado = clienteService.removeCliente(id);
            return ResponseEntity.ok(eliminado);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error interno: " + ex.getMessage());
        }
    }


}
