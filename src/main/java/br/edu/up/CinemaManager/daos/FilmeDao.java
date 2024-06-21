package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.models.Filme;
import br.edu.up.CinemaManager.utils.IdUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao implements GenericDao<Filme>{
    private static final Logger logger = LogManager.getLogger(FilmeDao.class);

    private static List<Filme> filmes = new ArrayList<Filme>();
    private static final File arqFilmes = new File("E:\\UP\\5ºSem\\DesenvolvimentoDeSoftware\\CinemaManager\\data\\listaFilmes.txt");

    public static List<Filme> carregar() {
        try (BufferedReader br = new BufferedReader(new FileReader(arqFilmes))){
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                int idFilme = Integer.parseInt(dados[0].trim());
                String titulo = dados[1].trim();
                String autor = dados[2].trim();
                String genero = dados[3].trim();
                Integer idadeIdicativa = Integer.valueOf(dados[4].trim());
                Filme filme = new Filme(idFilme, titulo, autor, genero, idadeIdicativa);
                filmes.add(filme);

                if (idFilme > IdUtils.getIdFilme()) {
                    IdUtils.setIdFilme(idFilme);
                }
            }
            br.close();
        }catch(IOException e){
            logger.error("Ocorreu um erro ao carregar os filmes.", e);
        }
        return filmes;
    }

    public static void salvar(List<Filme> filmes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arqFilmes))){
            for (Filme filme : filmes) {
                bw.write(filme.toString());
                bw.newLine();
            }
            bw.close();
        }catch (IOException e){
            logger.error("Ocorreu um erro ao salvar os filmes.", e);
        }
    }

}
