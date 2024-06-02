package ar.edu.utn.frbb.tup.presentation.subprocess;

import java.util.List;
import java.util.Scanner;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.service.ClienteService;
import ar.edu.utn.frbb.tup.service.CuentaService;
import ar.edu.utn.frbb.tup.service.OperacionService;

public class OperacionesInputProcessor {
    private Scanner scanner = new Scanner(System.in);
    private ClienteService clienteService = new ClienteService();
    private OperacionService operacionService = new OperacionService();
    private CuentaService cuentaService = new CuentaService();

    public void realizarOperacion() {
        System.out.println("Ingrese el DNI del cliente:");
        Long dni = scanner.nextLong();

        Cliente cliente = clienteService.buscarClientePorDni(dni);
        if (cliente == null) {
            System.out.println("No se encontro un cliente con el DNI ingresado.");
            return;
        }

        System.out.println("Ingrese el numero de cuenta:");
        Long numeroCuenta = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(numeroCuenta);

        if(cuenta == null){
            System.out.println("No se encontro una cuenta con el numero ingresado.");
            return;
        }

        System.out.println("Ingrese el monto de la operacion:");
        Double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero



        System.out.println("Seleccione el tipo de operacion:");
        System.out.println("1. Retiro");
        System.out.println("2. Deposito");
        System.out.println("3. Transferencia");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

        switch (opcion) {
            case 1:
                operacionService.retirar(numeroCuenta, monto);
                break;
            case 2:
                operacionService.depositar(numeroCuenta, monto);
                break;
            case 3:
                System.out.println("Ingrese el numero de cuenta de destino:");
                Long numeroCuentaDestino = scanner.nextLong();
                scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

                Cuenta cuentaDestino = cuentaService.buscarCuentaPorNumero(numeroCuentaDestino);
                if(cuentaDestino == null){
                    System.out.println("No se encontro una cuenta con el numero ingresado.");
                    return;
                }

                operacionService.transferir(numeroCuenta, numeroCuentaDestino, opcion);
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }
    }
}
   
   

    
