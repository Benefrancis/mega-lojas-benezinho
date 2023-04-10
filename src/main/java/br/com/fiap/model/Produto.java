package br.com.fiap.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO")
    @Column(name = "ID_PRODUTO")
    private long id;


    @Column(name = "NM_PRODUTO")
    private String nome;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "TB_PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "ID_PRODUTO",
                    foreignKey = @ForeignKey(name = "FK_PRODUTO", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIA",
                    foreignKey = @ForeignKey(name = "FK_CATEGORIA", value = ConstraintMode.CONSTRAINT))

    )
    private Set<Categoria> categorias = new LinkedHashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PROD_PEDIDO", value = ConstraintMode.CONSTRAINT))
    private Pedido pedido;

    public Produto addCategoria(Categoria c) {
        this.categorias.add(c);
        c.getProdutos().add(this); //quando encapsular a classe Categoria aqui vai dar erro.
        return this;
    }

    public Produto removerCategoria(Categoria c) {
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
