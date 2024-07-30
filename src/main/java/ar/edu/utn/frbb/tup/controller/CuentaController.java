package ar.edu.utn.frbb.tup.controller;


import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;




    @PostMapping
    public Cuenta crearCuenta(@RequestBody CuentaDetalladaDto clienteDto)  {
        return cuentaService.darDeAltaCuenta(clienteDto);
    }
}

