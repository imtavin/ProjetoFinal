package br.edu.up.CinemaManager.view;

import br.edu.up.CinemaManager.controllers.ClienteController;
import br.edu.up.CinemaManager.controllers.FilmeController;
import br.edu.up.CinemaManager.controllers.SessaoController;
import br.edu.up.CinemaManager.controllers.TransacaoController;
import br.edu.up.CinemaManager.models.*;
import br.edu.up.CinemaManager.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransacaoView {
    private static final Logger logger = LogManager.getLogger(TransacaoView.class);
    static Scanner scannerTransacao = new Scanner(System.in);
    private static ClienteController clienteController = new ClienteController();
    private static FilmeController filmeController = new FilmeController();
    private static SessaoController sessaoController = new SessaoController();
    private static TransacaoController transacaoController = new TransacaoController();
    private static SessaoView sessaoView = new SessaoView();

    public static void menuTransacao(){
        Integer opcaoTransacao = 1;
        while (opcaoTransacao != 4){
            System.out.println("//////////////TRANSAÇÕES//////////////");
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
        System.out.println("Quantos ingressos deseja comprar?");
        int quantidade = scannerTransacao.nextInt();
        scannerTransacao.nextLine(); // Limpar o buffer do scanner

        List<Ingresso> ingressos = new ArrayList<>();

        System.out.println("Informe o seu CPF:");
        String cpf = scannerTransacao.nextLine();
        Cliente cliente = clienteController.buscarCliente(cpf);

        if (cliente == null) System.out.println("Cliente não cadastrado ainda."); ClienteView.adicionarCliente();

        for (int i = 0; i < quantidade; i++) {
            System.out.println("Ingresso " + (i + 1));

            System.out.println("Filmes disponíveis:");
            FilmeView.listarFilmes();
            System.out.println("Informe o título do filme:");

            String tituloFilme = scannerTransacao.nextLine();
            Filme filme = filmeController.buscarFilmeTitulo(tituloFilme);

            System.out.println("Sessões disponíveis para o filme " + tituloFilme + ":");
            sessaoView.listarSessaoPorFilme(tituloFilme);
            System.out.println("Informe o ID da sessão:");
            int idSessao = scannerTransacao.nextInt();
            scannerTransacao.nextLine(); // Limpar o buffer do scanner
            Sessao sessao = sessaoController.buscarSessao(idSessao);
            String assento;

            do{
                System.out.println("Assentos disponiveis:");
                mostrarAssentosDisponiveis(sessao.getAssentosDisponiveis());
                System.out.println("Escolha um assento:");
                assento = scannerTransacao.nextLine();

                if (!(sessao.getAssentosDisponiveis().contains(assento))) System.out.println("Assento errado, informe novamente um assento.");
            }while (!(sessao.getAssentosDisponiveis().contains(assento)));

            System.out.println("O ingresso será meia? (true/false) ");
            Boolean meia = scannerTransacao.nextBoolean();

            if (cliente != null && filme != null && sessao != null) {
                System.out.println("Ingresso vendido com sucesso para " + cliente.getNome());
                ingressos.add(new Ingresso(sessao, assento, meia));
                sessao.ocuparAssento(assento);
            } else {
                System.out.println("Erro ao vender ingresso. Verifique os dados informados.");
            }

            Util.consumirQuebraDeLinha(scannerTransacao);
        }

        String horario = Util.obterHorarioAtual();
        Transacao transacao = new Transacao(ingressos, cliente, horario);

        System.out.println("Valor total em R$ " + transacao.getValorTotal());

        transacaoController.adicionarTransacao(transacao);
    }

    public static void valorArrecadado(){
        System.out.println("O valor arrecado com ingressos é igual a R$" + transacaoController.somarValoresTotaisTransacoes());
    }

    public static void listarTransacoes(){
        List<Transacao> transacoesOrdenadas = transacaoController.listarTransacoesOrdenadasPorData();

        for (Transacao transacao : transacoesOrdenadas) {
            System.out.println("ID da Transação: " + transacao.getIdTransacao());
            try {
                System.out.println("Cliente: " + transacao.getCliente().getNome());
            }
            catch (Exception e) {
                System.out.println("Cliente:" + null);
            }
            System.out.println("Data da Transação: " + transacao.getHorario());
            System.out.println("Valor Total: " + transacao.getValorTotal());
            System.out.println("--------------------------------------");
        }
    }

    public static void mostrarAssentosDisponiveis(List<String> assentosDisponiveis){
        char[] fileiras = {'A', 'B', 'C', 'D', 'E', 'F'};
        int colunas = 11;

        String[][] mapaAssentos = new String[fileiras.length][colunas];

        for (int i = 0; i < fileiras.length; i++) {
            for (int j = 0; j < colunas; j++) {
                mapaAssentos[i][j] = fileiras[i] + Integer.toString(j);
            }
        }
        System.out.println("---------------TELA------------------\n");

        for (int i = 0; i < fileiras.length; i++) {
            for (int j = 0; j < colunas; j++) {
                if (assentosDisponiveis.contains(mapaAssentos[i][j])) {
                    System.out.printf("|%4s", mapaAssentos[i][j]);
                } else {
                    System.out.printf("|%4s", " ");
                }
            }
            System.out.println();
        }
    }
}
