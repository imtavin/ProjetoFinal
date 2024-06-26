package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.GenericDao;
import br.edu.up.CinemaManager.daos.SessaoDao;
import br.edu.up.CinemaManager.exceptions.SessaoNotFoundException;
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
      /**
      *Método para adicionar Sessões
      *@return
      *@param logger
      *@param sessao
      *@param sessaoDao
      */
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
      /**
      *Método para deletar Sessões
      *@return
      *@param logger
      *@param sessao
      *@param sessaoDao
      */
        Sessao sessao = buscarSessao(idSessao);
        if (sessao != null) {
            delete(sessao);
            sessaoDao.salvar(items); // Salva a lista atualizada de sessões
            logger.info("Sessão removida: " + idSessao);
            return true;
        } else {
            logger.warn("Tentativa de remover uma sessão não encontrada: " + idSessao);
            throw new SessaoNotFoundException("Sessão não encontrada: " + idSessao);
        }
    }

    public Sessao buscarSessao(int idSessao) {
      /**
      *Método para buscar Sessões
      *@return
      *@param logger
      *@param sessao
      *@exception SessaoNotFound
      */
        for (Sessao i : items) {
            if (i.getIdSessao() == idSessao) {
                return i;
            }
        }
        logger.warn("Sessão não encontrada: " + idSessao);
        throw new SessaoNotFoundException("Sessão não encontrada: " + idSessao);
    }

    public List<Sessao> listarSessoesOrdenadasPorHorario() {
      /**
      *Método para listar Sessões por Horario
      *@return
      *@param logger
      *@param sessao
      *@param sessoesOrdenadas
      */
        List<Sessao> sessoesOrdenadas = new ArrayList<>(items);
        sessoesOrdenadas.sort(Comparator.comparing(Sessao::getHorario));
        logger.info("Sessões listadas em ordem de horário.");
        return sessoesOrdenadas;
    }

    public List<Sessao> listarSessoesOrdenadasPorFilme(String tituloFilme) {
      /**
      *Método para listar Sessões por Filme
      *@return
      *@param logger
      *@param sessao
      *@param sessoesOrdenadas
      */
        List<Sessao> sessoesOrdenadas = new ArrayList<>();
        for (Sessao sessao : items) {
            if (sessao.getFilme().getTitulo().equalsIgnoreCase(tituloFilme)) {
                sessoesOrdenadas.add(sessao);
            }
        }
        return sessoesOrdenadas;
    }

    public void atualizarSessao(Sessao sessaoAtualizada) {
      /**
      *Método para a
      *@return
      *@param logger
      *@param sessao
      *@param sessaoExistente
      *@param sessaoDao
      */
        Sessao sessaoExistente = buscarSessao(sessaoAtualizada.getIdSessao());
        if (sessaoExistente != null) {
            int index = items.indexOf(sessaoExistente);
            if (index != -1) {
                items.set(index, sessaoAtualizada);
                sessaoExistente.setAssentosDisponiveis(sessaoAtualizada.getAssentosDisponiveis());
                sessaoDao.salvar(items);
                logger.info("Sessão atualizada com sucesso: " + sessaoAtualizada);
            } else {
                logger.warn("Erro ao atualizar sessão. Sessão não encontrada na lista.");
            }
        } else {
            logger.warn("Erro ao atualizar sessão. Sessão não encontrada pelo ID: " + sessaoAtualizada.getIdSessao());
        }
    }
}
