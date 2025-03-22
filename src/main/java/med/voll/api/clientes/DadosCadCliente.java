package med.voll.api.clientes;

import med.voll.api.endereco.Endereco;

public record DadosCadCliente(String nome,
                              String email,
                              String cpf,
                              String telefone,
                              Endereco endereco) {
}
