package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.controller.dto.ClienteDto;
import ar.edu.utn.frbb.tup.controller.dto.CuentaDetalladaDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosDto;
import ar.edu.utn.frbb.tup.controller.dto.MovimientosSimplesDto;
import ar.edu.utn.frbb.tup.exceptions.CuentaNotFoundException;
import ar.edu.utn.frbb.tup.exceptions.CuentaSinFondosException;
import ar.edu.utn.frbb.tup.exceptions.DiferenteMonedaException;
import ar.edu.utn.frbb.tup.model.enums.TipoMoneda;
import ar.edu.utn.frbb.tup.model.enums.TipoOperacion;
import ar.edu.utn.frbb.tup.model.Movimiento;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovimientosServiceTest {

    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private BanelcoService banelcoService;

    @InjectMocks
    private MovimientosService movimientosService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDepositarExitoso() throws CuentaNotFoundException, DiferenteMonedaException {
        // Dado
        MovimientosSimplesDto movimientosSimplesDto = new MovimientosSimplesDto(4000d, 1234l, "D");
        CuentaDetalladaDto cuentaDetalladaDto = new CuentaDetalladaDto(44882709, "CA", "D");
        Cuenta cuenta = new Cuenta(cuentaDetalladaDto);

        when(cuentaDao.find(1234l)).thenReturn(cuenta);

        // Cuando
        movimientosService.depositar(movimientosSimplesDto);

        // Entonces
        assertEquals(4000, cuenta.getBalance());
        verify(cuentaDao, times(1)).save(cuenta);
    }

    @Test
    public void testDepositarMonedaDiferente() throws CuentaNotFoundException {
        MovimientosSimplesDto movimientosSimplesDto = new MovimientosSimplesDto(3200d, 1234, "P");
        CuentaDetalladaDto cuentaDetalladaDto = new CuentaDetalladaDto(44882709, "CA", "D");
        Cuenta cuenta = new Cuenta(cuentaDetalladaDto);

        when(cuentaDao.find(1234l)).thenReturn(cuenta);

        assertThrows(DiferenteMonedaException.class, () -> {
            movimientosService.depositar(movimientosSimplesDto);
        });
    }

    @Test
    public void testDepositarCuentaInexistente() {
        MovimientosSimplesDto movimientosSimplesDto = new MovimientosSimplesDto(3000d, 1234l, "D");
        when(cuentaDao.find(12345l)).thenReturn(null);

        assertThrows(CuentaNotFoundException.class, () -> {
            movimientosService.depositar(movimientosSimplesDto);
        });
    }

    @Test
    public void testRetirarExitoso() throws CuentaNotFoundException, CuentaSinFondosException {
        MovimientosSimplesDto movimientosSimplesDto = new MovimientosSimplesDto(450d, 1234, "P");
        CuentaDetalladaDto cuentaDetalladaDto = new CuentaDetalladaDto(44882709, "CA", "P");
        Cuenta cuenta = new Cuenta(cuentaDetalladaDto);
        cuenta.setBalance(500d);
        when(cuentaDao.find(1234l)).thenReturn(cuenta);

        movimientosService.retirar(movimientosSimplesDto);

        assertEquals(50d, cuenta.getBalance());
        verify(cuentaDao, times(1)).save(cuenta);
    }

    @Test
    public void testRetirarSaldoInsuficiente() throws CuentaNotFoundException {
        MovimientosSimplesDto movimientosSimplesDto = new MovimientosSimplesDto(300d, 1234, "D");
        CuentaDetalladaDto cuentaDetalladaDto = new CuentaDetalladaDto(44882709, "CA", "D");
        Cuenta cuenta = new Cuenta(cuentaDetalladaDto);
        when(cuentaDao.find(1234l)).thenReturn(cuenta);

        assertThrows(CuentaSinFondosException.class, () -> {
            movimientosService.retirar(movimientosSimplesDto);
        });
    }

    @Test
    public void testTransferirExitoso() throws CuentaNotFoundException, CuentaSinFondosException, DiferenteMonedaException {
        // Dado
        MovimientosDto movimientosDto = new MovimientosDto(4321l, 1234l, 1000.0, "D");
        CuentaDetalladaDto cuentaDetalladaDto1 = new CuentaDetalladaDto(44882709, "CA", "D");
        CuentaDetalladaDto cuentaDetalladaDto2 = new CuentaDetalladaDto(44442312, "CA", "D");

        Cuenta cuentaOrigen = new Cuenta(cuentaDetalladaDto1);
        Cuenta cuentaDestino = new Cuenta(cuentaDetalladaDto2);

        // Configuramos las cuentas con los datos necesarios
        cuentaOrigen.setNumeroCuenta(1234L);
        cuentaOrigen.setBalance(3000.0);

        cuentaDestino.setNumeroCuenta(4321L);
        cuentaDestino.setBalance(2000.0);

        // Configuramos el mock del DAO para devolver las cuentas adecuadas
        when(cuentaDao.find(1234L)).thenReturn(cuentaOrigen);
        when(cuentaDao.find(4321L)).thenReturn(cuentaDestino);

        // Cuando
        movimientosService.transferir(movimientosDto);

        // Entonces
        assertEquals(2000.0, cuentaOrigen.getBalance(), 0.01); // Balance de la cuenta origen después de la transferencia
        assertEquals(3000.0, cuentaDestino.getBalance(), 0.01); // Balance de la cuenta destino después de la transferencia

        // Verificamos que el DAO haya guardado las dos cuentas
        verify(cuentaDao, times(2)).save(any(Cuenta.class));
    }


    @Test
    public void testTransferirMonedaDiferente() throws CuentaNotFoundException, DiferenteMonedaException {
        MovimientosDto movimientosDto = new MovimientosDto(12345, 67890, 1000.0, "P");
        CuentaDetalladaDto cuentaDetalladaDto1 = new CuentaDetalladaDto(44882709, "CA", "D");
        CuentaDetalladaDto cuentaDetalladaDto2 = new CuentaDetalladaDto(31231244, "CA", "P");


        Cuenta cuentaOrigen = new Cuenta(cuentaDetalladaDto1);
        cuentaOrigen.setBalance(3000d);
        Cuenta cuentaDestino = new Cuenta(cuentaDetalladaDto2);
        cuentaDestino.setBalance(3200d);

        when(cuentaDao.find(12345l)).thenReturn(cuentaOrigen);
        when(cuentaDao.find(67890l)).thenReturn(cuentaDestino);

        assertThrows(DiferenteMonedaException.class, () -> {
            movimientosService.transferir(movimientosDto);
        });
    }

    @Test
    public void testTransferirACuentaExternaExitosa() throws CuentaNotFoundException, CuentaSinFondosException, DiferenteMonedaException {
        // Dado
        MovimientosDto movimientosDto = new MovimientosDto(67890l, 12345l, 1000.0, "P");


        CuentaDetalladaDto cuentaDetalladaDto1 = new CuentaDetalladaDto(44882709, "CA", "P");
        CuentaDetalladaDto cuentaDetalladaDto2 = new CuentaDetalladaDto(31231244, "CA", "P");


        Cuenta cuentaOrigen = new Cuenta(cuentaDetalladaDto1);
        cuentaOrigen.setBalance(3000d);
        Cuenta cuentaDestino = new Cuenta(cuentaDetalladaDto2);
        cuentaDestino.setBalance(3200d);

        // Configuramos el mock del DAO para devolver la cuenta origen y destino
        when(cuentaDao.find(12345l)).thenReturn(cuentaOrigen);
        when(cuentaDao.find(67890l)).thenReturn(cuentaDestino);
        // Simulamos que la transferencia externa es exitosa
        when(banelcoService.transferenciaBanelco()).thenReturn(true);

        // Cuando
        movimientosService.transferir(movimientosDto);

        // Entonces
        assertEquals(2000d, cuentaOrigen.getBalance(), 0.01); // Balance de la cuenta origen después de la transferencia
        assertEquals(4200d, cuentaDestino.getBalance(), 0.01); // Balance de la cuenta destino después de la transferencia
        verify(cuentaDao, times(2)).save(any(Cuenta.class)); // Verificamos que el DAO haya guardado ambas cuentas
    }
}

