package ar.edu.utn.frbb.tup.controller.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Validator { // En validator van a ir las validaciones generales, para reutilizar mas el codigo

    public static void validateNotNullOrEmpty(Object valor, String nombreCampo) {  // Metodo estatico par validar
        if (Objects.isNull(valor) || valor.toString().trim().isEmpty()) {  // Si el movimientoDto, CuentaDto o el Dto que fuese esta vacio arroja la excpetion, o si el atributo despues de ser convertido a String, quitado los espacios y verificado que este vacio, esta vacio tambien arroja la exception
            throw new IllegalArgumentException("El " + nombreCampo + " no puede ser null o estar vacio");
        }
    }
}
