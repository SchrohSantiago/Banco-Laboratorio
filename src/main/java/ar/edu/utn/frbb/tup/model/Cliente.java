package ar.edu.utn.frbb.tup.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.model.enums.TipoPersona;

public class Cliente extends Persona {

    private TipoPersona tipoPersona;
    private String banco;
    private LocalDate fechaAlta;
    private List<Cuenta> cuentas;
    private String cbu;


    public Cliente(){};
    public Cliente(ClienteDto clienteDto){
        super(clienteDto.getDni(), clienteDto.getApellido(), clienteDto.getNombre(), clienteDto.getFechaNacimiento(), clienteDto.getTelefono(), clienteDto.getDireccion());
        fechaAlta = LocalDate.now();
        tipoPersona = TipoPersona.fromString(clienteDto.getTipoPersona());
        banco = clienteDto.getBanco();
        cbu = generarCbu();
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
        return "\n ///// Cliente ///// \n" +
                " dni: " + getDni() +
                "\n nombre: " + getNombre() + 
                "\n apellido: " + getApellido() + 
                "\n tipoPersona: " + getTipoPersona() +
                "\n banco: " + getBanco() +  
                "\n fechaAlta: " + getFechaAlta() +
                "\n cbu=: " + getCbu() +
                "\n cuentas: " + getCuentas();
    }
}
