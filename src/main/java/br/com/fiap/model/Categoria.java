package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TB_CATEGORIA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_CATEGORIA", columnNames = "NM_CATEGORIA")
})
public class Categoria {


    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CATEGORIA")
    @SequenceGenerator(name = "SQ_CATEGORIA", sequenceName = "SQ_CATEGORIA")
    @Column(name = "ID_CATEGORIA")
    private long id;

    @NonNull @Getter @Setter
    @Column(name = "NM_CATEGORIA")
    private String nome;

//    @ManyToMany(mappedBy = "categorias", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @OrderBy("nome ASC")
//    private Set<Produto> produtos = new LinkedHashSet<>();


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Categoria{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
