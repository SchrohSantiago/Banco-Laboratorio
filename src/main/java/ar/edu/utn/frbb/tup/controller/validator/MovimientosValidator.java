package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import org.springframework.stereotype.Component;

@Component
public class MovimientosValidator {
    public void validateMovimientoDto(MovimientosDto movimientosDto) {
        if (movimientosDto == null) {  // Validamos que no sea un JSON null
            throw new IllegalArgumentException("La operacion es null");
        }

        // Validamos los campos individualmente para ser mas especificos
        Validator.validateNotNullOrEmpty(movimientosDto.getMonto(),"monto");
        Validator.validateNotNullOrEmpty(movimientosDto.getCuentaOrigen(),"cuentaOrigen");
        Validator.validateNotNullOrEmpty(movimientosDto.getCuentaDestino(),"cuentaDestino");
        Validator.validateNotNullOrEmpty(movimientosDto.getTipoMoneda(), "tipoMoneda");
    }

    public void validateMovimientoSimpleDto(MovimientosSimplesDto movimientosDto) {
        if (movimientosDto == null) {  // Validamos que no sea un JSON null
            throw new IllegalArgumentException("La operacion es null");
        }

        // Validamos los campos individualmente para ser mas especificos
        Validator.validateNotNullOrEmpty(movimientosDto.getMonto(),"monto");
        Validator.validateNotNullOrEmpty(movimientosDto.getTipoMoneda(), "tipoMoneda");
    }


}
