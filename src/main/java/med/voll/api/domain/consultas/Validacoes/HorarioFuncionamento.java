package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamento  implements ValidadorAgendamento{
    public void validar(DadosAgendamentoConsulta dados){
        var horarioConsulta = dados.data();
        var domingo = horarioConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var abertura = horarioConsulta.getHour() < 7;
        var fechamento = horarioConsulta.getHour() > 18;
        if(domingo || abertura || fechamento){
            throw new ValidacaoException("Consulta fora do horario");
        }
    }
}
