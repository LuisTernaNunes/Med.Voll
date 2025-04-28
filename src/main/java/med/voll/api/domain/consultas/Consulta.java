package med.voll.api.domain.consultas;

import jakarta.persistence.*;
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
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    private LocalDateTime data;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public Consulta(Cliente paciente, Medico medico, LocalDateTime data) {
        this.cliente = paciente;
        this.medico = medico;
        this.data = data;
        this.ativo = false;
    }


    public void CancelaConsulta(DadosCancelamento dados){
        this.ativo = true;
        this.motivoCancelamento = dados.motivoCancelamento();
    }
}
