package ar.edu.utn.frbb.tup.model;

import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;

public class Banco {
    private List<Cliente> clientes;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente buscarClientePorDni(Long dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return cliente; 
            }
        }
        return null;
    }

    public void mostrarClientes() {
        System.out.println("=== Lista de Clientes ===");
        for (Cliente cliente : clientes) {
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Direccion: " + cliente.getDireccion());
            System.out.println("Telefono: " + cliente.getTelefono());
            System.out.println("Cuentas:");
            for (Cuenta cuenta : cliente.getCuentas()) {
                System.out.println("  - Numero de cuenta: " + cuenta.getNumeroCuenta());
                System.out.println("    Tipo de cuenta: " + cuenta.getTipoCuenta().name());
                System.out.println("    Saldo: " + cuenta.getBalance());
            }
            System.out.println("===========================");
        }
    }
}
