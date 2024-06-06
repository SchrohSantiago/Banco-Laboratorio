package ar.edu.utn.frbb.tup.service;

import java.util.List;

import ar.edu.utn.frbb.tup.model.person.Cliente;
import ar.edu.utn.frbb.tup.model.person.Cuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;


public class ClienteService {
    ClienteDao clienteDao = new ClienteDao();
    CuentaDao cuentaDao = new CuentaDao();

    public ClienteService() {
        clienteDao = new ClienteDao();
    }

    public void darAltaCliente(Cliente cliente) {
        if (clienteDao.find(cliente.getDni())!= null) {
            throw new RuntimeException("El cliente ya existe");
        }

        
        System.out.println("Cliente dado de alta");
        clienteDao.save(cliente);
        
    }   

    public void agregarCuenta(long dni, Cuenta cuenta) {
        Cliente cliente = clienteDao.find(dni);
        if (cliente == null) {
            throw new RuntimeException("El cliente no existe");
        }
        cuenta.setCliente(cliente);
        cuentaDao.save(cuenta);
        System.out.println("Cuenta agregada");
    }

    public Cliente buscarClientePorDni(long dni) {
        if (clienteDao.find(dni) == null) {
            throw new RuntimeException("El cliente no existe");
        }
        return clienteDao.find(dni);
    }
}
