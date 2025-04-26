package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import med.voll.api.domain.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HorarioConsultasMedico implements ValidadorAgendamento{
    @Autowired
    ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        if(repository.existsByMedicoIdAndData(dados.idMedico(),dados.data())){
            throw new ValidacaoException("Medico ja possui consulta nesse horario");
        }
    }
}
