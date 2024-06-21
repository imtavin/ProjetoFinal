package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.ClienteDao;
import br.edu.up.CinemaManager.models.Cliente;
import br.edu.up.CinemaManager.models.Filme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClienteController extends AbstractCRUD<Cliente>{
    private static final Logger logger = LogManager.getLogger(ClienteController.class);
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = ClienteDao.carregarClientes();
    }

    public void adicionarCliente(Cliente cliente) {
        if (buscarCliente(cliente.getCpf()) != null) {
            logger.warn("Cliente com CPF " + cliente.getCpf() + " já está cadastrado.");
        } else {
            clientes.add(cliente);
            ClienteDao.salvarCliente(clientes);
            logger.info("Cliente adicionado com sucesso: " + cliente);
        }
    }

    public void removerCliente(String cpf) {
        Cliente cliente = buscarCliente(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            ClienteDao.salvarCliente(clientes);
            logger.info("Cliente removido com sucesso: " + cliente);
        } else {
            logger.warn("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientesOrdenados = new ArrayList<>(items);
        clientesOrdenados.sort(Comparator.comparing(Cliente::getNome));
        return clientesOrdenados;
    }

    public void atualizarCliente(Cliente clienteAtualizado) {
        Cliente cliente = buscarCliente(clienteAtualizado.getCpf());
        if (cliente != null) {
            cliente.setNome(clienteAtualizado.getNome());
            ClienteDao.salvarCliente(clientes);
            logger.info("Cliente atualizado com sucesso: " + clienteAtualizado);
        } else {
            logger.warn("Cliente com CPF " + clienteAtualizado.getCpf() + " não encontrado.");
        }
    }
}
