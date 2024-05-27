package ar.edu.utn.frbb.tup.model.operation;

import java.time.LocalDateTime;

import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;


public class Movimiento {
    private LocalDateTime fechaHora;
    private TipoOperacion tipoOperacion;
    private double monto;

    public Movimiento(LocalDateTime fechaHora, TipoOperacion tipoOperacion, double monto) {
        this.fechaHora = fechaHora;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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

   

    @Override
    public String toString() {
        return "Movimiento{" +
                "fechaHora=" + fechaHora +
                ", tipoOperacion=" + tipoOperacion +
                ", monto=" + monto +
                '}';
    }
}
