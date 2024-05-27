package ar.edu.utn.frbb.tup.presentation.subprocess;

import java.util.List;
import java.util.Scanner;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;

public class OperacionesInputProcessor {
    private Scanner scanner = new Scanner(System.in);

    public void realizarOperacion(List<Cliente> clientes) {
        System.out.println("Ingrese el DNI del cliente:");
        String dni = scanner.nextLine();

        Cliente cliente = buscarClientePorDni(dni, clientes);
        if (cliente == null) {
            System.out.println("No se encontro un cliente con el DNI ingresado.");
            return;
        }

        System.out.println("Seleccione el tipo de operacion:");
        System.out.println("1. Retiro");
        System.out.println("2. Deposito");
        System.out.println("3. Transferencia");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

        switch (opcion) {
            case 1:
                realizarRetiro(cliente);
                break;
            case 2:
                realizarDeposito(cliente);
                break;
            case 3:
                realizarTransferencia(clientes, cliente);
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }

    private void realizarRetiro(Cliente cliente) {
        System.out.println("Ingrese el numero de cuenta:");
        int numeroCuenta = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

        Cuenta cuenta = buscarCuentaPorNumero(numeroCuenta, cliente.getCuentas());
        if (cuenta == null) {
            System.out.println("No se encontro una cuenta con el numero ingresado.");
            return;
        }

        System.out.println("Ingrese el monto a retirar:");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el double

        cuenta.retirar(monto);
        System.out.println("Retiro realizado exitosamente. Nuevo saldo: " + cuenta.getBalance());
    }

    private void realizarDeposito(Cliente cliente) {
        System.out.println("Ingrese el numero de cuenta:");
        int numeroCuenta = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

        Cuenta cuenta = buscarCuentaPorNumero(numeroCuenta, cliente.getCuentas());
        if (cuenta == null) {
            System.out.println("No se encontro una cuenta con el numero ingresado.");
            return;
        }

        System.out.println("Ingrese el monto a depositar:");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el double

        cuenta.depositar(monto);
        System.out.println("Deposito realizado exitosamente. Nuevo saldo: " + cuenta.getBalance());
    }

    private void realizarTransferencia(List<Cliente> clientes, Cliente cliente) {
        System.out.println("Ingrese el numero de cuenta origen:");
        int numeroCuentaOrigen = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

        Cuenta cuentaOrigen = buscarCuentaPorNumero(numeroCuentaOrigen, cliente.getCuentas());
        if (cuentaOrigen == null) {
            System.out.println("No se encontro una cuenta origen con el numero ingresado.");
            return;
        }

        System.out.println("Ingrese el DNI del cliente destino:");
        String dniDestino = scanner.nextLine();

        Cliente clienteDestino = buscarClientePorDni(dniDestino, clientes);
        if (clienteDestino == null) {
            System.out.println("No se encontro un cliente destino con el DNI ingresado.");
            return;
        }

        System.out.println("Ingrese el numero de cuenta destino:");
        int numeroCuentaDestino = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

        Cuenta cuentaDestino = buscarCuentaPorNumero(numeroCuentaDestino, clienteDestino.getCuentas());
        if (cuentaDestino == null) {
            System.out.println("No se encontro una cuenta destino con el numero ingresado.");
            return;
        }

        System.out.println("Ingrese el monto a transferir:");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el double

        cuentaOrigen.transferir(cuentaDestino, monto);
        System.out.println("Transferencia realizada exitosamente.");
        System.out.println("Nuevo saldo de la cuenta origen: " + cuentaOrigen.getBalance());
        System.out.println("Nuevo saldo de la cuenta destino: " + cuentaDestino.getBalance());
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
