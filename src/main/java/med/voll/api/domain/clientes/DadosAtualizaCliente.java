package med.voll.api.domain.clientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizaCliente(@NotNull Long id,
                                   String nome,
                                   String email,
                                   String telefone,
                                   DadosEndereco endereco) {
}
