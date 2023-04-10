package br.com.fiap.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_CLIENTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_CLIENTE", columnNames = "EMAIL_CLIENTE")
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE")
    @Column(name = "ID_CLIENTE")
    private long id;

    @Column(name = "NM_CLIENTE")
    private String nome;

    @Column(name = "EMAIL_CLIENTE")
    private String email;


    @OneToMany(mappedBy = "cliente")
    @OrderBy("data DESC")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

    public Cliente() {
    }

    public Cliente(long id, String nome, String email, Set<Pedido> pedidos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.pedidos = pedidos;
    }

    public Cliente addPedido(Pedido p) {
        this.pedidos.add(p);
        p.setCliente(this);
        return this;
    }

    public Cliente removePedido(Pedido p) {
        this.pedidos.remove(p);
        p.setCliente(null);
        return this;
    }

    public long getId() {
        return id;
    }

    public Cliente setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Cliente setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cliente{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", pedidos=").append(pedidos);
        sb.append('}');
        return sb.toString();
    }
}
