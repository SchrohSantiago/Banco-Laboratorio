package ar.edu.utn.frbb.tup.controller.dto;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;

public class CuentaDto {
    private String dni;
    private Double balance;
    private TipoCuenta tipoCuenta;

    public Double getBalance() {
        return balance;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public String getDni() {
        return dni;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }



    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
