package ar.edu.utn.frbb.tup.persistence.entity;


import java.time.LocalDateTime;

import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;
import ar.edu.utn.frbb.tup.model.operation.Movimiento;
import ar.edu.utn.frbb.tup.model.person.Cuenta;


public class MovimientoEntity extends BaseEntity {
    private final TipoOperacion tipoOperacion;
    private final double monto;
    private final LocalDateTime fecha;


    public MovimientoEntity(Movimiento movimiento) {
        this.tipoOperacion = movimiento.getTipoOperacion();
        this.monto = movimiento.getMonto();
        this.fecha = movimiento.getFecha();
    }

    

    public Movimiento toMovimiento(Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipoOperacion(this.tipoOperacion);
        movimiento.setMonto(this.monto);
        movimiento.setFecha(this.fecha);

        return movimiento;
    }
}