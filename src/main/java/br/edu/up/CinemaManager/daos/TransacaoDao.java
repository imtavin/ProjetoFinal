package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.controllers.ClienteController;
import br.edu.up.CinemaManager.controllers.SessaoController;
import br.edu.up.CinemaManager.models.Cliente;
import br.edu.up.CinemaManager.models.Ingresso;
import br.edu.up.CinemaManager.models.Sessao;
import br.edu.up.CinemaManager.models.Transacao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDao implements GenericDao<Transacao> {
    private static final Logger logger = LogManager.getLogger(TransacaoDao.class);
    private static final String filePath = "E:\\UP\\5ºSem\\DesenvolvimentoDeSoftware\\CinemaManager\\data\\listaTransacoes.txt";

    private static SessaoController sessaoController = new SessaoController();
    private static ClienteController clienteController = new ClienteController();

    public static List<Transacao> carregarTransacoes() {
        return GenericDao.carregar(filePath, TransacaoDao::parseTransacao);
    }

    public static void salvarTransacoes(List<Transacao> transacoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Transacao transacao : transacoes) {
                bw.write(transacao.getIdTransacao() + ",");
                for (Ingresso ingresso : transacao.getIngressos()) {
                    if (ingresso.getSessao() == null) {
                        bw.write(ingresso.getAssento() + "," + ingresso.isMeia() + ",null,");
                    } else {
                        bw.write(ingresso.getAssento() + "," + ingresso.isMeia() + "," + ingresso.getSessao().getIdSessao() + ",");
                    }
                }
                bw.write(transacao.getCliente().getCpf() + ",");
                bw.write(transacao.getHorario() + ",");
                bw.write(transacao.getValorTotal() + "\n");
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao salvar as transações.", e);
        }
    }

    private static Transacao parseTransacao(String linha) {
        String[] dados = linha.split(",");
        int index = 0;

        int idTransacao = Integer.parseInt(dados[index++]);
        List<Ingresso> ingressos = new ArrayList<>();

        while (index < dados.length && !dados[index].contains("@")) {
            String assento = dados[index++];
            boolean meia = Boolean.parseBoolean(dados[index++]);
            int idSessao = Integer.parseInt(dados[index++]);

            Sessao sessao = null;
            if (!"null".equals(idSessao)) {
                sessao = sessaoController.buscarSessao(idSessao);
            }

            Ingresso ingresso = new Ingresso(sessao, assento, meia);
            ingressos.add(ingresso);
        }

        String cpfCliente = dados[index++];
        Cliente cliente = clienteController.buscarCliente(cpfCliente);

        String horario = dados[index++];
        double valorTotal = Double.parseDouble(dados[index]);

        return new Transacao(idTransacao, ingressos, cliente, horario, valorTotal);
    }
}
