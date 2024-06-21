package br.edu.up.CinemaManager.daos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface GenericDao<T> {
    static <T> void salvar(List<T> entities, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (T entity : entities) {
                bw.write(entity.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static <T> List<T> carregar(String filePath, EntityParser<T> parser) {
        List<T> entities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                entities.add(parser.parse(linha));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @FunctionalInterface
    interface EntityParser<T> {
        T parse(String linha);
    }
}
