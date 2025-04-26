package med.voll.api.domain.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByIdAndAtivoFalse(@NotNull Long id);

    @Query("""
    SELECT COUNT(c) > 0 FROM Consulta c
    WHERE c.cliente.id = :id
    AND c.data BETWEEN :primeiroHorario AND :ultimoHorario
    """)
    boolean existsByClienteIdAndDataBetween(@NotNull Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndData(Long aLong, @NotNull @Future LocalDateTime data);
}
