package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultas.Consulta;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import med.voll.api.domain.consultas.DadosDetalhamentoConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultasController {

    @Autowired
    ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        //Consulta consulta = new Consulta(dados);
        //repository.save(consulta);
        return ResponseEntity.ok(new DadosDetalhamentoConsultas(dados.idPaciente(),dados.idMedico(),dados.data()));
    }
}
