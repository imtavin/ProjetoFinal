package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.models.Filme;
import br.edu.up.CinemaManager.models.Sessao;
import br.edu.up.CinemaManager.utils.IdUtils;
import br.edu.up.CinemaManager.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoDao {
    private static final Logger logger = LogManager.getLogger(SessaoDao.class);

    private static List<Sessao> sessoes = new ArrayList<>();
    static File arqSessoes = new File("E:\\UP\\5ºSem\\DesenvolvimentoDeSoftware\\CinemaManager\\data\\listaSessoes.txt");

    public static Sessao carregarSessao() {
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

                if (idSessao > IdUtils.getIdSessao()) {
                    IdUtils.setIdSessao(idSessao);
                }

                Filme filme = null;
                if (filme != null) {
                    Sessao sessao = new Sessao(idSessao, filme, horario, tipo3D, tipoDublado, sala, assentos);
                    sessoes.add(sessao);
                } else {
                    Sessao sessao = new Sessao(idSessao, null, horario, tipo3D, tipoDublado, sala, assentos);
                    sessoes.add(sessao);
                }
            }
            br.close();
            return (Sessao) sessoes;
        }catch (IOException e) {
            logger.error("Ocorreu um errou ao carregar as sessões.", e);
        }
        return null;
    }

    public static void salvarSessao(List<Sessao> sessoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arqSessoes))) {
            for (Sessao sessao : sessoes) {
                bw.write(sessao.getIdSessao() + ", " + sessao.getFilme().getTitulo() + ", "
                        + sessao.getHorario() + ", " + sessao.getTipo3D() + ", "
                        + sessao.getTipoDublado() + ", " + sessao.getSala() + ", " + Util.contatenaAssentos(sessao.getAssentosDisponiveis()));
                bw.newLine();
            }
            bw.close();
        }catch (IOException e) {
            logger.error("Ocorreu um errou ao salvar as sessões.", e);
        }
    }
}


