package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidaHoraAntecedecia  implements ValidadorAgendamento{
    public void validar(DadosAgendamentoConsulta dados) {
    var dataConsulta = dados.data();
    var horaAtual = LocalDateTime.now();
    var diferenca = Duration.between(horaAtual,dataConsulta).toMinutes();
    if(diferenca < 30){
        throw new ValidacaoException("Consulta com menos de 30minutos de antecedencia");
    }
}
}
