package med.voll.api.domain.medicos;

import med.voll.api.domain.endereco.Endereco;

public record dadosCompletoMedico (Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco){
    public dadosCompletoMedico(Medico medico){
        this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getTelefone(),medico.getCrm(),medico.getEspecialidade(),medico.getEndereco());
    }

}
