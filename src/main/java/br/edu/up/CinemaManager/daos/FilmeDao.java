package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.models.Filme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao {
    private static final Logger logger = LogManager.getLogger(FilmeDao.class);

    private static List<Filme> filmes = new ArrayList<Filme>();
    static File arqFilmes = new File("E:\\UP\\5ºSem\\DesenvolvimentoDeSoftware\\CinemaManager\\data\\listaFilmes.txt");

    public static List<Filme> carregarFilmes() {
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
            }
            br.close();
        }catch(IOException e){
            logger.error("Ocorreu um erro ao carregar os filmes.", e);
        }
        return null;
    }

    public static void SalvarFilme(List<Filme> filmes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arqFilmes))){
            for (Filme filme : filmes) {
                bw.write(filme.toString());
            }
            bw.close();
        }catch (IOException e){
            logger.error("Ocorreu um erro ao salvar os filmes.", e);
        }
    }

}
