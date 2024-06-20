package br.edu.up.CinemaManager.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ClienteView {
    private static final Logger logger = LogManager.getLogger(ClienteView.class);
    static Scanner scannerCliente = new Scanner(System.in);

    public static void menuCliente() {
        Integer opcaoCliente = 1;
        while (opcaoCliente != 5) {
            System.out.println("//////////////CLIENTES//////////////");
            System.out.println("1-Adicionar Cliente");
            System.out.println("2-Remover Cliente");
            System.out.println("3-Pesquisar Cliente");
            System.out.println("4-Listar Cliente");
            System.out.println("5-Voltar");
            opcaoCliente = scannerCliente.nextInt();
            switch (opcaoCliente) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        }
    }
}

