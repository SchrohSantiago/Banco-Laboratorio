package ar.edu.utn.frbb.tup.process;

import java.util.Scanner;

public class BaseInputProcessor {

    protected Scanner scanner = new Scanner(System.in);


    protected static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
