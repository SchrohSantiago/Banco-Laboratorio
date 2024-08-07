package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.controller.dto.MovimientosDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import ar.edu.utn.frbb.tup.controller.validator.MovimientosValidator;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.CuentaSinFondosException;
import ar.edu.utn.frbb.tup.exceptions.DiferenteMonedaException;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.model.Views;
import ar.edu.utn.frbb.tup.service.CuentaService;
import ar.edu.utn.frbb.tup.service.MovimientosService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    CuentaService cuentaService;

    @Autowired
    MovimientosService movimientosService;

    @Autowired
    private MovimientosValidator movimientosValidator;

    @JsonView(Views.Public.class)    // Utilizamos Jackson para que a la hora de ver la respuesta en JSON en cuanto a los depositos o retiros no muestre "Cuenta Origen" y "CuentaDestino"
    @PostMapping("/deposito")
    public Movimiento deposito(@RequestBody MovimientosSimplesDto movimientosSimplesDto) throws CuentaNotFoundException, DiferenteMonedaException {
        movimientosValidator.validateMovimientoSimpleDto(movimientosSimplesDto);
        movimientosService.depositar(movimientosSimplesDto);
        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(movimientosSimplesDto.getNumeroCuenta());
        return cuenta.getMovimientos().getLast(); // Utilizamos el metodo getLast para que devuelva el ultimo movimiento de la LinkedList
    }

    @JsonView(Views.Public.class)
    @PostMapping("/retiro")
    public Movimiento retiro(@RequestBody MovimientosSimplesDto movimientosSimplesDto) throws CuentaNotFoundException, CuentaSinFondosException {
        movimientosValidator.validateMovimientoSimpleDto(movimientosSimplesDto);
        movimientosService.retirar(movimientosSimplesDto);
        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(movimientosSimplesDto.getNumeroCuenta());
        return cuenta.getMovimientos().getLast();
    }

    @JsonView(Views.Transferencia.class)
    @PostMapping("/transferencia")
    public Movimiento transferencia(@RequestBody MovimientosDto movimientosDto) throws CuentaNotFoundException, CuentaSinFondosException, DiferenteMonedaException {
        movimientosValidator.validateMovimientoDto(movimientosDto);
        movimientosService.transferir(movimientosDto);
        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(movimientosDto.getCuentaOrigen());
        return cuenta.getMovimientos().getLast();
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{numeroCuenta}")  // Endpoint para ver los movimientos de una cuenta especifica....
    public LinkedList<Movimiento> obtenerMovimientos(@PathVariable long numeroCuenta) throws CuentaNotFoundException {
        Cuenta cuenta = cuentaService.buscarCuentaPorNumero(numeroCuenta);
        return cuenta.getMovimientos();
    }

}
