package ar.edu.utn.frbb.tup.controller.dto;

public class MovimientosSimplesDto { // este nuevo DTO es para realizar movimientos de deposito y retiro sin la necesidad de incluir en el JSON cuentaOrigen y cuentaDestino
    private Double monto;
    private String tipoMoneda;
    private long numeroCuenta;

    public MovimientosSimplesDto(Double monto, long numeroCuenta, String tipoMoneda) {
        this.monto = monto;
        this.numeroCuenta = numeroCuenta;
        this.tipoMoneda = tipoMoneda;
    }

    public Double getMonto() {
        return monto;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }
}
