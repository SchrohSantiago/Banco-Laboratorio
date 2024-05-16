package ar.edu.utn.frbb.tup;

import ar.edu.utn.frbb.tup.process.MenuInputProcessor;
import ar.edu.utn.frbb.tup.utils.Banco;


class Aplicacion {

    public static void main(String args[]) {
        Banco banco = new Banco();

        MenuInputProcessor menuInputProcessor = new MenuInputProcessor();
        menuInputProcessor.renderMenu(banco);
        System.out.println(banco.getClientes());

    }


}
