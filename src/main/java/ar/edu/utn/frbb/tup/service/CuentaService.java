package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    CuentaDao cuentaDao = new CuentaDao();

    public Cuenta darDeAltaCuenta(CuentaDetalladaDto cDto) {
        Cuenta cuenta = new Cuenta(cDto);

        if(cuentaDao.find(cDto.getNumeroCuenta())!= null) {
            throw new RuntimeException("La cuenta ya existe");
        }
        cuentaDao.save(cuenta);
        return cuenta;
    }

    public Cuenta buscarCuentaPorNumero(long numeroCuenta) {
        if(cuentaDao.find(numeroCuenta) == null) {
            throw new RuntimeException("La cuenta no existe");
        }

        return cuentaDao.find(numeroCuenta);
    }
}
