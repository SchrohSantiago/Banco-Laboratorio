package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.model.enums.TipoPersona;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class ClienteValidator {

    public void validate(ClienteDto clienteDto) {
        if (clienteDto == null) {  // Validamos que no sea un JSON null
            throw new IllegalArgumentException("El cliente es null");
        }

        // Validamos los campos individualmente para ser mas especificos
        if (Objects.isNull(clienteDto.getNombre()) || clienteDto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser null o estar vacio");
        }

        if (Objects.isNull(clienteDto.getApellido()) || clienteDto.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser null o estar vacio");
        }

        if (Objects.isNull(clienteDto.getFechaNacimiento()) || clienteDto.getFechaNacimiento().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser null o estar vacia");
        }

        if (Objects.isNull(clienteDto.getBanco()) || clienteDto.getBanco().trim().isEmpty()) {
            throw new IllegalArgumentException("El banco no puede ser null o estar vacio");
        }

        try {
            LocalDate.parse(clienteDto.getFechaNacimiento());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el formato de fecha");
        }
    }
}
