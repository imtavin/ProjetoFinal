package br.edu.up.CinemaManager.view;

import br.edu.up.CinemaManager.controllers.FilmeController;
import br.edu.up.CinemaManager.models.Filme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class FilmeView {
    private static final Logger logger = LogManager.getLogger(FilmeView.class);
    static Scanner scannerFilme = new Scanner(System.in);
    private static FilmeController filmeController = new FilmeController();

    public static void menuFilme() {
        Integer opcaoFilme = 1;
        while (opcaoFilme != 5) {
            System.out.println("//////////////FILMES//////////////");
            System.out.println("1-Adicionar Filme");
            System.out.println("2-Remover Filme");
            System.out.println("3-Pesquisar Filme");
            System.out.println("4-Listar Filme");
            System.out.println("5-Voltar");
            opcaoFilme = scannerFilme.nextInt();
            switch (opcaoFilme) {
                case 1:
                    adicionarFilme();
                    break;
                case 2:
                    removerFilme();
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

    public static void adicionarFilme() {
        System.out.println("Informe o título do filme:");
        String titulo = scannerFilme.nextLine(); // Consumir quebra de linha pendente
        titulo = scannerFilme.nextLine(); // Ler o título corretamente
        System.out.println("Informe o gênero do filme:");
        String genero = scannerFilme.next();
        System.out.println("Informe o diretor do filme:");
        String diretor = scannerFilme.nextLine(); // Consumir quebra de linha pendente
        diretor = scannerFilme.nextLine(); // Ler o título corretamente
        System.out.println("Informe a idade indicativa do filme:");
        Integer idade = scannerFilme.nextInt();
        Filme filme = new Filme(titulo, genero, diretor, idade);
        filmeController.create(filme);
    }

    public static void removerFilme() {
        System.out.println("Informe o título do filme a ser removido:");
        String tituloRemover = scannerFilme.nextLine();// Consumir quebra de linha pendente
        tituloRemover = scannerFilme.nextLine();
        Filme filmeRemover = buscarFilme(tituloRemover);
    }
}
