package ar.edu.utn.frbb.tup.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;

public class Cuenta {
    private Cliente titular;
    private TipoCuenta tipoCuenta;
    private long numeroCuenta;
    private LocalDateTime fechaCreacion;
    private Double balance = 0.0; // Cambiamos el tipo de balance a double para manejar montos decimales
    List<Movimiento> movimientos;

    public Cuenta(){};
    public Cuenta(CuentaDetalladaDto cuentaDto) {
        this.balance = 0d;
        this.titular = cuentaDto.getTitular();
        this.fechaCreacion = LocalDateTime.now();
        this.movimientos = new ArrayList<>();
        this.numeroCuenta = generarNumeroCuenta();
        this.tipoCuenta = cuentaDto.getTipoCuenta();
    }

    public Cliente getTitular() {
        return titular;
    }

    public Cuenta setTitular(Cliente titular) {
        this.titular = titular;
        return this;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cuenta setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;

        return this;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public Cuenta setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
        return this;
    }



    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Cuenta setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public Cuenta setBalance(Double balance) {
        this.balance = balance;
        return this;
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
        int numeroCuenta = (int) (Math.random() * 1000000);
    
        return numeroCuenta;
    }


    @Override
    public String toString() {
        return "Cuenta{" +
                "cliente=" + titular +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", balance=" + balance +
                '}';
    }
}
