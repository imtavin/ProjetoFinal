package br.edu.up.CinemaManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import br.edu.up.CinemaManager.view.*;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        Integer opcao = 1;

        while (opcao != 5){
            System.out.println("///////////////////GERENCIAMENTO DE CINEMA///////////////");
            System.out.println("1-Filmes");
            System.out.println("2-Sessões");
            System.out.println("3-Vendas");
            System.out.println("4-Clientes");
            System.out.println("5-Encerrar Programa");
            System.out.println("//////////////////////////////////////////////////////////");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                //filmes
                    FilmeView.menuFilme();
                  break;
                case 2:
                //Sessões
                    SessaoView.menuSessao();


                  break;
                case 3:
                //Vendas
                    TransacaoView.menuTransacao();
                  break;
                case 4:
                //Clientes
                    ClienteView.menuCliente();
                  break;
                default:
                  break;
            }
        }

    }
}
