package med.voll.api.domain.consultas;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.clientes.ClienteRepository;
import med.voll.api.domain.consultas.Validacoes.ValidadorAgendamento;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsulta {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorAgendamento> validadorAgendamentos;

    public void agendar(DadosAgendamentoConsulta dados){
        if(!clienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Paciente nao cadastrado");
        }

        if(dados.idMedico() == null || !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException(dados.idMedico()+"Medico nao cadastrado");
        }

        validadorAgendamentos.forEach(v -> v.validar(dados));

        var paciente = clienteRepository.findById(dados.idPaciente()).get();
        var medico = escolheMedico(dados);
        var consulta = new Consulta(paciente,medico,dados.data());
        if(medico == null){
            throw new ValidacaoException("Nenhum Medico livre nessa data");
        }
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
