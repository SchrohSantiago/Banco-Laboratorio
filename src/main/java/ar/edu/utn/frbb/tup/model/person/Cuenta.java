package ar.edu.utn.frbb.tup.model.person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;
import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;
import ar.edu.utn.frbb.tup.model.operation.Movimiento;

public class Cuenta {
    private String id;
    private Cliente cliente; 
    private TipoCuenta tipoCuenta;
    private long numeroCuenta;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private double balance = 0.0; // Cambiamos el tipo de balance a double para manejar montos decimales
    List<Movimiento> movimientos = new ArrayList<>();
    List<Cuenta> numeroCuentas = new ArrayList<>();

    public Cliente getCliente() {
        return cliente;
    }

    public Cuenta setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public String getId() {
        return id;
    }

    public Cuenta setId(String id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public Cuenta setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Cuenta setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Cuenta setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public void depositar(double monto) {
        this.balance += monto; 
        registrarMovimiento(TipoOperacion.DEPOSITO, monto);
    }

    public void retirar(double monto) {
        if (this.balance >= monto) {
            this.balance -= monto;
            registrarMovimiento(TipoOperacion.RETIRO, monto);
        } else {
            System.out.println("Saldo insuficiente para realizar el retiro");
        }
    }

    public void transferir(Cuenta cuentaDestino, double monto) {
        if (this.balance >= monto) {
            this.balance -= monto;
            cuentaDestino.depositar(monto);
            setBalance(balance); 
            registrarMovimiento(TipoOperacion.TRANSFERENCIA, monto);
            cuentaDestino.registrarMovimiento(TipoOperacion.TRANSFERENCIA, monto);
        } else {
            System.out.println("Saldo insuficiente para realizar la transferencia");
        }
    }
    

    public void registrarMovimiento(TipoOperacion tipoOperacion, double monto) {
        LocalDateTime fechaHora = LocalDateTime.now();
        Movimiento movimiento = new Movimiento(fechaHora, tipoOperacion, monto);
        movimientos.add(movimiento);
    }

  

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public List<Cuenta> getNumeroCuentas() {
        return numeroCuentas;
    }

    public void addNumeroCuenta(Cuenta cuenta) {
        numeroCuentas.add(cuenta);
    }

    public void generarNumeroCuenta() {
        // Creamos un numero de cuenta random
        int numeroCuenta = (int) (Math.random() * 1000000);
    
        this.setNumeroCuenta(numeroCuenta);
    }

    public void mostrarNumerosCuenta(List<Cuenta> cuentas) {
        System.out.println("Lista de numeros de cuenta creados:");
        for (Cuenta cuenta : cuentas) {
            System.out.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
        }
    }

    public Cuenta buscarCuentaPorNumero(int numeroCuenta, List<Cuenta> cuentas) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "cliente=" + cliente +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", balance=" + balance +
                '}';
    }
}
