package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;

public class CuentaService {
    CuentaDao cuentaDao = new CuentaDao();

    public void darDeAltaCuenta(Cuenta cuenta) {
        if(cuentaDao.find(cuenta.getNumeroCuenta())!= null) {
            throw new RuntimeException("La cuenta ya existe");
        }
        cuentaDao.save(cuenta);
    }   

    public Cuenta buscarCuentaPorNumero(long numeroCuenta) {
        if(cuentaDao.find(numeroCuenta) == null) {
            throw new RuntimeException("La cuenta no existe");
        }

        return cuentaDao.find(numeroCuenta);
    }
}
