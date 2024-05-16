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

    private boolean exit = false;

    public void renderMenu(Banco banco) {
        List<Cliente> clientes = banco.getClientes();
        List<Cuenta> cuentas = new ArrayList<>();

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
                    System.out.println("Por favor introduzca el DNI del cliente al que le crearemos la cuenta:");
                    String dni = scanner.nextLine();

                    Cliente clienteEncontrado = buscarClientePorDni(dni, clientes);
                    if (clienteEncontrado != null) {
                        Cuenta cuenta = cuentaInputProcessor.crearCuenta(clienteEncontrado);
                        clienteEncontrado.addCuenta(cuenta);
                        cuentas.add(cuenta);
                        System.out.println("Cuenta creada exitosamente para el cliente con DNI " + dni
                                + ". Numero de cuenta: " + cuenta.getNumeroCuenta()
                                + ", Saldo inicial: " + cuenta.getBalance()
                                + ", Tipo de cuenta: " + cuenta.getTipoCuenta().name());
                    } else {
                        System.out.println("El cliente no existe en la lista del banco.");
                    }
                    break;
                case 3:
                    operacionesInputProcessor.realizarOperacion(clientes);
                    break;
                case 4:
                    System.out.println("Ingrese el DNI del cliente:");
                    String dniCliente = scanner.nextLine();

                    Cliente clienteEncontrado2 = buscarClientePorDni(dniCliente, clientes);
                    if (clienteEncontrado2 != null) {
                        System.out.println("Ingrese el numero de cuenta:");
                        int numeroCuenta = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

                        Cuenta cuentaEncontrada = buscarCuentaPorNumero(numeroCuenta, clienteEncontrado2.getCuentas());
                        if (cuentaEncontrada != null) {
                            System.out.println("Movimientos de la cuenta " + numeroCuenta + ":");
                            List<Movimiento> movimientos = cuentaEncontrada.getMovimientos();
                            if (movimientos.isEmpty()) {
                                System.out.println("No hay movimientos registrados para esta cuenta.");
                            } else {
                                for (Movimiento movimiento : movimientos) {
                                    System.out.println(movimiento);
                                }
                            }
                        } else {
                            System.out.println("No se encontro una cuenta con el numero ingresado.");
                        }
                    } else {
                        System.out.println("No se encontro un cliente con el DNI ingresado.");
                    }
                    break;
                case 5:
                    System.out.println("Lista de clientes creados:");
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes creados.");
                    } else {
                        for (int i = 0; i < clientes.size(); i++) {
                            Cliente clienteActual = clientes.get(i);
                            System.out.println("=== Cliente " + (i + 1) + " ===");
                            System.out.println("Nombre: " + clienteActual.getNombre());
                            System.out.println("DNI: " + clienteActual.getDni());
                            System.out.println("Direccion: " + clienteActual.getDireccion());
                            System.out.println("Telefono: " + clienteActual.getTelefono());
                            System.out.println("===================");
                        }
                    }
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

    private Cliente buscarClientePorDni(String dni, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }

    private Cuenta buscarCuentaPorNumero(int numeroCuenta, List<Cuenta> cuentas) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }
}
