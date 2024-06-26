package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.controllers.ClienteController;
import br.edu.up.CinemaManager.controllers.SessaoController;
import br.edu.up.CinemaManager.models.Cliente;
import br.edu.up.CinemaManager.models.Ingresso;
import br.edu.up.CinemaManager.models.Sessao;
import br.edu.up.CinemaManager.models.Transacao;
import br.edu.up.CinemaManager.utils.IdUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDao implements GenericDao<Transacao> {
    private static final Logger logger = LogManager.getLogger(TransacaoDao.class);
    private static final String filePath = "C:\\Users\\autologon.CSED\\Downloads\\ProjetoFinal-develop-Gustavo\\data\\listaTransacoes.txt";
    private static final SessaoController sessaoController = new SessaoController();
    private static final ClienteController clienteController = new ClienteController();

    @Override
    public List<Transacao> carregar() {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Transacao transacao = parseTransacao(linha);
                transacoes.add(transacao);
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao carregar as transações.", e);
        }
        return transacoes;
    }

    @Override
    public void salvar(List<Transacao> transacoes) {
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
                bw.write(String.valueOf(transacao.getValorTotal()) + "\n");
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao salvar as transações.", e);
        }
    }

    private Transacao parseTransacao(String linha) {
        String[] dados = linha.split(",");
        int idTransacao = Integer.parseInt(dados[0].trim());
        List<Ingresso> ingressos = new ArrayList<>();
        int i = 1;
        while (i < dados.length - 3) {
            String assento = dados[i].trim();
            boolean meia = Boolean.parseBoolean(dados[i + 1].trim());
            int idSessao = Integer.parseInt(dados[i + 2].trim());
            Sessao sessao = sessaoController.buscarSessao(idSessao);
            ingressos.add(new Ingresso(sessao, assento, meia));
            i += 3;
        }

        String cpfCliente = dados[dados.length - 3].trim();
        Cliente cliente = clienteController.buscarCliente(cpfCliente);

        String horario = dados[dados.length - 2].trim();
        double valorTotal = Double.parseDouble(dados[dados.length - 1].trim());

        if (idTransacao > IdUtils.getIdTransacao()) {
            IdUtils.setIdTransacao(idTransacao);
        }

        return new Transacao(idTransacao, ingressos, cliente, horario, valorTotal);
    }
}
