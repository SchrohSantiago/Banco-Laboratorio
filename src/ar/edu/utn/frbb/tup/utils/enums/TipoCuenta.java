package ar.edu.utn.frbb.tup.utils.enums;

public enum TipoCuenta {
    PLAZO_FIJO("PF"),
    CAJA_AHORRO("CA"),
    CUENTA_CORRIENTE("CT");

    private final String descripcion;

    TipoCuenta(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoCuenta fromString(String text) {
        for (TipoCuenta tipo : TipoCuenta.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No se pudo encontrar un TipoCuenta con la descripcion: " + text);
    }
}
