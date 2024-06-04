package ar.edu.utn.frbb.tup.model.person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.edu.utn.frbb.tup.model.enums.TipoCuenta;
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

    public Cuenta() {}
    public Cuenta(Cliente cliente, TipoCuenta tipoCuenta, long numeroCuenta, String nombre, double balance, List<Movimiento> movimientos) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único para cada cuenta
        this.cliente = getCliente();
        this.tipoCuenta = getTipoCuenta();
        this.numeroCuenta = getNumeroCuenta();
        this.nombre = getNombre();
        this.fechaCreacion = getFechaCreacion();
        this.balance = getBalance();
        this.movimientos = getMovimientos();
    }

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

    public void agregarMovimiento(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
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
