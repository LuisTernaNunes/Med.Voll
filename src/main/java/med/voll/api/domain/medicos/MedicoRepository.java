package med.voll.api.domain.medicos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico , Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);



    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = true
            and
            especialidade = :especialidade
            and
            m.id NOT in(
                SELECT c.medico.id FROM Consulta c
                WHERE
                c.data = :data
                OR
                c.cancelada = true
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Medico escolherMedicoLivreData(Especialidade especialidade, @NotNull @Future LocalDateTime data);

    @Query("""
        SELECT m.ativo
        FROM Medico m
        WHERE 
        m.id = :id
            """)
    boolean findAtivoById(Long idMedico);

    boolean existsByMedicoIdAndData(Long aLong, @NotNull @Future LocalDateTime data);
}
