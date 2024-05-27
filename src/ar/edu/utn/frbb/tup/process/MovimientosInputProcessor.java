package ar.edu.utn.frbb.tup.process;

import java.util.List;
import java.util.Scanner;
import ar.edu.utn.frbb.tup.utils.Banco;
import ar.edu.utn.frbb.tup.utils.Cliente;
import ar.edu.utn.frbb.tup.utils.Cuenta;
import ar.edu.utn.frbb.tup.utils.Movimiento;

public class MovimientosInputProcessor {

    public void mostrarMovimientos(Banco banco, Cuenta cuenta){
       

        System.out.println("Ingrese el DNI del cliente:");
        Scanner scanner = new Scanner(System.in);
        String dniCliente = scanner.nextLine();

        Cliente clienteEncontrado2 = banco.buscarClientePorDni(dniCliente);
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