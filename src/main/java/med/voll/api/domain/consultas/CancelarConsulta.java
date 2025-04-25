package med.voll.api.domain.consultas;

import med.voll.api.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
@Service
public class CancelarConsulta {
    @Autowired
    ConsultaRepository consultaRepository;
    public void cancelamento(DadosCancelamento dados){
        if(!consultaRepository.existsByIdAndCancelamentoFalse(dados.id())){
            throw new ValidacaoException("Consulta nao encontrada");
        }
        var consulta = consultaRepository.findById(dados.id()).get();
        if(Duration.between(LocalDateTime.now(), consulta.getData()).toHours() < 24 &&
                LocalDateTime.now().isBefore(consulta.getData())){
            throw new ValidacaoException("Consulta com menos de 24H nao podem ser canceladas");
        }
        consulta.CancelaConsulta(dados);
        consultaRepository.save(consulta);
    }
}
