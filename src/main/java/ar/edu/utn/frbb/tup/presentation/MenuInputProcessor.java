package ar.edu.utn.frbb.tup.presentation;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.model.Banco;
import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.presentation.subprocess.*;
import ar.edu.utn.frbb.tup.service.ClienteService;

public class MenuInputProcessor extends BaseInputProcessor {
    private ClienteInputProcessor clienteInputProcessor = new ClienteInputProcessor();
    private CuentaInputProcessor cuentaInputProcessor = new CuentaInputProcessor();
    private OperacionesInputProcessor operacionesInputProcessor = new OperacionesInputProcessor();
    private MovimientosInputProcessor movimientosInputProcessor = new MovimientosInputProcessor();
    private ShowInfoCliente showInfoCliente;
    private ClienteService clienteService = new ClienteService();


    private boolean exit = false;

    public void renderMenu() {
        
        List<Cuenta> cuentas = new ArrayList<>();
        Cuenta cuenta = null; // Initialize cuenta to null

    
        while (!exit) {
            System.out.println("Bienvenido a la aplicacion de Banco");
            System.out.println("1. Crear un nuevo Cliente");
            System.out.println("2. Crear una nueva Cuenta");
            System.out.println("3. Generar un movimiento");
            System.out.println("4. Ver movimientos de una cuenta");
            System.out.println("5. Ver lista de clientes");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opcion (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
                continue;
            }

            switch (choice) {
                case 1:
                    clienteInputProcessor.ingresarCliente();
                    break;
                case 2:
                    cuentaInputProcessor.crearCuenta();
                    break;
                case 3:
                  
                    break;
                case 4:
                   
                    break;
                case 5:
                    clienteService.mostrarTodosLosClientes();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor seleccione 1-6.");
                    break;
            }
        }
    }

   

   
}
