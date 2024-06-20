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
                    adicionarCliente();
                    break;
                case 2:
                    removerCliente();
                    break;
                case 3:
                    pesquisarCliente();
                    break;
                case 4:
                    listarCliente();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção Inválida! Tente novamente. ");
                    break;
            }
        }
    }
    public static void adicionarCliente(){
        //teste
    }
    public static void removerCliente(){
        //teste
    }
    public static void pesquisarCliente(){

    }
    public static void listarCliente(){

    }
}

