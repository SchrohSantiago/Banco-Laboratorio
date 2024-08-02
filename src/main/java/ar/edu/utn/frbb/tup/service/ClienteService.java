package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.exceptions.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.exceptions.ClienteNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.EdadInvalidaException;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ClienteService {
    @Autowired
    private CuentaDao cuentaDao;

    @Autowired
    private ClienteDao clienteDao;

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public Cliente darAltaCliente(ClienteDto clienteDto) throws ClienteAlreadyExistsException, EdadInvalidaException {
        Cliente cliente = new Cliente(clienteDto);
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(LocalDate.parse(clienteDto.getFechaNacimiento()), fechaActual);

        if (clienteDao.find(cliente.getDni())!= null) {
            throw new ClienteAlreadyExistsException("El cliente ya existe");
        }

        if (periodo.getYears() < 18){
            throw new EdadInvalidaException("Error en crear la cuenta, la persona es menor de edad");
        }


        String dniRegex = "^[0-9]{7,8}$";   // El regex indica que se permiten unicamente numeros del 1 al 9, y debe tener un largo de 7 a 8 numeros
        String dniString = Long.toString(cliente.getDni());
        if (!dniString.matches(dniRegex)) {
            throw new IllegalArgumentException("El DNI no es valido.");
        }

        clienteDao.save(cliente);

        return cliente;
    }   


    public Cliente buscarClientePorDni(long dni) throws ClienteNotFoundException {

        if (clienteDao.find(dni) == null) {
            throw new ClienteNotFoundException("El cliente no existe");
        }
        return clienteDao.find(dni);
    }

    public Cliente eliminarClientPorDni(long dni) throws ClienteNotFoundException {
        try{
            Cliente cliente = clienteDao.find(dni);
            clienteDao.delete(cliente);
            return cliente;
        } catch (RuntimeException e){
            throw new ClienteNotFoundException("El cliente buscado no existe");
        }
    }

    public Cliente editarClientPorDni(long dni, ClienteDto clienteDto) throws ClienteNotFoundException {
       try {
           Cliente cliente = clienteDao.find(dni);

           if (cliente == null) {
               throw new ClienteNotFoundException("El cliente buscado no existe");
           }

           if (!clienteDto.getDireccion().isEmpty()) {
               cliente.setDireccion(clienteDto.getDireccion());
           }

           if (!clienteDto.getBanco().isEmpty()) {
               cliente.setBanco(clienteDto.getBanco());
           }

           if (!clienteDto.getTelefono().isEmpty()) {
               cliente.setTelefono(clienteDto.getTelefono());
           }

           return cliente;

       } catch (RuntimeException e) {
           throw new ClienteNotFoundException("El cliente buscado no existe");
       }
    }
}
