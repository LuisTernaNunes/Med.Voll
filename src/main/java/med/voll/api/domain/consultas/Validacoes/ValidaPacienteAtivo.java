package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.clientes.ClienteRepository;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo  implements ValidadorAgendamento{
    @Autowired
    ClienteRepository repository;
    public void validar(DadosAgendamentoConsulta dados){

        if (dados.idPaciente() == null){
            return;
        }

        if (!repository.existsByIdAndAtivoTrue(dados.idPaciente())){
            throw new ValidacaoException("Paciente excluido");
        }
    }
}
