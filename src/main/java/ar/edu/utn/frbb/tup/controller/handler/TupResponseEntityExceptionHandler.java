package ar.edu.utn.frbb.tup.controller.handler;

import ar.edu.utn.frbb.tup.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TupResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ClienteAlreadyExistsException.class,
            CuentaAlreadyExistsException.class,
            IllegalArgumentException.class,
            EdadInvalidaException.class,
            IllegalStateException.class,
            DiferenteMonedaException.class,
            TipoCuentaAlreadyExistsException.class
    })
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(409); // El error 409 abarca desde conflictos con la logica de negocio hasta conflictos de restricciones de unicidad, ejemplo transferir desde una cuenta en pesos a otra cuenta en dolares es una violacion de la logica de negocio, y en cuanto la unicidad un ejemplo seria si se intenta registrar una cuenta o cliente que ya existe
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {ClienteNotFoundException.class, CuentaNotFoundException.class, ClienteSinCuentasException.class})
    protected ResponseEntity<Object> handleClienteNotFoundException(
            RuntimeException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(404); // indica que el servidor no puede encontrar el recurso solicitado
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {MaximoCuentasException.class})
    protected ResponseEntity<Object> handleMaximoCuentasException(
            RuntimeException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(403);  // El error HTTP 403 "forbidden" es utilizado normalmente para indicar que la operacion esta pohibida por la logica de negocio, en este caso la logica de negocio creada indica que no se pueden mas de n cantidad de cuentas por cliente
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(ex.getMessage());
            body = error;
        }

        return new ResponseEntity<>(body, headers, status);
    }

}