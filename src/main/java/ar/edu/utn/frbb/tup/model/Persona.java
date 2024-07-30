package ar.edu.utn.frbb.tup.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected Long dni;
    protected LocalDate fechaNacimiento;
    protected String direccion;
    protected String telefono;

    public Persona(){};

    public Persona(String apellido, String direccion, Long dni, String fechaNacimiento, String nombre, String telefono) {
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
        this.nombre = nombre;
        this.telefono = telefono;
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

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
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

