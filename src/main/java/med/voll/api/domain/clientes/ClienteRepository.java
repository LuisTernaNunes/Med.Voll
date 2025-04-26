package med.voll.api.domain.clientes;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ClienteRepository extends JpaRepository<Cliente , Long>{
    Page<Cliente> findAllByAtivoTrue(Pageable paginacao);


    boolean existsByIdAndAtivoTrue(Long id);


}
