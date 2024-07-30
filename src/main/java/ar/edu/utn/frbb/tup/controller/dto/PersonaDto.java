package ar.edu.utn.frbb.tup.controller.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PersonaDto {
    private String nombre;
    private String apellido;
    private long dni;
    private String fechaNacimiento;

    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
