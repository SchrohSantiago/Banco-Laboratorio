package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;
import ar.edu.utn.frbb.tup.model.operation.Movimiento;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;

public class OperacionService {
    private CuentaDao cuentaDao = new CuentaDao();

    public void depositar(Long numeroCuenta, double monto) {
        Cuenta cuenta = cuentaDao.find(numeroCuenta);
        if (cuenta != null) {
            cuenta.setBalance(cuenta.getBalance() + monto);
            Movimiento movimiento = new Movimiento(TipoOperacion.DEPOSITO, monto, cuenta.getId());
            cuenta.agregarMovimiento(movimiento);
            cuentaDao.save(cuenta);
            System.out.println("Nuevo Balance: " + cuenta.getBalance());
        } else {
            System.out.println("La cuenta no existe.");
        }
    }

    public void retirar(Long numeroCuenta, double monto) {
        Cuenta cuenta = cuentaDao.find(numeroCuenta);
        if (cuenta != null) {
            if (cuenta.getBalance() >= monto) {
                cuenta.setBalance(cuenta.getBalance() - monto);
                Movimiento movimiento = new Movimiento(TipoOperacion.RETIRO, monto, cuenta.getId());
                cuenta.agregarMovimiento(movimiento);
                cuentaDao.save(cuenta);
                System.out.println("Nuevo Balance: " + cuenta.getBalance());
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("La cuenta no existe.");
        }
    }

    public void transferir(Long numeroCuentaOrigen, Long numeroCuentaDestino, double monto) {
        Cuenta cuentaOrigen = cuentaDao.find(numeroCuentaOrigen);
        Cuenta cuentaDestino = cuentaDao.find(numeroCuentaDestino);

        if (cuentaOrigen != null && cuentaDestino != null) {
            if (cuentaOrigen.getBalance() >= monto) {
                cuentaOrigen.setBalance(cuentaOrigen.getBalance() - monto);
                cuentaDestino.setBalance(cuentaDestino.getBalance() + monto);

                Movimiento movimientoOrigen = new Movimiento(TipoOperacion.TRANSFERENCIA, monto, cuentaOrigen.getId());
                Movimiento movimientoDestino = new Movimiento(TipoOperacion.TRANSFERENCIA, monto, cuentaDestino.getId());

                cuentaOrigen.agregarMovimiento(movimientoOrigen);
                cuentaDestino.agregarMovimiento(movimientoDestino);

                cuentaDao.save(cuentaOrigen);
                cuentaDao.save(cuentaDestino);

                System.out.println("Nuevo Balance de la cuenta: " + cuentaOrigen.getBalance());
            } else {
                System.out.println("Saldo insuficiente en la cuenta origen.");
            }
        } else {
            System.out.println("Una o ambas cuentas no existen.");
        }
    }
}
