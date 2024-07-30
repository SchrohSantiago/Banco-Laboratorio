package ar.edu.utn.frbb.tup.model;
import java.time.LocalDateTime;

import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;
import ar.edu.utn.frbb.tup.model.Cuenta;

public class Movimiento{

    private TipoOperacion tipoOperacion;
    private Double monto;
    private LocalDateTime fecha;
    private Cuenta cuenta;

    public Movimiento(Double monto, TipoOperacion tipoOperacion, Cuenta cuenta) {
        this.fecha = LocalDateTime.now();
        this.monto = monto;
        this.tipoOperacion = tipoOperacion;
        this.cuenta = cuenta;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public double getMonto() {
        return monto;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }


    @Override
    public String toString() {
            return "\n Tipo de Operacion: " + getTipoOperacion() + "\n Monto: " + getMonto() + "\n Fecha: " + getFecha();
    }
}
