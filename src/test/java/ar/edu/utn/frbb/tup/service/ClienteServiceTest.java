package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.exceptions.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.exceptions.ClienteNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.EdadInvalidaException;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAgregarCliente() throws EdadInvalidaException, ClienteAlreadyExistsException {
        ClienteDto clienteDto = new ClienteDto("Lanzev", "Brasil 873", 44882709, "1980-04-05", "Jorge", "29167534", "BBVA", "F");
        when(clienteDao.find(clienteDto.getDni())).thenReturn(null);

        clienteService.darAltaCliente(clienteDto);

        verify(clienteDao, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testAgregarClienteExistente() throws EdadInvalidaException, ClienteAlreadyExistsException {
        // Crea un objeto ClienteDto con los datos esperados
        ClienteDto clienteDto = new ClienteDto("Lanzev", "Brasil 873", 44882709, "1980-04-05", "Jorge", "29167534", "BBVA", "F");
        Cliente cliente = new Cliente(clienteDto);

        // Configura el mock para devolver el objeto Cliente cuando se busque por DNI
        when(clienteDao.find(clienteDto.getDni())).thenReturn(cliente);

        // Verifica que se lanza la excepcion
        assertThrows(ClienteAlreadyExistsException.class, () -> {
            clienteService.darAltaCliente(clienteDto);
        });
    }

    @Test
    public void testEliminarClienteExistente() throws ClienteNotFoundException {
        ClienteDto clienteDto = new ClienteDto("Lanzev", "Brasil 873", 44882709, "1980-04-05", "Jorge", "29167534", "BBVA", "F");
        Cliente cliente = new Cliente(clienteDto);
        when(clienteDao.find(cliente.getDni())).thenReturn(cliente);
        clienteService.eliminarClientPorDni(cliente.getDni());
        verify(clienteDao, times(1)).delete(cliente);
    }

    @Test
    public void testAgregarClienteEdadInvalida() {
        ClienteDto clienteDto = new ClienteDto("Lanzev", "Brasil 873", 44882709, "2010-04-05", "Jorge", "29167534", "BBVA", "F");

        // Configura el mock para que no devuelva nada
        when(clienteDao.find(clienteDto.getDni())).thenReturn(null);

        // Verifica que se lance una excepcion de EdadInvalidaException
        assertThrows(EdadInvalidaException.class, () -> {
            clienteService.darAltaCliente(clienteDto);
        });
    }
}
