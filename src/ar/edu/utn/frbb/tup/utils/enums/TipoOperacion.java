package ar.edu.utn.frbb.tup.utils.enums;

public enum TipoOperacion {

    DEPOSITO("D"),
    RETIRO("R"),
    TRANSFERENCIA("T");

    private final String descripcion;

    TipoOperacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoOperacion fromString(String text) {
        for (TipoOperacion tipo : TipoOperacion.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No se pudo encontrar un TipoOperacion con la descripcion: " + text);
    }
}
