package ar.edu.utn.frbb.tup.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.model.enums.TipoPersona;
import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;

public class ClienteEntity extends BaseEntity {
    private final String tipoPersona;
    private final String nombre;  // Declaramos a todos los atributos como final para que no puedan ser modificados después de la creación de la entidad 
    private final String apellido;
    private final LocalDate fechaAlta;
    private final LocalDate fechaNacimiento;
    private final String direccion;
    private final String telefono;
    private final String banco;
    private final String cbu;
    private List<Long> cuentas;

    public ClienteEntity(Cliente cliente) {  // Pasamos por parametros al objeto cliente
        super(Long.parseLong(cliente.getId())); // Usa el ID del cliente
        this.tipoPersona = cliente.getTipoPersona() != null ? cliente.getTipoPersona().getDescripcion() : null;
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.fechaAlta = cliente.getFechaAlta();
        this.fechaNacimiento = cliente.getFechaNacimiento();
        this.direccion = cliente.getDireccion();
        this.telefono = cliente.getTelefono();
        this.banco = cliente.getBanco();
        this.cbu = cliente.getCbu();
        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            this.cuentas = new ArrayList<>();
            for (Cuenta c : cliente.getCuentas()) {
                cuentas.add(c.getNumeroCuenta());
            }
        }
    }

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre(this.nombre);
        cliente.setApellido(this.apellido);
        cliente.setTipoPersona(TipoPersona.fromString(this.tipoPersona));
        cliente.setFechaAlta(this.fechaAlta);
        cliente.setFechaNacimiento(this.fechaNacimiento);
        cliente.setDireccion(this.direccion);
        cliente.setTelefono(this.telefono);
        cliente.setBanco(this.banco);
        cliente.setCbu(this.cbu);
        cliente.setId(this.getId().toString());
        return cliente;
    }

    public void addCuenta(Cuenta cuenta) {
        if (cuentas == null) {
            cuentas = new ArrayList<>();
        }
        cuentas.add(cuenta.getNumeroCuenta());
    }
}
