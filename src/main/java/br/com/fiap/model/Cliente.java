package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "TB_CLIENTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_CLIENTE", columnNames = "EMAIL_CLIENTE")
})
public class Cliente {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE")
    @Column(name = "ID_CLIENTE")
    private long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "NM_CLIENTE")
    private String nome;

    @Getter
    @Setter
    @NonNull
    @Column(name = "EMAIL_CLIENTE")
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("data DESC")
    private Set<Pedido> pedidos = new LinkedHashSet<>();


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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cliente{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
     //   sb.append(", pedidos=").append(pedidos);
        sb.append('}');
        return sb.toString();
    }
}
