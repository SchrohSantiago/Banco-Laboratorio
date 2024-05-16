package ar.edu.utn.frbb.tup.process;

import ar.edu.utn.frbb.tup.utils.Cuenta;
import ar.edu.utn.frbb.tup.utils.enums.TipoCuenta;
import ar.edu.utn.frbb.tup.utils.Cliente;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Random;

public class CuentaInputProcessor {

    private Scanner scanner = new Scanner(System.in);

    public Cuenta crearCuenta(Cliente cliente) {
        Cuenta cuenta = new Cuenta();
        
        // Asignar el cliente a la cuenta
        cuenta.setCliente(cliente);

        // Establecer la fecha de creación como la fecha y hora actual
        cuenta.setFechaCreacion(LocalDateTime.now());

        // Establecer el balance inicial en 0
        cuenta.setBalance(0);

        // Generamos el numero de cuenta aleatorio sin que se repita con otro anteriormente creado
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
                System.out.println("Error! Opcion invalida");
                break;
        }

        return cuenta;
    }

   
}
