package br.com.cadastro;

import br.com.cadastro.dao.ClienteMapDAO;
import br.com.cadastro.dao.IClienteDAO;
import br.com.cadastro.domain.Cliente;

import javax.swing.*;

public class App {
    private static IClienteDAO clienteDAO;

    public static void main(String[] args){

        clienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastrar, 2 para consultar, 3 parar excluir, 4 para alterar, 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while(!isOpcaoValida(opcao)){
            if("".equals(opcao)){
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,"Opção invalida escolha entre 1 e 5","Opcao invalida",JOptionPane.INFORMATION_MESSAGE);
        }
        while (isOpcaoValida(opcao)){
            if(isOpcaoSair(opcao)){
                sair();
            } else if (isOpcaoCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separado por virgulas","cadastro",JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
                
            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o cpf","buscar por cpf",JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);

            } else if (isAtualizar(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados que para atualizar","Atualizando dados",JOptionPane.INFORMATION_MESSAGE);
                atualizar(dados);

            } else if (isExcluir(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite o cpf para excluir","Exclusão",JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);

            }
            opcao = JOptionPane.showInputDialog(null, "Digite 1 para cadastrar, 2 para consultar, 3 parar excluir, 4 para alterar, 5 para sair", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        }


    }


    private static void cadastrar(String dados) {
        String[] dadosSemVirgulas = dados.split(",");
        if(dadosSemVirgulas.length != 3){
            JOptionPane.showMessageDialog(null, "você precisa preencher todos os campos","campos nullos",JOptionPane.INFORMATION_MESSAGE);
        }
        Cliente cliente = new Cliente(dadosSemVirgulas[0],dadosSemVirgulas[1],dadosSemVirgulas[2]);
        Boolean isCadastrar = clienteDAO.cadastrar(cliente);

        if(isCadastrar){
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso","Cadastro2",JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Cliente já cadastrado","ERRO de Cadastro",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void atualizar(String dados){
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1], dadosSeparados[2]);
        clienteDAO.alterar(cliente);
    }

    private static void consultar(String dados) {
        Cliente cliente = clienteDAO.consultar(Long.parseLong(dados));
        if(cliente != null){
            JOptionPane.showMessageDialog(null, "Cadastro do cliente: " + cliente.toString(),"consulta de cliente",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro não encontrado ","not found",JOptionPane.INFORMATION_MESSAGE);
        }


    }

    public static void excluir(String dados){
        Cliente cliente = clienteDAO.excluir(Long.parseLong(dados));
        if(cliente != null){
            JOptionPane.showMessageDialog(null, "Cadastro excluido: ","DELETE",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro não encontrado ","not found",JOptionPane.INFORMATION_MESSAGE);
        }

    }



    private static boolean isOpcaoSair(String opcao) {
        if("5".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isAtualizar(String opcao) {
        if("4".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isExcluir(String opcao){
        if("3".equals(opcao)){
            return true;
        }

        return false;
    }

    private static boolean isConsultar(String opcao) {
        if("2".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isOpcaoCadastro(String opcao) {
        if("1".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isOpcaoValida(String opcao){
        if("1".equals(opcao) || "2".equals(opcao)
        || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)){
            return true;
        }
        return false;
    }



    private static void sair(){
        JOptionPane.showMessageDialog(null, "EXIT","sair",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }


}
