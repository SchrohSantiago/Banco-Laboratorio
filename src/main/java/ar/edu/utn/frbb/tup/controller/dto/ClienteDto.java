package ar.edu.utn.frbb.tup.controller.dto;

public class ClienteDto extends PersonaDto{
    private String tipoPersona;
    private String banco;

    public String getTipoPersona() {
        return tipoPersona;
    }

    public String getBanco() {
        return banco;
    }
}
