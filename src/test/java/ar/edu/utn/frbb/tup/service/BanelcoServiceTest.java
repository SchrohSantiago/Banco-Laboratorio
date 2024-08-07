package ar.edu.utn.frbb.tup.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BanelcoServiceTest {

    @Test
    public void testTransferenciaBanelco() {
        BanelcoService banelcoService = new BanelcoService();

        // Realizamos una llamada para verificar que no lanza excepciones
        Boolean resultado = banelcoService.transferenciaBanelco();

        // Verificamos que el resultado es un booleano
        assertTrue(resultado instanceof Boolean, "El resultado debe ser de tipo Boolean.");
    }
}
