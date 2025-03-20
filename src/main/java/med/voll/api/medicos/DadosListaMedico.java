package med.voll.api.medicos;

public record DadosListaMedico(Long Id,
                               String nome,
                               String email,
                               String crm,
                               Especialidade especialidade) {
}
