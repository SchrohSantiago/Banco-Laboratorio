package ar.edu.utn.frbb.tup.controller;


import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.controller.validator.ClienteValidator;
import ar.edu.utn.frbb.tup.exceptions.ClienteNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.EdadInvalidaException;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.exceptions.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    @Autowired
    private ClienteValidator clienteValidator;

    @Autowired
    private ClienteService clienteService;


    @PostMapping
    public Cliente crearCliente(@RequestBody ClienteDto clienteDto) throws ClienteAlreadyExistsException, EdadInvalidaException {
        clienteValidator.validate(clienteDto);
        return clienteService.darAltaCliente(clienteDto);
    }

    @GetMapping("/{dni}")
    public Cliente obtenerClientePorDni(@PathVariable long dni) throws ClienteNotFoundException {
        return clienteService.buscarClientePorDni(dni);
    }

    @DeleteMapping("/{dni}")
    public Cliente eliminarClientePorDni(@PathVariable long dni) throws ClienteNotFoundException {
        return clienteService.eliminarClientPorDni(dni);
    }

    @PutMapping("/{dni}")
    public Cliente editarClientePorDni(@PathVariable long dni) throws  ClienteNotFoundException {}

}
