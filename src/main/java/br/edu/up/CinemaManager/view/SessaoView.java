package br.edu.up.CinemaManager.view;

import br.edu.up.CinemaManager.controllers.SessaoController;
import br.edu.up.CinemaManager.models.Filme;
import br.edu.up.CinemaManager.controllers.FilmeController;
import br.edu.up.CinemaManager.models.Sessao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class SessaoView {
    private static final Logger logger = LogManager.getLogger(SessaoView.class);
    static Scanner scannerSessao = new Scanner(System.in);
    private static FilmeController filmeController = new FilmeController();
    private static SessaoController sessaoController = new SessaoController();

    public static void menuSessao(){
        /**
         * Método responsável pelas escolhas relacionadas ao uso do Modelo Sessao
         * @param scannerSessao
         * @param opcaoSessao
         */
        Integer opcaoSessao = 1;
        while (opcaoSessao != 5){
            System.out.println("//////////////SESSÕES//////////////");
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
        /**
         * Método responsável por adicionar sessões no banco de dados, usando os filmes já presentes neste.
         * @param scannerSessao
         * @param sessaoController
         * @param input
         * @param listaFilmesOrdenados
         * @param filme
         * @param tipoDublado
         * @param tipo3D
         * @param horario
         * @param sala
         * @param sessao
         */
        List<Filme> listaFilmesOrdenados = filmeController.listarFilmesOrdenadosPorTitulo();
        for (Filme i : listaFilmesOrdenados){
            System.out.println("Titulo: " + i.getTitulo() + "\n    ID: " + i.getId());
        }

        System.out.println("Informe o ID do filme ou o título do filme:");
        String input = scannerSessao.next();
        Filme filme = null;

        try {
            int idFilme = Integer.parseInt(input);
            filme = filmeController.buscarFilmeId(idFilme);
        } catch (NumberFormatException e) {
            filme = filmeController.buscarFilmeTitulo(input);
        }

        if (filme == null) {
            System.out.println("Filme não encontrado.");
        }

        System.out.println("Informe o horário da sessão (formato HH:MM):");
        String horario = scannerSessao.next();

        System.out.println("A sessão é dublada? (true/false):");
        boolean tipoDublado = scannerSessao.nextBoolean();

        System.out.println("A sessão é 3D? (true/false):");
        boolean tipo3D = scannerSessao.nextBoolean();

        System.out.println("Informe o número da sala:");
        int sala = scannerSessao.nextInt();

        Sessao sessao = new Sessao(filme, horario, tipoDublado, tipo3D, sala);
        sessaoController.adicionarSessao(sessao);
        System.out.println("Sessão adicionada com sucesso.");
    }

    public static void removerSessao(){
        /**
         * Método responsável pela remoção de sessões do banco de dados
         * @param scannerSessao
         * @param sessaoRemover
         * @param sessaoController
         * @param IDSessaoRemover
         */
        System.out.println("Informe o ID da sessão a ser removido:");
        int IDSessaoRemover = scannerSessao.nextInt();
        boolean sessaoRemover = sessaoController.deletarSessao(IDSessaoRemover);
        if (!sessaoRemover) {
            System.out.println("Sessão deletada com sucesso.");
        } else {
            System.out.println("Sessão não encontrado.");
        }
    }

    public static void pesquisarSessao(){
        /**
         * Método responsável por pesquisar sessoes no banco de dados
         * @param scannerSessao
         * @param sessaoController
         * @param sessaoPesquisar
         * @param IDSessaoPesquisar
         */
        System.out.println("Pesquisar sessao, informe o ID da sessao:");
        int IDSessaoPesquisar = scannerSessao.nextInt();
        Sessao sessaoPesquisar = sessaoController.buscarSessao(IDSessaoPesquisar);
        System.out.println("Sessão: " + sessaoPesquisar.toString());
    }

    public static void listarSessao(){
        /**
         * Método responsável por listar as sessões existentes no banco de dados.
         * @param sessoes
         * @param sessaoController
         */
        List<Sessao> sessoes = sessaoController.listarSessoesOrdenadasPorHorario();
        for (Sessao sessao : sessoes) {
            System.out.println(sessao);
        }
    }

    public static void listarSessaoPorFilme(String tituloFilme){
        /**
         * Método responsável por listar as sessões no banco de dados, ordenadas por filme.
         * @param sessoes
         * @param sessaoController
         */
        List<Sessao> sessoes = sessaoController.listarSessoesOrdenadasPorFilme(tituloFilme);
        for (Sessao sessao : sessoes) {
            System.out.println("ID: " + sessao.getIdSessao());
            System.out.println("Horário: " + sessao.getHorario());
            System.out.println("Sessao " + (sessao.getTipo3D() ? "3D" : "2D"));
            System.out.println("Sessao " + (sessao.getTipoDublado() ? "Dublada" : "Legendada"));
            System.out.println("Sala: " + sessao.getSala());
            System.out.println("--------------------------------------");
        }
    }

}
