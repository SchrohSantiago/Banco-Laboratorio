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

    @ExceptionHandler(value = {CuentaAlreadyExistsException.class, IllegalArgumentException.class, EdadInvalidaException.class})
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(409);
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ClienteNotFoundException.class})
    protected ResponseEntity<Object> handleClienteNotFoundException(
            RuntimeException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(404);
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

    @ExceptionHandler(value = { IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(409);
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {ClienteSinCuentasException.class})
    protected ResponseEntity<Object> handleClienteSinCuentasException(
            RuntimeException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorCode(204); // El codigo 204 HTTP indica que la solicitud fue exitosa pero que no hay contendio
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(ex.getMessage());
            body = error;
        }

        return new ResponseEntity(body, headers, status);
    }

}