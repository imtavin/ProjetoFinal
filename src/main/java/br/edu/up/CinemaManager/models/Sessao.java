package br.edu.up.CinemaManager.models;

import java.util.ArrayList;
import java.util.List;

public class Sessao {
    private int idSessao;
    private Filme filme;
    private String horario;
    private boolean tipo3D;
    private boolean tipoDublado;
    private int sala;
    private List<String> assentosDisponiveis;
    private double preco = 30.00;

    public Sessao(Filme filme, String horario, boolean tipoDublado, boolean tipo3D, int sala) {
        this.filme = filme;
        this.horario = horario;
        this.tipoDublado = tipoDublado;
        this.tipo3D = tipo3D;
        //this.idSessao = (LastId.getLastIdSessao()) + 1;
        this.sala = sala;
        this.assentosDisponiveis = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= 10; j++) {
                assentosDisponiveis.add ((Character.toString((char) (i + 65)) + j));
            }
        }

        if(this.getTipo3D()){
            this.preco = preco * 1.5;
        }
    }

    public Sessao(int idSessao, Filme filme, String horario, boolean tipo3D, boolean tipoDublado, int sala, List<String> assentosDisponiveis) {
        this.idSessao = idSessao;
        this.filme = filme;
        this.horario = horario;
        this.tipo3D = tipo3D;
        this.tipoDublado = tipoDublado;
        this.sala = sala;
        this.assentosDisponiveis = assentosDisponiveis;

        if(this.getTipo3D()){
            this.preco = preco * 1.5;
        }
    }

    public int getIdSessao() {
        return idSessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public String getHorario() {
        return horario;
    }

    public boolean getTipo3D() {
        return tipo3D;
    }

    public boolean getTipoDublado() {
        return tipoDublado;
    }

    public int getSala() {return sala;}

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean ocuparAssento(String assento) {
        if (assentosDisponiveis.contains(assento)) {
            assentosDisponiveis.remove(String.valueOf(assento));
            return true;
        } else {
            return false;
        }
    }

    public List<String> getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "idSessao=" + idSessao +
                ", filme=" + filme +
                ", horario='" + horario + '\'' +
                ", tipo3D=" + tipo3D +
                ", tipoDublado=" + tipoDublado +
                ", sala=" + sala +
                ", assentosDisponiveis=" + assentosDisponiveis +
                '}';
    }
}

