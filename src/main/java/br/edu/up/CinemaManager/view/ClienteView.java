package br.edu.up.CinemaManager.view;

import br.edu.up.CinemaManager.controllers.ClienteController;
import br.edu.up.CinemaManager.models.Cliente;
import br.edu.up.CinemaManager.models.Filme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private static final Logger logger = LogManager.getLogger(ClienteView.class);
    static Scanner scannerCliente = new Scanner(System.in);
    private static ClienteController clienteController = new ClienteController();

    /**
    * Método responsável pelas escolhas relacionadas ao uso do Modelo Cliente
    * @param scannerCliente
    * @param opcaoCliente
    */

    
    public static void menuCliente() {
        Integer opcaoCliente = 1;
        while (opcaoCliente != 5) {
            System.out.println("//////////////CLIENTES//////////////");
            System.out.println("1-Adicionar Cliente");
            System.out.println("2-Remover Cliente");
            System.out.println("3-Pesquisar Cliente");
            System.out.println("4-Listar Cliente");
            System.out.println("5-Voltar");
            opcaoCliente = scannerCliente.nextInt();
            switch (opcaoCliente) {
                case 1:
                    adicionarCliente();
                    break;
                case 2:
                    removerCliente();
                    break;
                case 3:
                    pesquisarCliente();
                    break;
                case 4:
                    listarCliente();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção Inválida! Tente novamente. ");
                    break;
            }
        }
    }
    public static void adicionarCliente(){
            /**
            * Método responsável por adicionar um novo cliente ao banco de dados
            * @param nomeCliente
            * @param cpfCliente
            * @param idadeCliente
            * @param scannerCliente
            */
        System.out.println("Informe o nome do cliente:");
        String nomeCliente = scannerCliente.nextLine();
        nomeCliente = scannerCliente.nextLine();
        System.out.println("Informe o CPF do cliente:");
        String cpfCliente = scannerCliente.next();
        System.out.println("Informe a idade do cliente:");
        int idadeCliente = scannerCliente.nextInt();
        clienteController.adicionarCliente(new Cliente(nomeCliente, cpfCliente, idadeCliente));
    }

    public static void removerCliente(){
            /**
            * Método responsável por remover um Cliente do banco de dados.
            * @param scannerCliente
            * @param cpfClienteRemover
            */
        System.out.println("Informe o CPF do cliente a ser removido:");
        String cpfClienteRemover = scannerCliente.next();
        clienteController.removerCliente(cpfClienteRemover);
    }

    public static void pesquisarCliente(){
            /**
            * Método responsável por buscar um Cliente específico no banco de dados.
            * @param scannerCliente
            * @param cpfClientePesquisar
            * @param cliente
            */
        System.out.println("Informe o CPF do cliente a ser pesquisado:");
        String cpfClientePesquisar = scannerCliente.next();
        Cliente cliente = clienteController.buscarCliente(cpfClientePesquisar);
        if(cliente == null){
            System.out.println("Cliente não encntrado");
        }
        else{
            System.out.println("Cliente: " + cliente.toString());
        }
    }

    public static void listarCliente(){
            /**
           * Método responsável por listar os Clientes no banco de dados
           * @param listaClienteOrdenados
           */
        List<Cliente> listaClienteOrdenados = clienteController.listarClientes();
        for (Cliente i : listaClienteOrdenados){
            System.out.println("Nome: " + i.getNome() + "\nCPF: " + i.getCpf()  + "\nIdade: " + i.getIdade());
        }
    }
}

