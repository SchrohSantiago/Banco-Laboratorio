package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.MovimientosDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.CuentaSinFondosException;
import ar.edu.utn.frbb.tup.exceptions.DiferenteMonedaException;
import ar.edu.utn.frbb.tup.model.enums.TipoMoneda;
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

    @Autowired
    BanelcoService banelcoService;

    public void depositar(MovimientosSimplesDto movimientosSimplesDto) throws CuentaNotFoundException, DiferenteMonedaException {
        Cuenta cuenta = cuentaDao.find(movimientosSimplesDto.getNumeroCuenta());

        if (cuenta != null) {
            Movimiento movimiento = new Movimiento(movimientosSimplesDto);
            if (cuenta.getTipoMoneda().equals(movimiento.getTipoMoneda())) {
                cuenta.setBalance(cuenta.getBalance() + movimientosSimplesDto.getMonto());
                movimiento.setTipoOperacion(TipoOperacion.DEPOSITO);
                cuenta.addMovimiento(movimiento);
                cuentaDao.save(cuenta);
            } else {
                throw new DiferenteMonedaException("No coinciden las monedas");
            }
        } else {
            throw new CuentaNotFoundException("El numero de cuenta ingresado no coincide con una cuenta existente");
        }
    }

    public void retirar(MovimientosSimplesDto movimientosSimplesDto) throws CuentaNotFoundException, CuentaSinFondosException {
        Cuenta cuenta = cuentaDao.find(movimientosSimplesDto.getNumeroCuenta());
        if (cuenta != null) {
            if (cuenta.getBalance() >= movimientosSimplesDto.getMonto()) {
                cuenta.setBalance(cuenta.getBalance() - movimientosSimplesDto.getMonto());
                Movimiento movimiento = new Movimiento(movimientosSimplesDto);
                movimiento.setTipoOperacion(TipoOperacion.RETIRO);
                cuenta.addMovimiento(movimiento);
                cuentaDao.save(cuenta);
            } else {
                throw new CuentaSinFondosException("El monto supera al dinero disponible en la cuenta");
            }
        } else {
            throw new CuentaNotFoundException("El numero de cuenta ingresado no coincide con una cuenta existente");
        }
    }

    public void transferir(MovimientosDto movimientosDto) throws CuentaNotFoundException, CuentaSinFondosException, DiferenteMonedaException {
        Cuenta cuentaOrigen = cuentaDao.find(movimientosDto.getCuentaOrigen());
        Cuenta cuentaDestino = cuentaDao.find(movimientosDto.getCuentaDestino());

        if (cuentaOrigen != null) {
            Movimiento movimiento = new Movimiento(movimientosDto);

            if (cuentaOrigen.getTipoMoneda().equals(movimiento.getTipoMoneda())) {  // Validamos que el tipo de moneda que se introduce en el JSON sea el mismo que la cuenta

                if (cuentaDestino != null) { // Si la cuenta destino no existe en nuestro banco "Utilizamos" el servicio Banelco

                    if (cuentaOrigen.getBalance() >= movimientosDto.getMonto()) {  // Claramente el balance de la cuenta tiene que ser mayor al monto a transferir

                        if (cuentaOrigen.getTipoMoneda().equals(cuentaDestino.getTipoMoneda())) { // Si el tipo de moneda de la cuenta origen coincide con el tipo de moneda de la cuentaDestino


                            cuentaOrigen.setBalance(cuentaOrigen.getBalance() - movimientosDto.getMonto()); // A la cuenta origen se le "retiran" ejemplo 10000 dolares
                            cuentaDestino.setBalance(cuentaDestino.getBalance() + (movimientosDto.getMonto() - calcularDescuento(movimientosDto))); // A la cuenta destino le llega el monto con el descuento incluido

                            registrarTransferencias(movimientosDto, cuentaOrigen, cuentaDestino); // Incluimos este metodo para hacer mas legible el codigo
                        } else {
                            throw new DiferenteMonedaException("Las monedas entre cuentas debe ser la misma");
                        }
                    } else {
                        throw new CuentaSinFondosException("El monto supera al dinero disponible en la cuenta");
                    }

                } else {
                    // Invocacion al servicio balenco
                    invocacionServicioBanelco(movimiento, movimientosDto, cuentaOrigen);
                }
            } else {
                throw new DiferenteMonedaException("Son diferentes monedas");
            }
        } else {
            throw new CuentaNotFoundException("La cuenta de origen no existe");
        }
    }

    public double calcularDescuento(MovimientosDto movimientosDto){
        double descuento;
        // Expresion ternaria si la moneda es igual a PESO el cargo es de 2% caso contrario %0.5
        double cargo = movimientosDto.getTipoMoneda().equals("P") ? 0.02 : 0.005;

        // Dependiendo el tipo de moneda es el monto minimo en el cual se cobra el recargo, en el caso de que la transferencia segun su tipo de moneda sea menor al maximo monto sin recargo devuelve 0
        double montoCargo = movimientosDto.getMonto() > 1000000d && movimientosDto.getTipoMoneda().equals("P")
                || movimientosDto.getMonto() > 5000d && movimientosDto.getTipoMoneda().equals("D") ? cargo : 0d;


        return descuento = montoCargo > 0d ? movimientosDto.getMonto() * cargo : 0d; // Si el montoCargo es mayor a 0 significa que el monto supera el maximo de transferencia sin cargo, por ende directamente multiplica el monto por el porcentaje de recago, caso contrario devuelve 0
    }

    public void registrarTransferencias(MovimientosDto movimientosDto, Cuenta cuentaOrigen, Cuenta cuentaDestino){
        Movimiento movimientoOrigen = new Movimiento(movimientosDto);
        Movimiento movimientoDestino = new Movimiento(movimientosDto);

        movimientoOrigen.setTipoOperacion(TipoOperacion.TRANSFERENCIA);
        movimientoDestino.setTipoOperacion(TipoOperacion.TRANSFERENCIA);

        cuentaOrigen.addMovimiento(movimientoOrigen);
        cuentaDestino.addMovimiento(movimientoDestino);

        cuentaDao.save(cuentaOrigen);
        cuentaDao.save(cuentaDestino);
    }

    public void invocacionServicioBanelco(Movimiento movimiento, MovimientosDto movimientosDto, Cuenta cuentaOrigen) throws CuentaNotFoundException, CuentaSinFondosException {
        if (cuentaOrigen.getBalance() >= movimientosDto.getMonto()) {

            Boolean transferenciaExterna = banelcoService.transferenciaBanelco( // invocamos a la simulacion del  servicio Banelco el cual retorna de manera random un numero entre 1 a 100, si el numero es menor a 70 la cuenta "existe"
                    // cuentaOrigen.getNumeroCuenta(),
                    // cuentaDestino.getNumeroCuenta(),
                    // movimientosDto.getMonto()
            );

            if (transferenciaExterna) {
                cuentaOrigen.setBalance(cuentaOrigen.getBalance() - movimientosDto.getMonto());
                movimiento.setTipoOperacion(TipoOperacion.TRANSFERENCIA);
                cuentaOrigen.addMovimiento(movimiento);
                cuentaDao.save(cuentaOrigen);

                System.out.println("Transferencia externa exitosa.");
            } else {
                throw new CuentaNotFoundException("La cuenta externa no existe");
            }
        } else {
            throw new CuentaSinFondosException("No posee los fondos suficientes");
        }
    }
}



