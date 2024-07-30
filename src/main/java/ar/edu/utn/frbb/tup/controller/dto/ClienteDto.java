package ar.edu.utn.frbb.tup.controller.dto;

import ar.edu.utn.frbb.tup.model.enums.TipoPersona;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.function.Supplier;

public class ClienteDto extends PersonaDto  {

    @JsonProperty("tipoPersona")
    private TipoPersona tipoPersona;

    @JsonProperty("banco")
    private String banco;


    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
