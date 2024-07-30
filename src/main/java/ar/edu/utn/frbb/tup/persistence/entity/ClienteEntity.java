package ar.edu.utn.frbb.tup.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frbb.tup.model.enums.TipoPersona;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;

public class ClienteEntity extends BaseEntity {
    private final String tipoPersona;
    private final String nombre;
    private final String apellido;
    private final LocalDate fechaAlta;
    private final LocalDate fechaNacimiento;
    private final String cbu;
    private final Long dni;
    private final String banco;

    private List<Cuenta> cuentas;

    public ClienteEntity(Cliente cliente) {
        // Asignar un ID único aquí si es necesario
        this.dni = cliente.getDni();
        this.tipoPersona = cliente.getTipoPersona() != null ? cliente.getTipoPersona().getDescripcion() : null;
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.fechaAlta = cliente.getFechaAlta();
        this.fechaNacimiento = cliente.getFechaNacimiento();
        this.banco = cliente.getBanco();
        this.cbu = cliente.getCbu();
        this.cuentas = new ArrayList<>();
        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            this.cuentas.addAll(cliente.getCuentas());
        }
    }

    public void addCuenta(Cuenta cuenta) {
        if (cuentas == null) {
            cuentas = new ArrayList<>();
        }
        cuentas.add(cuenta);
    }

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setDni(this.dni);
        cliente.setNombre(this.nombre);
        cliente.setCbu(this.cbu);
        cliente.setApellido(this.apellido);
        cliente.setTipoPersona(TipoPersona.fromString(this.tipoPersona));
        cliente.setFechaAlta(this.fechaAlta);
        cliente.setCuentas(this.cuentas);
        cliente.setBanco(this.banco);
        cliente.setFechaNacimiento(this.fechaNacimiento);

        return cliente;
    }
}
