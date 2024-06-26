package ar.edu.utn.frbb.tup.model.person;

import java.time.LocalDate;

public class Persona {
    private String nombre;
    private String apellido;
    private Long dni;
    private LocalDate fechaNacimiento;
   
    public Persona(){}
    public Persona(String nombre, String apellido, Long dni) {
        this.nombre = getNombre();
        this.apellido = getApellido();
        this.dni = getDni();
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


}

