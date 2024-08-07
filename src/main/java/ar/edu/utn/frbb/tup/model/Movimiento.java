package ar.edu.utn.frbb.tup.model;
import java.time.LocalDateTime;
import java.util.Objects;

import ar.edu.utn.frbb.tup.controller.dto.MovimientosDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import ar.edu.utn.frbb.tup.model.enums.TipoMoneda;
import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;
import ar.edu.utn.frbb.tup.model.Cuenta;
import com.fasterxml.jackson.annotation.JsonView;

public class Movimiento{

    @JsonView(Views.Public.class)
    private TipoOperacion tipoOperacion;

    @JsonView(Views.Public.class)
    private Double monto;

    @JsonView(Views.Public.class)
    private LocalDateTime fecha;

    @JsonView(Views.Public.class)
    private TipoMoneda tipoMoneda;

    @JsonView(Views.Transferencia.class)
    private long cuentaOrigen;

    @JsonView(Views.Transferencia.class)
    private long cuentaDestino;

    // Este constructor sera utilizado para las operaciones de monto y retiro
    public Movimiento(MovimientosSimplesDto movimientosSimplesDto){
        this.fecha = LocalDateTime.now();
        this.monto = movimientosSimplesDto.getMonto();
        this.tipoMoneda = TipoMoneda.fromString(movimientosSimplesDto.getTipoMoneda());
    }

    public Movimiento(MovimientosDto movimientosDto) {
        this.cuentaDestino = movimientosDto.getCuentaDestino();
        this.cuentaOrigen = movimientosDto.getCuentaOrigen();
        this.fecha = LocalDateTime.now();
        this.monto = movimientosDto.getMonto();
        this.tipoMoneda = TipoMoneda.fromString(movimientosDto.getTipoMoneda());
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public double getMonto() {
        return monto;
    }

    public TipoMoneda getTipoMoneda() {
        return tipoMoneda;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public long getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(long cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public long getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(long cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }


    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    @Override
    public String toString() {
            return "\n Tipo de Operacion: " + getTipoOperacion() + "\n Monto: " + getMonto() + "\n Fecha: " + getFecha();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimiento that = (Movimiento) o;
        return tipoMoneda == that.tipoMoneda;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tipoMoneda);
    }
}
