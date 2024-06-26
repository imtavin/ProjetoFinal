package br.edu.up.CinemaManager.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class TransacaoView {
    private static final Logger logger = LogManager.getLogger(TransacaoView.class);
    static Scanner scannerTransacao = new Scanner(System.in);

    public static void menuTransacao(){
        Integer opcaoTransacao = 1;
        while (opcaoTransacao != 5){
            System.out.println("//////////////TRANSAÇÕES//////////////");
            Integer opTransacao;
            System.out.println("1-Vender Ingresso");
            System.out.println("2-Valor Arrecadado");
            System.out.println("3-Listar Transações");
            System.out.println("4-Voltar");
            opcaoTransacao = scannerTransacao.nextInt();
            switch(opcaoTransacao){
                case 1:
                    venderIngresso();
                    break;
                case 2:
                    valorArrecadado();
                    break;
                case 3:
                    listarTransacoes();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção Inválida! Tente Novamente. ");
            }
        }

    }
    public static void venderIngresso(){

    }
    public static void valorArrecadado(){

    }
    public static void listarTransacoes(){

    }

}

