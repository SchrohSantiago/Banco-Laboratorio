package ar.edu.utn.frbb.tup.controller.dto;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;

import java.time.LocalDateTime;
import java.util.List;

public class CuentaDetalladaDto {  // Creamos dos DTO de la clase Cuenta
    private long dniTitular;
    private String tipoCuenta;
    private String tipoMoneda;

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public long getDniTitular() {
        return dniTitular;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }
}
