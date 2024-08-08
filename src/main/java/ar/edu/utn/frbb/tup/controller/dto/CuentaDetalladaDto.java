package ar.edu.utn.frbb.tup.controller.dto;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;

import java.time.LocalDateTime;
import java.util.List;

public class CuentaDetalladaDto {
    private long dniTitular;
    private String tipoCuenta;
    private String tipoMoneda;

    public CuentaDetalladaDto(long dniTitular, String tipoCuenta, String tipoMoneda) {
        this.dniTitular = dniTitular;
        this.tipoCuenta = tipoCuenta;
        this.tipoMoneda = tipoMoneda;
    }

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
