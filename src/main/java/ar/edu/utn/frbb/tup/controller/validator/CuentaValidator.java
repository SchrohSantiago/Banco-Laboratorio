package ar.edu.utn.frbb.tup.controller.validator;


import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import org.springframework.stereotype.Component;


@Component
public class CuentaValidator {
    public void validate(CuentaDetalladaDto cuentaDetdto) {
        if (cuentaDetdto == null) {  // Validamos que no sea un JSON null
            throw new IllegalArgumentException("la cuenta es null");
        }

        // Validamos los campos individualmente para ser mas especificos
        Validator.validateNotNullOrEmpty(cuentaDetdto.getTipoCuenta(),"tipoCuenta");
        Validator.validateNotNullOrEmpty(cuentaDetdto.getDniTitular(),"dniTitular");
        Validator.validateNotNullOrEmpty(cuentaDetdto.getTipoMoneda(),"tipoMoneda");

    }
}
