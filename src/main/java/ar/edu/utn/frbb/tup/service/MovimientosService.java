package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.MovimientosDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientosService {
    @Autowired
    CuentaDao cuentaDao;

    public void depositar(MovimientosSimplesDto movimientosSimplesDto) {
        Cuenta cuenta = cuentaDao.find(movimientosSimplesDto.getNumeroCuenta());
        if (cuenta != null) {
            cuenta.setBalance(cuenta.getBalance() + movimientosSimplesDto.getMonto());
            Movimiento movimiento = new Movimiento(movimientosSimplesDto);
            movimiento.setTipoOperacion(TipoOperacion.DEPOSITO);
            cuenta.addMovimiento(movimiento);
            cuentaDao.save(cuenta);
        } else {
            System.out.println("La cuenta no existe.");
        }
    }

    public void retirar(MovimientosSimplesDto movimientosSimplesDto) {
        Cuenta cuenta = cuentaDao.find(movimientosSimplesDto.getNumeroCuenta());
        if (cuenta != null) {
            if (cuenta.getBalance() >= movimientosSimplesDto.getMonto()) {
                cuenta.setBalance(cuenta.getBalance() - movimientosSimplesDto.getMonto());
                Movimiento movimiento = new Movimiento(movimientosSimplesDto);
                movimiento.setTipoOperacion(TipoOperacion.RETIRO);
                cuenta.addMovimiento(movimiento);
                cuentaDao.save(cuenta);
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("La cuenta no existe.");
        }
    }

    public void transferir(MovimientosDto movimientosDto) {
        Cuenta cuentaOrigen = cuentaDao.find(movimientosDto.getCuentaOrigen());
        Cuenta cuentaDestino = cuentaDao.find(movimientosDto.getCuentaDestino());

        if (cuentaOrigen != null && cuentaDestino != null) {
            if (cuentaOrigen.getBalance() >= movimientosDto.getMonto()) {
                cuentaOrigen.setBalance(cuentaOrigen.getBalance() - movimientosDto.getCuentaOrigen());
                cuentaDestino.setBalance(cuentaDestino.getBalance() + movimientosDto.getCuentaOrigen());

                Movimiento movimientoOrigen = new Movimiento(movimientosDto);
                Movimiento movimientoDestino = new Movimiento(movimientosDto);

                cuentaOrigen.addMovimiento(movimientoOrigen);
                cuentaDestino.addMovimiento(movimientoDestino);

                cuentaDao.save(cuentaOrigen);
                cuentaDao.save(cuentaDestino);
            } else {
                System.out.println("Saldo insuficiente en la cuenta origen.");
            }
        } else {
            System.out.println("Una o ambas cuentas no existen.");
        }
    }
}
