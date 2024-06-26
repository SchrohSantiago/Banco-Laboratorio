package ar.edu.utn.frbb.tup.presentation.subprocess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.service.ClienteService;
import ar.edu.utn.frbb.tup.model.enums.TipoPersona;

public class ClienteInputProcessor extends BaseInputProcessor {

    private static List<Cliente> clientes = new ArrayList<>();
    ClienteService clienteService = new ClienteService();

    public ClienteInputProcessor() {}
    public ClienteInputProcessor(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente ingresarCliente() {

        // Ingreso de datos del Cliente
        Cliente cliente = new Cliente();
       
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();
        cliente.setNombre(nombre);

        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.nextLine();
        cliente.setApellido(apellido);

        System.out.println("Ingrese el dni:");
        Long dni = Long.parseLong(scanner.nextLine());
        cliente.setDni(dni);

        System.out.println("Ingrese el tipo de persona Fisica(F) o Juridica(J):");
        String tipoPersonaStr = scanner.nextLine().toUpperCase();
        while (!tipoPersonaStr.equals("F") && !tipoPersonaStr.equals("J")) {
            System.out.println("Tipo de persona invalido. Ingrese NATURAL o JURIDICA:");
            tipoPersonaStr = scanner.nextLine().toUpperCase();
        }
        TipoPersona tipoPersona = TipoPersona.fromString(tipoPersonaStr);
        cliente.setTipoPersona(tipoPersona);

        System.out.println("Ingrese el banco del cliente:");
        String banco = scanner.nextLine();
        cliente.setBanco(banco);

        System.out.println("Ingrese la fecha de alta del cliente (Formato: YYYY-MM-DD):");
        LocalDate fechaAlta = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fechaAlta = LocalDate.parse(scanner.nextLine());
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha invalido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }
        cliente.setFechaAlta(fechaAlta);

        cliente.setCbu(cliente.generarCbu());

        System.out.println("Ingrese el telefono del cliente:");
        String telefono = scanner.nextLine();
        cliente.setTelefono(telefono);

        System.out.println("Ingrese la direccion del cliente:");
        String direccion = scanner.nextLine();
        cliente.setDireccion(direccion);

        clienteService.darAltaCliente(cliente);

        clearScreen();
        return cliente;
    }
}

