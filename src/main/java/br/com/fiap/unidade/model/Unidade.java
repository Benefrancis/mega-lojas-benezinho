package br.com.fiap.unidade.model;


import br.com.fiap.funcionario.model.Funcionario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_UNIDADE")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNIDADE")
    @SequenceGenerator(name = "SQ_UNIDADE", sequenceName = "SQ_UNIDADE", initialValue = 1)
    @Column(name = "ID_UNIDADE")
    private Long id;

    @Column(name = "NM_UNIDADE")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_UNIDADE_MATRIZ",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(name = "FK_UNIDADE_MATRIZ", value = ConstraintMode.CONSTRAINT)
    )
    private Unidade matriz;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FUNCIONARIO_CHEFE",
            referencedColumnName = "ID_FUNCIONARIO",
            foreignKey = @ForeignKey(name = "FK_FUNC_CHEFE", value = ConstraintMode.CONSTRAINT)
    )
    private Funcionario chefe;


    public void setChefe(Funcionario f) {
        this.chefe = f;
        if (f != null) {
            f.addUnidade(this);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Unidade{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", matriz=").append(matriz);
        // sb.append(", chefe=").append(chefe);
        sb.append('}');
        return sb.toString();
    }
}
