package ar.edu.utn.frbb.tup.controller.validator;

import java.util.Objects;

public class Validator { // En validator van a ir las validaciones generales, para reutilizar mas el codigo

    public static void validateNotNullOrEmpty(Object valor, String nombreCampo) {
        if (Objects.isNull(valor) || valor.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("El " + nombreCampo + " no puede ser null o estar vacio");
        }
    }


}
