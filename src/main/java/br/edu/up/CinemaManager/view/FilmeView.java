package br.edu.up.CinemaManager.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class FilmeView {
    private static final Logger logger = LogManager.getLogger(FilmeView.class);
    static Scanner scannerFilme = new Scanner(System.in);

    public static void menuFilme() {
        Integer opcaoFilme = 1;
        while (opcaoFilme != 5) {
            System.out.println("//////////////SESSÕES//////////////");
            System.out.println("1-Adicionar Sessão");
            System.out.println("2-Remover Sessão");
            System.out.println("3-Pesquisar Sessão");
            System.out.println("4-Listar Sessões");
            System.out.println("5-Voltar");
            opcaoFilme = scannerFilme.nextInt();
            switch (opcaoFilme) {
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
