package br.com.cadastro.dao;

import br.com.cadastro.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO{

    private Map<Long,Cliente> mapCliente;

    public ClienteMapDAO(){
        this.mapCliente = new HashMap<>();
    }


    @Override
    public boolean cadastrar(Cliente cliente) {
        if(this.mapCliente.containsKey(cliente.getCpf())){
            return false;
        }
        this.mapCliente.put(cliente.getCpf(),cliente);
        return true;
    }

    @Override
    public Cliente excluir(Long cpf) {
        Cliente clienteCadastrado = this.mapCliente.get(cpf);

        if(clienteCadastrado != null){
            this.mapCliente.remove(clienteCadastrado.getCpf(), clienteCadastrado);
        }

        return clienteCadastrado;
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.mapCliente.get(cliente.getCpf());
        if(clienteCadastrado != null){
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setCpf(cliente.getCpf());
            clienteCadastrado.setEstado(cliente.getEstado());
        }

    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.mapCliente.get(cpf);
    }



    @Override
    public Collection<Cliente> buscarTodos() {
        return this.mapCliente.values();
    }


}
