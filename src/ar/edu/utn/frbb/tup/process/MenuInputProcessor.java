package ar.edu.utn.frbb.tup.process;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.utils.Banco;
import ar.edu.utn.frbb.tup.utils.Cliente;
import ar.edu.utn.frbb.tup.utils.Cuenta;
import ar.edu.utn.frbb.tup.utils.Movimiento;

public class MenuInputProcessor extends BaseInputProcessor {
    private ClienteInputProcessor clienteInputProcessor = new ClienteInputProcessor();
    private CuentaInputProcessor cuentaInputProcessor = new CuentaInputProcessor();
    private OperacionesInputProcessor operacionesInputProcessor = new OperacionesInputProcessor();
    private MovimientosInputProcessor movimientosInputProcessor = new MovimientosInputProcessor();


    private boolean exit = false;

    public void renderMenu(Banco banco) {
        List<Cliente> clientes = banco.getClientes();
        List<Cuenta> cuentas = new ArrayList<>();
        Cuenta cuenta = null; // Initialize cuenta to null

        for (Cliente c : clientes) {
            cuentas.addAll(c.getCuentas());
        }

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
                    Cliente cliente = clienteInputProcessor.ingresarCliente();
                    banco.getClientes().add(cliente);
                    break;
                case 2:
                    cuenta = cuentaInputProcessor.crearCuenta(banco);
                    Cliente clienteEncontrado = banco.buscarClientePorDni(cuenta.getCliente().getDni());
                    clienteEncontrado.getCuentas().add(cuenta);
                    break;
                case 3:
                    operacionesInputProcessor.realizarOperacion(clientes);
                    break;
                case 4:
                    movimientosInputProcessor.mostrarMovimientos(banco, cuenta);
                    break;
                case 5:
                    banco.mostrarClientes();
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
