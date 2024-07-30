package ar.edu.utn.frbb.tup.controller.dto;

import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;

import java.time.LocalDateTime;

public class MovimientosDto {
    private TipoOperacion tipoOperacion;
    private Double monto;
    private LocalDateTime fecha;

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }
}
