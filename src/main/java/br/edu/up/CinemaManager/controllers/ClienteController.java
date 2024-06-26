package br.edu.up.CinemaManager.controllers;

import br.edu.up.CinemaManager.daos.ClienteDao;
import br.edu.up.CinemaManager.daos.GenericDao;
import br.edu.up.CinemaManager.models.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClienteController extends AbstractCRUD<Cliente>{
    private static final Logger logger = LogManager.getLogger(ClienteController.class);
    private GenericDao<Cliente> clienteDao;

    public ClienteController() {
        clienteDao = new ClienteDao();
        items = clienteDao.carregar();
    }

    public void adicionarCliente(Cliente cliente) {
        /**
         * Método responsável por adicionar os Clientes ao banco de dados
         * @param logger
         * @param clienteDao
         * @param items
         */
        if (buscarCliente(cliente.getCpf()) != null) {
            logger.warn("Cliente com CPF " + cliente.getCpf() + " já está cadastrado.");
        } else {
            create(cliente);
            items.add(cliente);
            clienteDao.salvar(items);
            logger.info("Cliente adicionado com sucesso: " + cliente);
        }
    }

    public void removerCliente(String cpf) {
        /**
         * Método responsável por remover um Cliente se este existir no banco de dados
         * @param cliente
         * @param clienteDao
         * @param logger
         * @param items
         */
        Cliente cliente = buscarCliente(cpf);
        if (cliente != null) {
            delete(cliente);
            items.remove(cliente);
            clienteDao.salvar(items);
            logger.info("Cliente removido com sucesso: " + cliente);
        } else {
            logger.warn("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public Cliente buscarCliente(String cpf) {
        /**
         * Método responsável por buscar Clientes
         * @return
         * @param clientes
         */
        for (Cliente cliente : items) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        /**
         * Método responsável por listar os Clientes
         * @return
         * @param clientesOrdenados
         */
        List<Cliente> clientesOrdenados = new ArrayList<>(items);
        clientesOrdenados.sort(Comparator.comparing(Cliente::getNome));
        return clientesOrdenados;
    }

    public void atualizarCliente(Cliente clienteAtualizado) {
        /**
         * Método responsável por atualizar informações de um cliente especificado no banco de dados, se o mesmo existir.
         * @param cliente
         * @param clienteDao
         * @param logger
         */
        Cliente cliente = buscarCliente(clienteAtualizado.getCpf());
        if (cliente != null) {
            cliente.setNome(clienteAtualizado.getNome());
            clienteDao.salvar(items);
            logger.info("Cliente atualizado com sucesso: " + clienteAtualizado);
        } else {
            logger.warn("Cliente com CPF " + clienteAtualizado.getCpf() + " não encontrado.");
        }
    }
}
