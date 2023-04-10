package br.com.fiap.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
    @SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "SQ_PEDIDO")
    @Column(name = "ID_PEDIDO")
    private long id;

    @Column(name = "DT_PEDIDO")
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_CLIENTE", value = ConstraintMode.NO_CONSTRAINT)
    )
    private Cliente cliente;


    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("nome ASC")
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
