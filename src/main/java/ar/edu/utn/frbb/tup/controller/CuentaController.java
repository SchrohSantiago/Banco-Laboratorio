package ar.edu.utn.frbb.tup.controller;


import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.controller.validator.CuentaValidator;
import ar.edu.utn.frbb.tup.exceptions.ClienteNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.MaximoCuentasException;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaValidator cuentaValidator;

    @Autowired
    private CuentaService cuentaService;


    @PostMapping
    public Cuenta crearCuenta(@RequestBody CuentaDetalladaDto cuentaDto) throws CuentaAlreadyExistsException, ClienteNotFoundException, MaximoCuentasException {
        cuentaValidator.validate(cuentaDto);
        return cuentaService.darDeAltaCuenta(cuentaDto);
    }

    @GetMapping("/{numeroCuenta}")  // Buscar una determinada cuenta por su numero
    public Cuenta obtenerCuenta(@PathVariable long numeroCuenta) throws CuentaNotFoundException {
        return cuentaService.buscarCuentaPorNumero(numeroCuenta);
    }
}

