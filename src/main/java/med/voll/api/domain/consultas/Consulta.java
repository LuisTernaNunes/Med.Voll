package med.voll.api.domain.consultas;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.clientes.Cliente;
import med.voll.api.domain.medicos.Medico;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peciente_id")
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    private LocalDateTime data;
    private Boolean cancelada;
    private Cancelamento cancelamento;

    public Consulta(Cliente paciente, Medico medico, @NotNull @Future LocalDateTime data) {
        this.cliente = paciente;
        this.medico = medico;
        this.data = data;
        this.cancelada = false;
    }

    public void CancelaConsulta(DadosCancelamento dados){
        this.cancelada = true;
        this.cancelamento = dados.cancelamento();
    }
}
