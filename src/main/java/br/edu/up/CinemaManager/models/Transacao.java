package br.edu.up.CinemaManager.models;

import br.edu.up.CinemaManager.utils.IdUtils;

import java.time.LocalDateTime;
import java.util.List;

public class Transacao {
    private int idTransacao;
    private List<Ingresso> ingressos;
    private Cliente cliente;
    private String horario;
    private double valorTotal = 0.00;

    public Transacao(List<Ingresso> ingressos, Cliente cliente, String horario) {
        this.ingressos = ingressos;
        this.cliente = cliente;
        this.horario = horario;
        this.valorTotal = valorTotal;
        this.idTransacao = IdUtils.newIdTransacao();
    }

    public Transacao(int idTransacao, List<Ingresso> ingressos, Cliente cliente, String horario, double valorTotal) {
        this.idTransacao = idTransacao;
        this.ingressos = ingressos;
        this.cliente = cliente;
        this.horario = horario;
        this.valorTotal = valorTotal;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getHorario() {
        return horario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "idTransacao=" + idTransacao +
                ", ingressos=" + ingressos +
                ", cliente=" + cliente +
                ", horario=" + horario +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
