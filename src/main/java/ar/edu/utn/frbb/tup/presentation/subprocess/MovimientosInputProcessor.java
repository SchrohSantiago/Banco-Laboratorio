package ar.edu.utn.frbb.tup.presentation.subprocess;

import java.util.List;
import java.util.Scanner;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.service.ClienteService;
import ar.edu.utn.frbb.tup.service.CuentaService;
import ar.edu.utn.frbb.tup.model.operation.Movimiento;

public class MovimientosInputProcessor {
    private ClienteService clienteService = new ClienteService();
    private CuentaService cuentaService = new CuentaService();

    public void mostrarMovimientos(){
       

        System.out.println("Ingrese el DNI del cliente:");
        Scanner scanner = new Scanner(System.in);
        Long dniCliente = scanner.nextLong();

        Cliente clienteEncontrado = clienteService.buscarClientePorDni(dniCliente); // Buscar el cliente por dni

        if (clienteEncontrado != null) { // Si el cliente existe
            System.out.println("Ingrese el numero de cuenta:");
            int numeroCuenta = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de linea despues de leer el entero

            Cuenta cuentaEncontrada = cuentaService.buscarCuentaPorNumero(numeroCuenta);

            if (cuentaEncontrada != null) { // Si la cuenta existe
        
                List<Movimiento> movimientos = cuentaEncontrada.getMovimientos(); // Guardamos los movimientos de esta cuenta en la lista

                if (movimientos.isEmpty()) {
                    System.out.println("No hay movimientos registrados para esta cuenta");
                } else {
                    System.out.println("///// Movimientos de la cuenta a nombre de " + cuentaEncontrada.getNombre() + " /////");
                    for (Movimiento movimiento : movimientos) {
                        System.out.println(movimiento);
                    }
                }
            } else {
                System.out.println("No se encontro una cuenta con el numero ingresado");
            }
        } else {
            System.out.println("No se encontro un cliente con el DNI ingresado");
        }
    }
}