package br.edu.up.CinemaManager.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class SessaoView {
    private static final Logger logger = LogManager.getLogger(SessaoView.class);
    static Scanner scannerSessao = new Scanner(System.in);

    public static void menuSessao(){
        Integer opcaoSessao = 1;
        while (opcaoSessao != 5){
            System.out.println("//////////////SESSÕES//////////////");
            Integer opSessao;
            System.out.println("1-Adicionar Sessão");
            System.out.println("2-Remover Sessão");
            System.out.println("3-Pesquisar Sessão");
            System.out.println("4-Listar Sessões");
            System.out.println("5-Voltar");
            opcaoSessao = scannerSessao.nextInt();
            switch(opcaoSessao){
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
