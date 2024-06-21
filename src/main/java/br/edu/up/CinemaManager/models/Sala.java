package br.edu.up.CinemaManager.models;

public class Sala {
    private int id;
    private Integer numeroSala;
    private boolean sala3D;
    private double preco;

    public Sala(double preco, boolean sala3D, Integer numeroSala) {
        this.preco = preco;
        this.sala3D = sala3D;
        this.numeroSala = numeroSala;
    }

    public Sala(int id, Integer numeroSala, boolean sala3D, double preco) {
        this.id = id;
        this.numeroSala = numeroSala;
        this.sala3D = sala3D;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public boolean isSala3D() {
        return sala3D;
    }

    public void setSala3D(boolean sala3D) {
        this.sala3D = sala3D;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
