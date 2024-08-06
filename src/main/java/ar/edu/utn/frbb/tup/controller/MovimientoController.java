package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.controller.dto.MovimientosDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.CuentaSinFondosException;
import ar.edu.utn.frbb.tup.exceptions.DiferenteMonedaException;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.service.CuentaService;
import ar.edu.utn.frbb.tup.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    MovimientosService movimientosService;

    @PostMapping("/deposito")
    public List<Movimiento> deposito(@RequestBody MovimientosSimplesDto movimientosSimplesDto) throws CuentaNotFoundException, DiferenteMonedaException {
        movimientosService.depositar(movimientosSimplesDto);
        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(movimientosSimplesDto.getNumeroCuenta());
        return cuenta.getMovimientos();
    }

    @PostMapping("/retiro")
    public List<Movimiento> retiro(@RequestBody MovimientosSimplesDto movimientosSimplesDto) throws CuentaNotFoundException, CuentaSinFondosException {
        movimientosService.retirar(movimientosSimplesDto);
        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(movimientosSimplesDto.getNumeroCuenta());
        return cuenta.getMovimientos();
    }

    @PostMapping("/transferencia")
    public List<Movimiento> transferencia(@RequestBody MovimientosDto movimientosDto) throws CuentaNotFoundException, CuentaSinFondosException, DiferenteMonedaException {
        movimientosService.transferir(movimientosDto);
        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(movimientosDto.getCuentaOrigen());
        return cuenta.getMovimientos();
    }

}
