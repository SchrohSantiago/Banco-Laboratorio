package ar.edu.utn.frbb.tup.presentation.subprocess;

import java.util.List;
import java.util.Scanner;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.model.operation.Movimiento;

public class MovimientosInputProcessor {
    private ClienteDao clienteDao = new ClienteDao();

    public void mostrarMovimientos(Cuenta cuenta){
       

        System.out.println("Ingrese el DNI del cliente:");
        Scanner scanner = new Scanner(System.in);
        Long dniCliente = scanner.nextLong();

        Cliente clienteEncontrado2 = clienteDao.find(dniCliente);
        if (clienteEncontrado2 != null) {
            System.out.println("Ingrese el numero de cuenta:");
            int numeroCuenta = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de linea despues de leer el entero
            System.out.println(numeroCuenta);

            Cuenta cuentaEncontrada = cuenta.buscarCuentaPorNumero(numeroCuenta, clienteEncontrado2.getCuentas());
            System.out.println(cuentaEncontrada);

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
    }
}