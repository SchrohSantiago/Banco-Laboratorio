package ar.edu.utn.frbb.tup.controller.handler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true) // Esta anotacion indica que cualquier propiedad desconocida en el JSON entrante sera ignorada
@JsonInclude(JsonInclude.Include.NON_NULL) // Esta anotacion indica que solo se incluiran en el JSON de salida las propiedades que no sean null. Esto ayuda a evitar enviar campos innecesarios en las respuestas.
public class CustomApiError {
    private Integer errorCode;
    private String errorMessage;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
