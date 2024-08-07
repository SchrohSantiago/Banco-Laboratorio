package ar.edu.utn.frbb.tup.controller.dto;

public class ClienteDto extends PersonaDto{
    private String tipoPersona;
    private String banco;

    public ClienteDto(String apellido, String direccion, long dni, String fechaNacimiento, String nombre, String telefono, String banco, String tipoPersona) {
        super(apellido, direccion, dni, fechaNacimiento, nombre, telefono);
        this.banco = banco;
        this.tipoPersona = tipoPersona;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public String getBanco() {
        return banco;
    }
}
