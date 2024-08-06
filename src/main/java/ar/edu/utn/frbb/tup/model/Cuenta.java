package ar.edu.utn.frbb.tup.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;
import ar.edu.utn.frbb.tup.model.enums.TipoMoneda;

public class Cuenta {
    private TipoMoneda tipoMoneda;
    private long dniTitular;
    private TipoCuenta tipoCuenta;
    private long numeroCuenta;
    private LocalDateTime fechaCreacion;
    private Double balance = 0d; // Cambiamos el tipo de balance a double para manejar montos decimales
    List<Movimiento> movimientos;

    public Cuenta(){};
    public Cuenta(CuentaDetalladaDto cuentaDto) {
        this.tipoMoneda = TipoMoneda.fromString(cuentaDto.getTipoMoneda());
        this.balance = getBalance();
        this.dniTitular = cuentaDto.getDniTitular();
        this.fechaCreacion = LocalDateTime.now();
        this.movimientos = new ArrayList<>();   // Cuando se crea un objeto de cuenta se genera un ArrayList vacio para los movimientos
        this.numeroCuenta = generarNumeroCuenta(); // Se genera un numero de cuenta automatico con la creacion del objeto
        this.tipoCuenta = TipoCuenta.fromString(cuentaDto.getTipoCuenta());
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(TipoMoneda tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public long getDniTitular() {
        return dniTitular;
    }

    public void setDniTitular(long dniTitular) {
        this.dniTitular = dniTitular;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Double getBalance() {
        return balance;
    }

    public void addMovimiento(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public long generarNumeroCuenta() {
        // Creamos un numero de cuenta random
        return (int) (Math.random() * 1000000);
    }


    @Override
    public String toString() {
        return "Cuenta{" +
                "cliente=" + dniTitular +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return tipoMoneda == cuenta.tipoMoneda;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tipoMoneda);
    }
}
