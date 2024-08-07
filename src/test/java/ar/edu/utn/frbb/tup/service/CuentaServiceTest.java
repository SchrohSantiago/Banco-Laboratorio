package ar.edu.utn.frbb.tup.service;


import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.exceptions.ClienteNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuentaServiceTest {

    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDarDeAltaCuentaClienteNoExistente() {
        CuentaDetalladaDto cuentaDetalladaDto = new CuentaDetalladaDto(44882709, "CT", "D");
        when(clienteDao.find(44882709)).thenReturn(null); // Simulamos que el cliente no existe

        assertThrows(ClienteNotFoundException.class, () -> {
            cuentaService.darDeAltaCuenta(cuentaDetalladaDto);
        });
    }


    @Test
    public void testBuscarCuentaPorNumeroExitoso() throws CuentaNotFoundException {
        Cuenta cuenta = new Cuenta(new CuentaDetalladaDto(44882709, "CT", "D"));
        cuenta.setNumeroCuenta(12345L);
        when(cuentaDao.find(12345L)).thenReturn(cuenta);

        Cuenta cuentaEncontrada = cuentaService.buscarCuentaPorNumero(12345L);

        assertNotNull(cuentaEncontrada);
        assertEquals(12345L, cuentaEncontrada.getNumeroCuenta());
    }

    @Test
    public void testBuscarCuentaPorNumeroNoExistente() {
        when(cuentaDao.find(12345L)).thenReturn(null); // Simulamos que la cuenta no existe

        assertThrows(CuentaNotFoundException.class, () -> {
            cuentaService.buscarCuentaPorNumero(12345L);
        });
    }
}
