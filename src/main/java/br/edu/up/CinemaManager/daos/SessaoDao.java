package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.controllers.FilmeController;
import br.edu.up.CinemaManager.models.Filme;
import br.edu.up.CinemaManager.models.Sessao;
import br.edu.up.CinemaManager.utils.IdUtils;
import br.edu.up.CinemaManager.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoDao implements GenericDao<Sessao> {
    private static final Logger logger = LogManager.getLogger(SessaoDao.class);
    private static final File arqSessoes = new File("C:\\Users\\autologon.CSED\\Downloads\\ProjetoFinal-develop-Gustavo\\data\\listaSessoes.txt");
    private static FilmeController filmeController = new FilmeController();

    @Override
    public List<Sessao> carregar() {
        List<Sessao> sessoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arqSessoes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                int idSessao = Integer.parseInt(dados[0].trim());
                String tituloFilme = dados[1].trim();
                String horario = dados[2].trim();
                boolean tipo3D = Boolean.parseBoolean(dados[3].trim());
                boolean tipoDublado = Boolean.parseBoolean(dados[4].trim());
                int sala = Integer.parseInt(dados[5].trim());
                String[] assentosStr = dados[6].trim().split("-");
                List<String> assentos = new ArrayList<>();
                for (String assentoStr : assentosStr) {
                    assentos.add(assentoStr.trim());
                }

                Filme filme = filmeController.buscarFilmeTitulo(tituloFilme);
                Sessao sessao = new Sessao(idSessao, filme, horario, tipo3D, tipoDublado, sala, assentos);
                sessoes.add(sessao);

                if (idSessao > IdUtils.getIdSessao()) {
                    IdUtils.setIdSessao(idSessao);
                }
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao carregar as sessões.", e);
        }
        return sessoes;
    }

    @Override
    public void salvar(List<Sessao> sessoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arqSessoes))) {
            for (Sessao sessao : sessoes) {
                bw.write(sessao.getIdSessao() + "," + sessao.getFilme().getTitulo() + "," +
                        sessao.getHorario() + "," + sessao.getTipo3D() + "," + sessao.getTipoDublado() +
                        "," + sessao.getSala() + "," + String.join("-", sessao.getAssentosDisponiveis()));
                bw.newLine();
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao salvar as sessões.", e);
        }
    }
}



