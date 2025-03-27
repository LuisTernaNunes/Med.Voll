package med.voll.api.domain.clientes;

import med.voll.api.domain.endereco.Endereco;

public record DadosCompletoCliente(Long id,
                                   String nome,
                                   String telefone,
                                   String email,
                                   String cpf,
                                   Endereco endereco) {
    public DadosCompletoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(),cliente.getTelefone(),cliente.getEmail(),cliente.getCpf(),cliente.getEndereco());
    }
}
