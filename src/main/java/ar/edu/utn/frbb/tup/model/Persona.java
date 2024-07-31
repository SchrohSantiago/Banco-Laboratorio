package ar.edu.utn.frbb.tup.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected long dni;
    protected LocalDate fechaNacimiento;
    protected String telefono;
    protected String direccion;


    public Persona(){};

    public Persona(long dni, String apellido, String nombre, String fechaNacimiento, String telefono, String direccion) {
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
}

