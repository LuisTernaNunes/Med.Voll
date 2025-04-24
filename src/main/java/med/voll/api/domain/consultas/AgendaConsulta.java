package med.voll.api.domain.consultas;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.clientes.ClienteRepository;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsulta {
    @Autowired
    ClienteRepository repository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    ConsultaRepository consultaRepository;

    public void agendar(DadosAgendamentoConsulta dados){
        if(!repository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente nao cadastrado");
        }

        if(dados.idMedico() != null || !repository.existsById(dados.idMedico())){
            throw new ValidacaoException("Medico nao cadastrado");
        }

        var paciente = repository.findById(dados.idPaciente()).get();
        var medico = escolheMedico(dados);
        var consulta = new Consulta(paciente,medico,dados.data());
        consultaRepository.save(consulta);
    }

    private Medico escolheMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade e obrigatorio se medico nao for escolhido");
        }
        return medicoRepository.escolherMedicoLivreData(dados.especialidade(),dados.data());
    }
}
