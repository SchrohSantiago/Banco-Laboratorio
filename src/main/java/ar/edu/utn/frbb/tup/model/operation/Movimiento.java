package ar.edu.utn.frbb.tup.model.operation;
import java.time.LocalDateTime;

import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;

public class Movimiento {
    private Long id;
    private TipoOperacion tipoOperacion;
    private double monto;
    private LocalDateTime fecha;
    private String cuentaId;

    public Movimiento() {}
    public Movimiento(TipoOperacion tipoOperacion, double monto, String cuentaId) {
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.cuentaId = cuentaId;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(String cuentaId) {
        this.cuentaId = cuentaId;
    }

    @Override
    public String toString() {
            return "\n Tipo de Operacion: " + getTipoOperacion() + "\n Monto: " + getMonto() + "\n Fecha: " + getFecha();
    }
}
