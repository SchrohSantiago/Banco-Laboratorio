package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClienteValidator {

    public void validate(ClienteDto clienteDto) {
        if (clienteDto == null) {  // Validamos que no sea un JSON null
            throw new IllegalArgumentException("El cliente es null");
        }

        // Validamos los campos individualmente para ser mas especificos
        Validator.validateNotNullOrEmpty(clienteDto.getNombre(),"nombre");
        Validator.validateNotNullOrEmpty(clienteDto.getApellido(),"apellido");
        Validator.validateNotNullOrEmpty(clienteDto.getBanco(),"banco");
        Validator.validateNotNullOrEmpty(clienteDto.getTipoPersona(),"tipo persona");
        Validator.validateNotNullOrEmpty(clienteDto.getDni(),"dni");
        Validator.validateNotNullOrEmpty(clienteDto.getDireccion(),"direccion");
        Validator.validateNotNullOrEmpty(clienteDto.getTelefono(),"telefono");
        Validator.validateNotNullOrEmpty(clienteDto.getFechaNacimiento(),"fecha nacimiento");

        try {
            LocalDate.parse(clienteDto.getFechaNacimiento());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el formato de fecha");
        }
    }

    public void editValidate(ClienteDto clienteDto){
        if(clienteDto == null){
            throw new IllegalArgumentException("El cliente es null");
        }
        
        boolean camposInvalidos = clienteDto.getNombre() != null ||
                clienteDto.getApellido() != null ||
                clienteDto.getFechaNacimiento() != null ||
                clienteDto.getTipoPersona() != null;

        if(camposInvalidos) {
            throw new IllegalArgumentException("Los unicos campos que se pueden modificar son: DIRECCION, TELEFONO y BANCO");
        }
    }


}
