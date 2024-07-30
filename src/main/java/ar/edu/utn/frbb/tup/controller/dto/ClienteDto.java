package ar.edu.utn.frbb.tup.controller.dto;

public class ClienteDto extends PersonaDto{
    private String tipoPersona;
    private String banco;

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
}
