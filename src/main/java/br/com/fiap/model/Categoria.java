package br.com.fiap.model;

import jakarta.persistence.Entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class Categoria {

    private long id;

    private String nome;

    private Set<Produto> produtos = new LinkedHashSet<>();


    public Categoria() {
    }


    public Categoria(long id, String nome, Set<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
    }

    public long getId() {
        return id;
    }

    public Categoria setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Categoria setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categoria{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        //   sb.append(", produtos=").append(produtos);
        sb.append('}');
        return sb.toString();
    }
}
