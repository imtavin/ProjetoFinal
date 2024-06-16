package br.edu.up.CinemaManager.models;

import br.edu.up.CinemaManager.utils.IdUtils;

public class Filme {
    private Integer idFilme;
    private String titulo;
    private String autor;
    private String genero;
    private Integer idadeIndicativa;

    public Filme(int idFilme, String titulo, String autor, String genero, Integer idadeIndicativa) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.idadeIndicativa = idadeIndicativa;
    }

    public Filme(String titulo, String autor, String genero, Integer idadeIndicativa) {
        this.idFilme = IdUtils.newIdFilme();
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.idadeIndicativa = idadeIndicativa;
    }

        public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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
}
