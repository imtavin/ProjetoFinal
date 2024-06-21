package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.SessaoDao;
import br.edu.up.CinemaManager.models.Sessao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class SessaoController extends AbstractCRUD<Sessao> {
    private static final Logger logger = LogManager.getLogger(SessaoController.class);

    public SessaoController() {
        items = SessaoDao.carregarSessoes(); // Carrega sessões do arquivo na inicialização
        logger.info("Sessões carregadas do arquivo.");
    }

    public void adicionarSessao(Sessao sessao) {
        for (Sessao i : items) {
            if (i.getIdSessao() == sessao.getIdSessao()) {
                logger.warn("Tentativa de adicionar uma sessão já existente: " + sessao.getIdSessao());
                return;
            }
        }
        create(sessao);
        SessaoDao.salvarSessoes(items); // Salva a lista atualizada de sessões
        logger.info("Sessão adicionada: " + sessao.getIdSessao());
    }

    public boolean deletarSessao(int idSessao) {
        Sessao sessao = buscarSessao(idSessao);
        if (sessao != null) {
            delete(sessao);
            SessaoDao.salvarSessoes(items); // Salva a lista atualizada de sessões
            logger.info("Sessão removida: " + idSessao);
            return true;
        } else {
            logger.warn("Tentativa de remover uma sessão não encontrada: " + idSessao);
            return false;
        }
    }

    public Sessao buscarSessao(int idSessao) {
        for (Sessao i : items) {
            if (i.getIdSessao() == idSessao) {
                return i;
            }
        }
        logger.warn("Sessão não encontrada: " + idSessao);
        return null;
    }

    public List<Sessao> listarSessoesOrdenadasPorHorario() {
        List<Sessao> sessoesOrdenadas = new ArrayList<>(items);
        sessoesOrdenadas.sort(Comparator.comparing(Sessao::getHorario));
        logger.info("Sessões listadas em ordem de horário.");
        return sessoesOrdenadas;
    }
}
