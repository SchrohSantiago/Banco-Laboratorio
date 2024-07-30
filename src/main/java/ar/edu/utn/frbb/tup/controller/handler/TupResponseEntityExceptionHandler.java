package ar.edu.utn.frbb.tup.controller.handler;

import ar.edu.utn.frbb.tup.exceptions.TipoCuentaAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // La anotación @ControllerAdvice en Spring es una característica poderosa que permite manejar excepciones, validar modelos y proporcionar lógica global a todos los controladores de una aplicación
public class TupResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TipoCuentaAlreadyExistsException.class, IllegalArgumentException.class}) // capturar excepciones lanzadas por los controladores y procesarlas de manera personalizada. Esto permite separar la lógica de manejo de excepciones del flujo principal de la aplicación, facilitando la lectura y el mantenimiento del código.
    protected ResponseEntity<Object> handleMateriaNotFound(Exception ex, WebRequest request) {  // Recibe la Exception y  el contexto de la solicitud web en el que ocurrio la excepcion

        String exceptionMessage = ex.getMessage(); // guarda el mensaje de error de la Exception
        CustomApiError error = new CustomApiError();
        error.setErrorCode(302);
        error.setErrorMessage(exceptionMessage); // seteamos el mensaje de error lanzado por la excepcion

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request); // Se llama al método handleExceptionInternal para crear y devolver una respuesta HTTP personalizada.
    }

    @ExceptionHandler(value = { IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(404);
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @Override  // crea y devuelve una respuesta HTTP personalizada
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(ex.getMessage());
            body = error;
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
