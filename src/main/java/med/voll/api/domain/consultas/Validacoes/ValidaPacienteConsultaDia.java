package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.clientes.ClienteRepository;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteConsultaDia  implements ValidadorAgendamento{
    @Autowired
    ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteConsulta = repository.existsByClienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if(pacienteConsulta){
            throw new ValidacaoException("paciente com consulta nesse dia");
        }
    }
}
