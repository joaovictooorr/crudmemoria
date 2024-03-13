package br.com.cadastro.domain;

import java.util.Objects;

public class Cliente {

    private String nome;
    private long cpf;
    private String estado;

    public Cliente(String nome, String cpf, String estado){
        this.nome = nome;
        this.cpf = Long.parseLong(cpf.trim());
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf == cliente.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Cliente[ " + "nome= " + nome + " cpf= " + cpf +" ]";
    }
}
