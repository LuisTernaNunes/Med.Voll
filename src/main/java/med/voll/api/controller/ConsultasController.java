package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {
    @Autowired
    AgendaConsulta agendar;

    @Autowired

    CancelarConsulta cancelamento;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        agendar.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsultas(dados.idPaciente(),dados.idMedico(),dados.data()));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamento dados){
        cancelamento.cancelamento(dados);
        return ResponseEntity.ok().build();
    }


}
