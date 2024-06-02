package ar.edu.utn.frbb.tup.model.person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.edu.utn.frbb.tup.model.enums.TipoPersona;

public class Cliente extends Persona {
    private String id;
    private String direccion;
    private String telefono;
    private TipoPersona tipoPersona;
    private String banco;
    private LocalDate fechaAlta;
    private List<Cuenta> cuentas = new ArrayList<>();
    private String cbu;

    public Cliente() {}
    public Cliente(String nombre, String apellido, Long dni, String direccion, String telefono, String banco, LocalDate fechaAlta) {
        super(nombre, apellido, dni);
        this.id = UUID.randomUUID().toString(); // Genera un ID único para cada cliente
        this.direccion = getDireccion();
        this.telefono = getTelefono();
        this.banco = getBanco();
        this.cbu = generarCbu(); // Genera un CBU
        this.fechaAlta = getFechaAlta(); // Genera la fecha de alta
        this.cuentas = getCuentas();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String generarCbu() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 22); // Genera un CBU de 22 caracteres
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni=" + getDni() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", tipoPersona=" + tipoPersona +
                ", banco='" + banco + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", cbu='" + cbu + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cuentas=" + cuentas +
                '}';
    }
}
