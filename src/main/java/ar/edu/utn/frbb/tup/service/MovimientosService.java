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
            boolean a = cuentaOrigen.getTipoMoneda().equals(movimiento.getTipoMoneda());
            System.out.println("a = " + a);
            if (cuentaOrigen.getTipoMoneda().equals(movimiento.getTipoMoneda())) {

                if (cuentaDestino != null) {

                    if (cuentaOrigen.getBalance() >= movimientosDto.getMonto()) {

                        if (cuentaOrigen.getTipoMoneda().equals(cuentaDestino.getTipoMoneda())) {

                            // Expresion ternaria si la moneda es igual a PESO el cargo es de 2% caso contrario %0.5
                            double cargo = movimientosDto.getTipoMoneda().equals("P") ? 0.02 : 0.005;
                            System.out.println(cargo);
                            double montoCargo = movimientosDto.getMonto() > 1000000d && movimientosDto.getTipoMoneda().equals("P")
                                    || movimientosDto.getMonto() > 5000d && movimientosDto.getTipoMoneda().equals("D") ? cargo : 0d;
                            System.out.println(montoCargo);

                            double descuento = movimientosDto.getMonto() > 0d ? movimientosDto.getMonto() * cargo : 0d;

                            cuentaOrigen.setBalance(cuentaOrigen.getBalance() - movimientosDto.getMonto());
                            System.out.println(movimientosDto.getMonto() - descuento);
                            cuentaDestino.setBalance(cuentaDestino.getBalance() + (movimientosDto.getMonto() - descuento));

                            Movimiento movimientoOrigen = new Movimiento(movimientosDto);
                            Movimiento movimientoDestino = new Movimiento(movimientosDto);

                            movimientoOrigen.setTipoOperacion(TipoOperacion.TRANSFERENCIA);
                            movimientoDestino.setTipoOperacion(TipoOperacion.TRANSFERENCIA);

                            cuentaOrigen.addMovimiento(movimientoOrigen);
                            cuentaDestino.addMovimiento(movimientoDestino);

                            cuentaDao.save(cuentaOrigen);
                            cuentaDao.save(cuentaDestino);
                        } else {
                            throw new DiferenteMonedaException("Las monedas entre cuentas debe ser la misma");
                        }
                    } else {
                        throw new CuentaSinFondosException("El monto supera al dinero disponible en la cuenta");
                    }

                } else {
                    // Invocacion al servicio balenco
                    Boolean transferenciaExterna = banelcoService.transferenciaBanelco(
                           // cuentaOrigen.getNumeroCuenta(),
                           // cuentaDestino.getNumeroCuenta(),
                           // movimientosDto.getMonto()
                    );

                    System.out.println(transferenciaExterna);

                    if (transferenciaExterna) {
                        cuentaOrigen.setBalance(cuentaOrigen.getBalance() - movimientosDto.getMonto());
                        cuentaDao.save(cuentaOrigen);
                        System.out.println("Transferencia externa exitosa.");
                    } else {
                        throw new CuentaNotFoundException("La cuenta externa no existe");
                    }

                }
            } else {
                throw new DiferenteMonedaException("Son diferentes monedas");
            }
        } else {
            throw new CuentaNotFoundException("La cuenta de origen no existe");
        }
    }
}



