package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.FilmeDao;
import br.edu.up.CinemaManager.daos.GenericDao;
import br.edu.up.CinemaManager.exceptions.FilmeNotFoundException;
import br.edu.up.CinemaManager.models.Filme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilmeController extends AbstractCRUD<Filme> {
    private static final Logger logger = LogManager.getLogger(FilmeController.class);
    private GenericDao<Filme> filmeDao;

    public FilmeController() {
        filmeDao = new FilmeDao();
        items = filmeDao.carregar();
    }

    public void adicionarFilme(Filme filme) {
        /**
         *Método para adicionar filmes
         * @return
         * @param filmeDao
         * @param logger
         */
        for (Filme i : items) {
            if (i.getTitulo().equals(filme.getTitulo())) {
                logger.warn("Tentativa de adicionar um filme já existente: " + filme.getTitulo());
                return;
            }
        }
        create(filme);
        filmeDao.salvar(items); // Salva a lista atualizada de filmes
        logger.info("Filme adicionado: " + filme.getTitulo());
    }

    public void deletarFilme(Integer id) {
        /**
         *Método para deletar filmes
         * @param filme
         * @param filmeDao
         * @param logger
         */
        Filme filme = buscarFilmeId(id);
        if (filme != null) {
            delete(filme);
            filmeDao.salvar(items); // Salva a lista atualizada de filmes
            logger.info("Filme removido: " + filme.getTitulo() + ", ID" + filme.getId());
        } else {
            logger.warn("Tentativa de remover um filme não encontrado: " + filme.getTitulo() + ", ID: " + filme.getId());
        }
    }

    public Filme buscarFilmeTitulo(String titulo) {
        /**
         *Método para buscar filmes
         * @param Filme
         * @param logger
         * @exception FilmeNotFound
         */
        try {
            for (Filme i : items) {
                if (i.getTitulo().equals(titulo)) {
                    return i;
                }
            }
        }catch (FilmeNotFoundException e){
            logger.warn("Filme não encontrado: " + e);
        }
        return null;
    }

    public Filme buscarFilmeId(Integer id) {
        /**
         *Método para buscar filmes
         * @return
         * @param items
         * @param logger
         */
        for (Filme i : items) {
            if (i.getId() == id) {
                return i;
            }
        }
        logger.warn("Filme não encontrado: " + id);
        return null;
    }

    public List<Filme> listarFilmesOrdenadosPorTitulo() {
        /**
         *Método para listar filmes em ordem de t
         * @return
         * @param filmesOrdenadas
         * @param logger
         */
        List<Filme> filmesOrdenados = new ArrayList<>(items);
        filmesOrdenados.sort(Comparator.comparing(Filme::getTitulo));
        return filmesOrdenados;
    }
}
