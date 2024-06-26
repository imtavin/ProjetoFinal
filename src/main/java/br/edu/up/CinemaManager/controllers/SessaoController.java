package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.GenericDao;
import br.edu.up.CinemaManager.daos.SessaoDao;
import br.edu.up.CinemaManager.models.Sessao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class SessaoController extends AbstractCRUD<Sessao> {
    private static final Logger logger = LogManager.getLogger(SessaoController.class);
    private GenericDao<Sessao> sessaoDao;

    public SessaoController() {
        sessaoDao = new SessaoDao();
        items = sessaoDao.carregar();
    }

    public void adicionarSessao(Sessao sessao) {
        for (Sessao i : items) {
            if (i.getIdSessao() == sessao.getIdSessao()) {
                logger.warn("Tentativa de adicionar uma sessão já existente: " + sessao.getIdSessao());
                return;
            }
        }
        create(sessao);
        sessaoDao.salvar(items); // Salva a lista atualizada de sessões
        logger.info("Sessão adicionada: " + sessao.getIdSessao());
    }

    public boolean deletarSessao(int idSessao) {
        Sessao sessao = buscarSessao(idSessao);
        if (sessao != null) {
            delete(sessao);
            sessaoDao.salvar(items); // Salva a lista atualizada de sessões
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

    @Override
    public void update(Sessao sessaoAtualizada) {
        Sessao sessao = buscarSessao(sessaoAtualizada.getIdSessao());
        if (sessao != null) {
            sessao.setHorario(sessaoAtualizada.getHorario());
            sessao.setFilme(sessaoAtualizada.getFilme());
            // Atualize outros atributos conforme necessário
            sessaoDao.salvar(items);
            logger.info("Sessão atualizada com sucesso: " + sessaoAtualizada);
        } else {
            logger.warn("Sessão com ID " + sessaoAtualizada.getIdSessao() + " não encontrada.");
        }
    }

    public List<Sessao> listarSessoesOrdenadasPorHorario() {
        List<Sessao> sessoesOrdenadas = new ArrayList<>(items);
        sessoesOrdenadas.sort(Comparator.comparing(Sessao::getHorario));
        logger.info("Sessões listadas em ordem de horário.");
        return sessoesOrdenadas;
    }

    public List<Sessao> listarSessoesOrdenadasPorFilme(String tituloFilme) {
        List<Sessao> sessoesOrdenadas = new ArrayList<>();
        for (Sessao sessao : items) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(tituloFilme)) {
                sessoesOrdenadas.add(sessao);
            }
        }
        return sessoesOrdenadas;
    }
}
