package ar.edu.utn.frbb.tup.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BanelcoService {
    private static final Random random = new Random();
    // Simulamos de manera random si la cuenta existe o no

    public Boolean transferenciaBanelco(/*long cuentaOrigen, long cuentaDestino, double monto*/){
        // Hacemos que alla un 70% de probablidad de que la cuenta simulada exista

        int probabilidad = random.nextInt(100);
        System.out.println(probabilidad);
        return probabilidad < 70;  // Retorna el numero random si es menor a 70
    }
}
