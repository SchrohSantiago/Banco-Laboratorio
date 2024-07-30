package ar.edu.utn.frbb.tup.controller.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PersonaDto {

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("dni")
    private long dni;

    @JsonProperty("fechaNacimiento")
    private String fechaNacimiento;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("telefono")
    private String telefono;


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


    public String getDireccion() {
        return direccion;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
