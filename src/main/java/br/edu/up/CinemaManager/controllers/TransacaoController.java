package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.TransacaoDao;
import br.edu.up.CinemaManager.models.Transacao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TransacaoController extends AbstractCRUD<Transacao> {
    private static final Logger logger = LogManager.getLogger(TransacaoController.class);

    public TransacaoController() {
        items = new ArrayList<>();
        carregarTransacoes();
    }

    public void carregarTransacoes(){
        items.clear();
        items.addAll(TransacaoDao.carregar());
    }

    public void adicionarTransacao(Transacao transacao) {
        for (Transacao i : items) {
            if (i.getIdTransacao() == transacao.getIdTransacao()) {
                logger.warn("Tentativa de adicionar uma transação já existente: " + transacao.getIdTransacao());
                return;
            }
        }
        create(transacao);
        TransacaoDao.salvar(items);
        logger.info("Transação adicionada: " + transacao.getIdTransacao());
    }

    public void deletarTransacao(int id) {
        Transacao transacao = buscarTransacaoId(id);
        if (transacao != null) {
            delete(transacao);
            TransacaoDao.salvar(items);
            logger.info("Transação removida: " + transacao.getIdTransacao());
        } else {
            logger.warn("Tentativa de remover uma transação não encontrada: " + id);
        }
    }

    public Transacao buscarTransacaoId(int id) {
        for (Transacao i : items) {
            if (i.getIdTransacao()==id) {
                return i;
            }
        }
        logger.warn("Transação não encontrada: " + id);
        return null;
    }

    public List<Transacao> listarTransacoesOrdenadasPorData() {
        List<Transacao> transacoesOrdenadas = new ArrayList<>(items);
        transacoesOrdenadas.sort(Comparator.comparing(Transacao::getHorario));
        return transacoesOrdenadas;
    }

    public double somarValoresTotaisTransacoes() {
        double soma = 0.0;
        for (Transacao transacao : items) {
            soma += transacao.getValorTotal();
        }
        return soma;
    }
}
