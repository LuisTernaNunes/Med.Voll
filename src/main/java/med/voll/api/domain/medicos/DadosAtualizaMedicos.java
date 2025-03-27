package med.voll.api.domain.medicos;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizaMedicos(@NotNull
                                   Long id,
                                   String nome,
                                   String telefone,
                                   DadosEndereco endereco) {
}
