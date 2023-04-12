package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
    @SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "SQ_PEDIDO")
    @Column(name = "ID_PEDIDO")
    private long id;

    @Getter
    @Setter
    @Column(name = "DT_PEDIDO")
    private LocalDateTime data;


    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(name = "FK_PEDIDO_CLIENTE", value = ConstraintMode.NO_CONSTRAINT)
    )
    private Cliente cliente;

    @Getter
    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("nome ASC")
    private Set<Produto> produtos = new LinkedHashSet<>();


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


}
