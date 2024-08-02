package ar.edu.utn.frbb.tup.controller.dto;

public class MovimientosSimplesDto { // este nuevo DTO es para realizar movimientos de deposito y retiro sin la necesidad de incluir en el JSON cuentaOrigen y cuentaDestino
    private Double monto;
    private String tipoMoneda;
    private long numeroCuenta;

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
