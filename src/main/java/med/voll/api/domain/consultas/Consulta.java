package med.voll.api.domain.consultas;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Consulta")
@Table(name = "Consultas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCliente;
    private Long idMedico;
    private LocalDateTime data;

    public Consulta(@Valid DadosAgendamentoConsulta dados) {
        this.idCliente = dados.idPaciente();
        this.idMedico = dados.idMedico();
        this.data = dados.data();
    }
}
