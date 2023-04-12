package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO")
    @Column(name = "ID_PRODUTO")
    private long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "NM_PRODUTO")
    private String nome;

    @Getter
    @Setter
    @NonNull
    private double preco;


    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "TB_PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "ID_PRODUTO",
                    foreignKey = @ForeignKey(name = "FK_PRODUTO", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIA",
                    foreignKey = @ForeignKey(name = "FK_CATEGORIA", value = ConstraintMode.CONSTRAINT))

    )
    private Set<Categoria> categorias = new LinkedHashSet<>();


    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PROD_PEDIDO", value = ConstraintMode.CONSTRAINT))
    private Pedido pedido;

    public Produto addCategoria(Categoria c) {
        this.categorias.add(c);
        //  c.getProdutos().add(this); //quando encapsular a classe Categoria aqui vai dar erro.
        return this;
    }

    public Produto removerCategoria(Categoria c) {
        this.categorias.remove(c);
        //  c.getProdutos().remove(this);
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Produto{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", preco=").append(preco);
        sb.append(", categorias=").append(categorias);
      //  sb.append(", pedido=").append(pedido);
        sb.append('}');
        return sb.toString();
    }
}
