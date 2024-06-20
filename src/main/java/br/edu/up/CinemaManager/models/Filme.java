package br.edu.up.CinemaManager.models;

import br.edu.up.CinemaManager.utils.IdUtils;

public class Filme {
    private Integer idFilme;
    private String titulo;
    private String diretor;
    private String genero;
    private Integer idadeIndicativa;

    public Filme(int idFilme, String titulo, String diretor, String genero, Integer idadeIndicativa) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.diretor = diretor;
        this.genero = genero;
        this.idadeIndicativa = idadeIndicativa;
    }

    public Filme(String titulo, String diretor, String genero, Integer idadeIndicativa) {
        this.idFilme = IdUtils.newIdFilme();
        this.titulo = titulo;
        this.diretor = diretor;
        this.genero = genero;
        this.idadeIndicativa = idadeIndicativa;
    }

    public int getId() {
        return idFilme;
    }

        public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIdadeIndicativa() {
        return idadeIndicativa;
    }

    public void setIdadeIndicativa(Integer idadeIndicativa) {
        this.idadeIndicativa = idadeIndicativa;
    }

    @Override
    public String toString() {
        return idFilme + " , " + titulo + " , "
                + diretor + " , " + genero + " , " +
                idadeIndicativa;
    }
}
