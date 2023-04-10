package br.com.fiap.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Produto {


    private long id;

    private String nome;

    private Set<Categoria> categorias = new LinkedHashSet<>();

    private Pedido pedido;

    public Produto addCategoria(Categoria c){
              this.categorias.add(c);
              c.getProdutos().add(this); //quando encapsular a classe Categoria aqui vai dar erro.
              return this;
    }

    public Produto removerCategoria(Categoria c){
        this.categorias.remove(c);
        c.getProdutos().remove(this);
        return this;
    }


    public Produto() {
    }

    public Produto(long id, String nome, Set<Categoria> categorias, Pedido pedido) {
        this.id = id;
        this.nome = nome;
        this.categorias = categorias;
        this.pedido = pedido;
    }

    public long getId() {
        return id;
    }

    public Produto setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }


    public Pedido getPedido() {
        return pedido;
    }

    public Produto setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Produto{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", categorias=").append(categorias);
        sb.append('}');
        return sb.toString();
    }
}
