package ar.edu.utn.frbb.tup.controller.dto;

import ar.edu.utn.frbb.tup.model.enums.TipoMoneda;
import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;

import java.time.LocalDateTime;

public class MovimientosDto {
    private Double monto;
    private long cuentaOrigen;
    private long cuentaDestino;
    private String tipoMoneda;

    public Double getMonto() {
        return monto;
    }

    public long getCuentaDestino() {
        return cuentaDestino;
    }

    public long getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }
}
