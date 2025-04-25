package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import med.voll.api.domain.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo  implements ValidadorAgendamento{
    @Autowired
    MedicoRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        if(!repository.findAtivoById(dados.idMedico())){
            throw new ValidacaoException("Medico excluido");
        }
    }
}
