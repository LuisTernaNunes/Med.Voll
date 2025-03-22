package med.voll.api.clientes;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    @Embedded
    private Endereco endereco;
    private String cpf;
    private boolean ativo;

    public Cliente(@Valid DadosCadCliente cliente) {
        this.nome = cliente.nome();
        this.telefone = cliente.telefone();
        this.email = cliente.email();
        this.cpf = cliente.cpf();
        this.endereco = cliente.endereco();
        this.ativo = true;
    }
}
