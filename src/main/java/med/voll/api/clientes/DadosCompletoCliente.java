package med.voll.api.clientes;

import med.voll.api.endereco.Endereco;
import med.voll.api.medicos.Especialidade;
import med.voll.api.medicos.Medico;

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
