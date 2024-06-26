package br.edu.up.CinemaManager.daos;

import br.edu.up.CinemaManager.models.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDao implements GenericDao<Cliente> {
    private static final Logger logger = LogManager.getLogger(ClienteDao.class);
    private static final File arqClientes = new File("E:\\UP\\5ºSem\\DesenvolvimentoDeSoftware\\CinemaManager\\data\\listaClientes.txt");

    @Override
    public List<Cliente> carregar() {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arqClientes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0].trim();
                String cpf = dados[1].trim();
                int idade = Integer.parseInt(dados[2].trim());

                Cliente cliente = new Cliente(nome, cpf, idade);
                clientes.add(cliente);
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao carregar os clientes.", e);
        }
        return clientes;
    }

    @Override
    public void salvar(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arqClientes))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.getNome() + "," + cliente.getCpf() + "," + cliente.getIdade());
                bw.newLine();
            }
        } catch (IOException e) {
            logger.error("Ocorreu um erro ao salvar os clientes.", e);
        }
    }
}

