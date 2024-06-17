package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.FilmeDao;
import br.edu.up.CinemaManager.models.Filme;

import java.util.List;

public class FilmeController extends AbstractCRUD<Filme> {
    private List<Filme> filmes;

    public FilmeController() {
        filmes = FilmeDao.carregarFilmes();
    }

    @Override
    protected int getId(Filme filme) {
        return filme.getId();
    }

    @Override
    public void create(Filme filme) {
        super.create(filme);
        FilmeDao.salvarFilme(filmes);
    }

    @Override
    public boolean update(int id, Filme newFilme) {
        boolean updated = super.update(id, newFilme);
        if (updated) {
            FilmeDao.salvarFilme(filmes);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = super.delete(id);
        if (deleted) {
            FilmeDao.salvarFilme(filmes);
        }
        return deleted;
    }
}
