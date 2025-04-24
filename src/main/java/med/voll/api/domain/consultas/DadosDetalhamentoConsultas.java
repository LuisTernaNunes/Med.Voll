package med.voll.api.domain.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultas(
        Long idPaciente,
        @NotNull
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime data
) {
}
