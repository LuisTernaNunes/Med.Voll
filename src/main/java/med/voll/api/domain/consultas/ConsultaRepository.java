package med.voll.api.domain.consultas;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByIdAndCancelamentoFalse(@NotNull Long id);
}
