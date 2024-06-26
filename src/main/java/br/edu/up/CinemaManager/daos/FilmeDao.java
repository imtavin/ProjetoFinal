package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.models.Filme;
import br.edu.up.CinemaManager.utils.IdUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao implements GenericDao<Filme> {
    private static final Logger logger = LogManager.getLogger(FilmeDao.class);
    private static final File arqFilmes = new File("E:\\UP\\5ºSem\\DesenvolvimentoDeSoftware\\CinemaManager\\data\\listaFilmes.txt");

    @Override
    public List<Filme> carregar() {
        List<Filme> filmes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arqFilmes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                int idFilme = Integer.parseInt(dados[0].trim());
                String titulo = dados[1].trim();
                String autor = dados[2].trim();
                String genero = dados[3].trim();
                int idadeIndicativa = Integer.parseInt(dados[4].trim());

                Filme filme = new Filme(idFilme, titulo, autor, genero, idadeIndicativa);
                filmes.add(filme);

                if (idFilme > IdUtils.getIdFilme()) {
                    IdUtils.setIdFilme(idFilme);
                }
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao carregar os filmes.", e);
        }
        return filmes;
    }

    @Override
    public void salvar(List<Filme> filmes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arqFilmes))) {
            for (Filme filme : filmes) {
                bw.write(filme.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao salvar os filmes.", e);
        }
    }
}

