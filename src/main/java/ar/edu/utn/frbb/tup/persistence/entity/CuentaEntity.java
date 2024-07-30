package ar.edu.utn.frbb.tup.persistence.entity;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.model.Movimiento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CuentaEntity extends BaseEntity{
    String nombre;
    LocalDateTime fechaCreacion;
    double balance;
    String tipoCuenta;
    Long titular;
    long numeroCuenta;

    List<Movimiento> movimientos = new ArrayList<>();
    
    public CuentaEntity(Cuenta cuenta) {
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.balance = cuenta.getBalance();
        this.tipoCuenta = cuenta.getTipoCuenta().toString();
        this.titular = cuenta.getTitular().getDni();
        this.fechaCreacion = cuenta.getFechaCreacion();
        this.movimientos = cuenta.getMovimientos();
    }

    public Cuenta toCuenta() {
        Cuenta cuenta = new Cuenta();

        cuenta.setBalance(this.balance);
        cuenta.setNumeroCuenta(this.numeroCuenta);
        cuenta.setTipoCuenta(TipoCuenta.valueOf(this.tipoCuenta));
        cuenta.setFechaCreacion(this.fechaCreacion);
        Cliente cliente = new ClienteDao().find(this.titular);
        cuenta.setTitular(cliente);
        cuenta.setMovimientos(this.movimientos);
        
        return cuenta;
    }
}