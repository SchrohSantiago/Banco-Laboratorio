package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.service.CuentaService;
import ar.edu.utn.frbb.tup.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    MovimientosService movimientosService;

    @PostMapping
    public Cuenta deposito(@RequestBody MovimientosSimplesDto movimientosSimplesDto) throws CuentaNotFoundException {
        movimientosService.depositar(movimientosSimplesDto);
        return cuentaService.buscarCuentaPorNumero(movimientosSimplesDto.getNumeroCuenta());
    }

}
