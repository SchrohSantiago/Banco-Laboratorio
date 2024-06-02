package ar.edu.utn.frbb.tup.model.exceptions;

public class TipoCuentaAlreadyExistsException extends Throwable {
    public TipoCuentaAlreadyExistsException(String message) {
        super(message);
    }
}