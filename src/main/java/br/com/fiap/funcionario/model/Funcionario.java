package br.com.fiap.funcionario.model;

import br.com.fiap.unidade.model.Unidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

@Entity
@Table(name = "TB_FUNCIONARIO")
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUNCIONARIO")
    @SequenceGenerator(name = "SQ_FUNCIONARIO", sequenceName = "SQ_FUNCIONARIO", initialValue = 1)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Getter
    @Setter
    @Column(name = "NR_MATRICULA")
    private String matricula;

    @Getter
    @Setter
    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "TB_FUNCIONARIO_UNIDADE",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_FUNCIONARIO",
                            referencedColumnName = "ID_FUNCIONARIO",
                            foreignKey = @ForeignKey(name = "FK_FUNCIONARIO", value = ConstraintMode.CONSTRAINT)
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_UNIDADE",
                            referencedColumnName = "ID_UNIDADE",
                            foreignKey = @ForeignKey(name = "FK_UNIDADE", value = ConstraintMode.CONSTRAINT)
                    )
            }
    )
    private Collection<Unidade> unidades = new LinkedHashSet<>();

    public Funcionario addUnidade(Unidade u) {
        this.unidades.add(u);
        return this;
    }

    public Funcionario removerUnidade(Unidade u) {
        this.unidades.remove(u);
        return this;
    }

    public Collection<Unidade> getUnidades() {
        return Collections.unmodifiableList(unidades.stream().toList());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Funcionario{");
        sb.append("id=").append(id);
        sb.append(", matricula='").append(matricula).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", unidades=").append(unidades);
        sb.append('}');
        return sb.toString();
    }
}
