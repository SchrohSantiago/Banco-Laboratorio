package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    CuentaDao cuentaDao = new CuentaDao();

    ClienteDao clienteDao;

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cliente darAltaCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente(clienteDto);

        if (clienteDao.find(cliente.getDni())!= null) {
            throw new RuntimeException("El cliente ya existe");
        }


        clienteDao.save(cliente);

        return cliente;
    }   

    public void agregarCuenta(long dni, Cuenta cuenta) {
        Cliente cliente = clienteDao.find(dni);
        if (cliente == null) {
            throw new RuntimeException("El cliente no existe");
        }
        cuenta.setTitular(cliente);
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
