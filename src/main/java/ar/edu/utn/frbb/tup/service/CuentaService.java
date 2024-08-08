package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.exceptions.ClienteNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.MaximoCuentasException;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    @Autowired
    CuentaDao cuentaDao;

    @Autowired
    ClienteDao clienteDao;

    public Cuenta darDeAltaCuenta(CuentaDetalladaDto cDto) throws CuentaAlreadyExistsException, ClienteNotFoundException, MaximoCuentasException {
        Cliente cliente = clienteDao.find(cDto.getDniTitular());

        if (cliente == null){
            throw new ClienteNotFoundException("El cliente no existe");
        }

        long numCuentasCT = cliente.getCuentas().stream()   // Stream es la programacion funcional de java, utiliza expresiones lambda y metodos que nos provee dicha libreria como Reduce, Map, Filter
                .filter(c -> c.getTipoCuenta().equals(TipoCuenta.CUENTA_CORRIENTE)) // Lo que hace es filtrar por cada tipo de Cuenta Corriente y luego utiliza el count() que basicamente es un contador
                .count();
        long numCuentasCA = cliente.getCuentas().stream()
                .filter(c -> c.getTipoCuenta().equals(TipoCuenta.CAJA_AHORRO))  // Esto mismo con cada tipo de cuenta
                .count();
        long numCuentasPF = cliente.getCuentas().stream()
                .filter(c -> c.getTipoCuenta().equals(TipoCuenta.PLAZO_FIJO))
                .count();

        // Verificar los limites de cuentas
        // Entonces por decision propia en nuestro banco un cliente puede tener maximo dos Cuenta Corrientes, dos Cajas de Ahorro y una sola cuenta de Plazo Fijo
        if (cDto.getTipoCuenta().equals("CT") && numCuentasCT >= 2) {
            throw new MaximoCuentasException("El cliente ya tiene el máximo permitido de 2 cuentas corrientes");
        } else if (cDto.getTipoCuenta().equals("CA") && numCuentasCA >= 2) {
            throw new MaximoCuentasException("El cliente ya tiene el máximo permitido de 2 cuentas de Caja de Ahorro");
        } else if (cDto.getTipoCuenta().equals("PF") && numCuentasPF >= 1) {
            throw new MaximoCuentasException("El cliente ya tiene el máximo permitido de 1 cuenta a plazo fijo");
        }

        Cuenta cuenta = new Cuenta(cDto);  // Tenemos que instancias a un objeto de la clase Cuenta para poder utilizarlo en el metodo cuentaDao.find

        if(cuentaDao.find(cuenta.getNumeroCuenta()) != null) {  //
            throw new CuentaAlreadyExistsException("La cuenta ya existe");
        }

        cuentaDao.save(cuenta);
        cliente.addCuenta(cuenta);

        return cuenta;
    }

    public Cuenta buscarCuentaPorNumero(long numeroCuenta) throws CuentaNotFoundException {
        if(cuentaDao.find(numeroCuenta) == null) {
            throw new CuentaNotFoundException("La cuenta no existe");
        }

        return cuentaDao.find(numeroCuenta);
    }
}
