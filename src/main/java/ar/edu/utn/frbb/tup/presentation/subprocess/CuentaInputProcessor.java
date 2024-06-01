package ar.edu.utn.frbb.tup.presentation.subprocess;

import java.time.LocalDateTime;
import java.util.Scanner;

import ar.edu.utn.frbb.tup.model.Banco;
import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;

public class CuentaInputProcessor {

    private Scanner scanner = new Scanner(System.in);

    public Cuenta crearCuenta(Banco banco) {
        System.out.println("Por favor introduzca el DNI del cliente al que le crearemos la cuenta:");
        String dni = scanner.nextLine();

        Cliente clienteEncontrado = banco.buscarClientePorDni(dni);
        
        if (clienteEncontrado != null) {
            Cuenta cuenta = new Cuenta();
           
            // Asignar el cliente a la cuenta
            cuenta.setCliente(clienteEncontrado);

            // Establecer la fecha de creación como la fecha y hora actual
            cuenta.setFechaCreacion(LocalDateTime.now());

            // Establecer el balance inicial en 0
            cuenta.setBalance(0);

            // Generamos el número de cuenta aleatorio sin que se repita con otro anteriormente creado
            cuenta.generarNumeroCuenta();

            // Preguntar al cliente el tipo de cuenta
            System.out.println("Seleccione el tipo de cuenta:");
            System.out.println("1. Caja de Ahorro");
            System.out.println("2. Cuenta Corriente");
            System.out.println("3. Plazo Fijo");
            int number = scanner.nextInt();

            switch (number) {
                case 1:
                    cuenta.setTipoCuenta(TipoCuenta.CAJA_AHORRO);
                    break;
                case 2:
                    cuenta.setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE);
                    break;
                case 3:
                    cuenta.setTipoCuenta(TipoCuenta.PLAZO_FIJO);
                    break;
                default:
                    System.out.println("Error! Opción inválida");
                    break;
            }

           
            clienteEncontrado.addCuenta(cuenta);
            System.out.println("Cuenta creada exitosamente para el cliente con DNI " + dni
                    + ". Numero de cuenta: " + cuenta.getNumeroCuenta()
                    + ", Saldo inicial: " + cuenta.getBalance()
                    + ", Tipo de cuenta: " + cuenta.getTipoCuenta().name());

            return cuenta;
        } else {
            System.out.println("El cliente no existe en la lista del banco.");
            return null;
        }
    }
}
