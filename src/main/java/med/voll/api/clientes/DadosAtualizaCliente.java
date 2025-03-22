package med.voll.api.clientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;

public record DadosAtualizaCliente(@NotNull Long id,
                                   String nome,
                                   String email,
                                   String telefone,
                                   DadosEndereco endereco) {
}
