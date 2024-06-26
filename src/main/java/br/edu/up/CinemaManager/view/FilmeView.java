package br.edu.up.CinemaManager.view;

import br.edu.up.CinemaManager.controllers.FilmeController;
import br.edu.up.CinemaManager.models.Filme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class FilmeView {
    private static final Logger logger = LogManager.getLogger(FilmeView.class);
    static Scanner scannerFilme = new Scanner(System.in);
    private static FilmeController filmeController = new FilmeController();

    public static void menuFilme() {
        /**
        * Método responsável por lidar com a escolha do usuário em relação ao modelo Filmes
        * @param scannerFilme
        * @param opcaoFilme
        */
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
                    pesquisarFilme();
                    break;
                case 4:
                    listarFilmes();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }

    public static void adicionarFilme() {
        /**
        * Método responsável por adicionar filmes ao banco de dados
        * @param scannerFilme
        * @param titulo
        * @param genero
        * @param diretor
        * @param idade
        * @param filme
        * @param filmeController
        */
        System.out.println("Informe o título do filme:");
        String titulo = scannerFilme.nextLine(); // Consumir quebra de linha pendente
        titulo = scannerFilme.nextLine();
        System.out.println("Informe o gênero do filme:");
        String genero = scannerFilme.nextLine();
        System.out.println("Informe o diretor do filme:");
        String diretor = scannerFilme.nextLine();
        System.out.println("Informe a idade indicativa do filme:");
        int idade = scannerFilme.nextInt();
        scannerFilme.nextLine(); // Consumir a quebra de linha

        Filme filme = new Filme(titulo, diretor, genero, idade);
        filmeController.adicionarFilme(filme);
    }

    public static void removerFilme() {
        /**
        * Método responsável por remover filmes do banco de dados
        * @param scannerFilme
        * @param IdRemover
        * @param filmeController
        */
        listarFilmes();
        System.out.println("Informe o ID do filme a ser removido:");
        Integer IdRemover = scannerFilme.nextInt(); // Consumir quebra de linha pendente
        filmeController.deletarFilme(IdRemover);
    }

    public static void pesquisarFilme() {
        /**
        * Método responsável por pesquisar filmes no banco de dados
        * @param scannerFilme
        * @param tituloPesquisar
        * @param filme
        * @param filmeController
        */
        System.out.println("Informe o título do filme a ser pesquisado:");
        String tituloPesquisar = scannerFilme.nextLine(); // Consumir quebra de linha pendente
        tituloPesquisar = scannerFilme.nextLine();
        Filme filme = filmeController.buscarFilmeTitulo(tituloPesquisar);
        if (filme != null) {
            System.out.println("Filme encontrado:");
            System.out.println("ID: " + filme.getId());
            System.out.println("Título: " + filme.getTitulo());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("Diretor: " + filme.getDiretor());
            System.out.println("Idade Indicativa: " + filme.getIdadeIndicativa());
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    public static void listarFilmes() {
        /**
        * Método responsável por listar os filmes no Banco de Dados
        * @param listaFilmesOrdenados
        * @param filmeController
        */
        List<Filme> listaFilmesOrdenados = filmeController.listarFilmesOrdenadosPorTitulo();
        for (Filme i : listaFilmesOrdenados){
            System.out.println("Titulo: " + i.getTitulo() + "\n    ID: " + i.getId());
        }
    }
}
