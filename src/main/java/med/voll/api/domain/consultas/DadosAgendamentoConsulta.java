package med.voll.api.domain.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medicos.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
