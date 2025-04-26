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
    AND c.ativo = false
    """)
    boolean existsByClienteIdAndDataBetween(@NotNull Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    @Query("""
    SELECT COUNT(c) > 0 FROM Consulta c
    WHERE c.medico.id = :medicoId
      AND c.data = :data
      AND c.ativo = false
""")
    boolean existsByMedicoIdAndDataAndAtivoTrue(Long medicoId, @NotNull @Future LocalDateTime data);
}
