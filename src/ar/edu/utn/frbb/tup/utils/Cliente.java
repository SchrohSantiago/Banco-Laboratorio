package ar.edu.utn.frbb.tup.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random; // Libreria de java para generar numeros random, en este caso para nuestro CBU 
import java.util.Set;
import java.util.UUID; // importamos UUID para generar un id

import ar.edu.utn.frbb.tup.utils.enums.TipoPersona;

public class Cliente extends Persona {
    private String id;
    private String direccion;
    private String telefono;
    private TipoPersona tipoPersona;
    private String banco;
    private LocalDate fechaAlta;
    private List<Cuenta> cuentas = new ArrayList<>();
    private String cbu;

    public Cliente(){};
    public Cliente(String nombre, String apellido, String dni, String direccion, String telefono, String banco) {
        super(nombre, apellido, dni);
        this.id = UUID.randomUUID().toString();
        this.direccion = direccion;
        this.telefono = telefono;
        this.banco = banco;
        this.cbu = generarCbu();
    }
    
    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta) {
        this.cuentas.remove(cuenta);
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public String getCbu(){
        return cbu;
    }

    public void setCbu(String cbu){
        this.cbu = cbu;
    }

    public String generarCbu() {
        Random random = new Random();
        StringBuilder randomCbu = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            int digito = random.nextInt(10); // Generar un número aleatorio entre 0 y 9
            randomCbu.append(digito);
        }

        return randomCbu.toString();
    }    
}
