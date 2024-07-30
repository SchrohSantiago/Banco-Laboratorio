package ar.edu.utn.frbb.tup.controller.dto;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;

import java.time.LocalDateTime;
import java.util.List;

public class CuentaDetalladaDto {  // Creamos dos DTO de la clase Cuenta
    private Cliente titular;
    private long numeroCuenta;
    private Double balance;
    private TipoCuenta tipoCuenta;
    private LocalDateTime fechaCreacion;
    List<MovimientosDto> movimientosDto;

    public Cliente getTitular() {
        return titular;
    }

    public Double getBalance() {
        return balance;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public List<MovimientosDto> getMovimientosDto() {
        return movimientosDto;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }
}
