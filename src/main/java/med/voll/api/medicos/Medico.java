package med.voll.api.medicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedicos medico) {
        this.nome = medico.nome();
        this.crm =  medico.crm();
        this.email = medico.email();
        this.telefone = medico.telefone();
        this.especialidade = medico.especialidade();
        this.endereco = new Endereco(medico.endereco());
        this.ativo = true;
    }

    public void atualizaInformacao(DadosAtualizaMedicos medico) {
        if(medico.telefone() != null) {
            this.telefone = medico.telefone();
        }
        if(medico.nome() != null) {
            this.nome = medico.nome();
        }
        if (medico.endereco() != null) {
            this.endereco.atualizaInformacao(medico.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

