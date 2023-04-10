package br.com.fiap.model;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class Pedido {

    private long id;

    LocalDateTime data;
    

    private Cliente cliente;

    private Set<Produto> produtos = new LinkedHashSet<>();


    public Pedido() {
    }

    public Pedido(long id, LocalDateTime data, Cliente cliente, Set<Produto> produtos) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Pedido addProduto(Produto p) {
        this.produtos.add(p);
        p.setPedido(this);
        return this;
    }

    public Pedido removerProduto(Produto p) {
        this.produtos.remove(p);
        p.setPedido(null);
        return this;
    }


    public long getId() {
        return id;
    }

    public Pedido setId(long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Pedido setData(LocalDateTime data) {
        this.data = data;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pedido setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pedido{");
        sb.append("id=").append(id);
        sb.append(", data=").append(data);
        //  sb.append(", cliente=").append(cliente);
        sb.append(", produtos=").append(produtos);
        sb.append('}');
        return sb.toString();
    }
}
