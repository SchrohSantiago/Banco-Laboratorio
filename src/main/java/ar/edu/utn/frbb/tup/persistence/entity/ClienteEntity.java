package ar.edu.utn.frbb.tup.persistence.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frbb.tup.model.enums.TipoPersona;
import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;

public class ClienteEntity extends BaseEntity {
    private final String tipoPersona;
    private final String nombre;
    private final String apellido;
    private final LocalDate fechaAlta;
    private final LocalDate fechaNacimiento;
    private final String direccion;
    private final String telefono;
    private final String cbu;
    private final Long dni;

    private List<Cuenta> cuentas; // Cambio de List<Long> a List<Cuenta>

    public ClienteEntity(Cliente cliente) {// Convertimos el objeto Cliente a ClienteEntity para poder guardarlo en la base de datos ENTRADA
        this.dni = cliente.getDni();
        this.tipoPersona = cliente.getTipoPersona() != null ? cliente.getTipoPersona().getDescripcion() : null;
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.fechaAlta = cliente.getFechaAlta();
        this.fechaNacimiento = cliente.getFechaNacimiento();
        this.direccion = cliente.getDireccion();
        this.telefono = cliente.getTelefono();
        this.cbu = cliente.getCbu();
        this.cuentas = new ArrayList<>();
        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            this.cuentas.addAll(cliente.getCuentas()); // Agregar todas las cuentas del cliente
        }
    }

    public void addCuenta(Cuenta cuenta) {
        if (cuentas == null) {
            cuentas = new ArrayList<>();
        }
        cuentas.add(cuenta); // Agregar la cuenta a la lista de cuentas
    }

    

    public Cliente toCliente() { // Convertimos el objeto ClienteEntity a Cliente para poder usarlo en el programa SALIDA
        Cliente cliente = new Cliente();
        cliente.setDni(this.dni);
        cliente.setNombre(this.nombre);
        cliente.setDireccion(this.direccion);
        cliente.setTelefono(this.telefono);
        cliente.setCbu(this.cbu);
        cliente.setCuentas(this.cuentas);
        cliente.setApellido(this.apellido);
        cliente.setTipoPersona(TipoPersona.fromString(this.tipoPersona));
        cliente.setFechaAlta(this.fechaAlta);
        cliente.setFechaNacimiento(this.fechaNacimiento);
        cliente.setCuentas(this.cuentas); // Establecer la lista de cuentas del cliente

        return cliente;
    }
}
