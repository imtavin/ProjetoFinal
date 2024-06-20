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
                    adicionarSessao();
                    break;
                case 2:
                    removerSessao();
                    break;
                case 3:
                    pesquisarSessao();
                    break;
                case 4:
                    listarSessao();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção Inválida! Tente Novamente. ");
            }
        }

    }
    public static void adicionarSessao(){
        
    }
    public static void removerSessao(){

    }
    public static void pesquisarSessao(){

    }
    public static void listarSessao(){

    }

}
