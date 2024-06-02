package ar.edu.utn.frbb.tup.presentation.subprocess;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.service.ClienteService;


import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ShowInfoCliente {
    private ClienteService clienteService;

    public ShowInfoCliente(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void mostrarInfoCliente(long dni) {
        Cliente cliente = clienteService.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        }
        System.out.println("Información del Cliente: ");
        System.out.println("Dni: " + cliente.getDni());
        System.out.println("Nombre y Apellido: " + cliente.getNombre() + " " + cliente.getApellido());
        System.out.println("Tipo de persona: " + cliente.getTipoPersona());
        System.out.println("Banco: " + cliente.getBanco());
        System.out.println("Fecha de nacimiento: " + cliente.getFechaNacimiento());


        if(cliente.getCuentas().isEmpty()) {
            System.out.println("El cliente no tiene cuentas asociadas.");
        } else {
            System.out.println("Cuentas del Cliente: ");
            System.out.println(cliente.getCuentas());
        }

    }

}