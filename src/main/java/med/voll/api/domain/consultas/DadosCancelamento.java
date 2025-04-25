package med.voll.api.domain.consultas;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamento(
        @NotNull
        Long id,
        @NotNull
        Cancelamento cancelamento) {
}
