package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroMedicos dadosMedico, UriComponentsBuilder uriBuild){
        var medico = new Medico(dadosMedico);
        repository.save(medico);
        var uri = uriBuild.path("/medicos/${id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new dadosCompletoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaMedico>> listaMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(l -> new DadosListaMedico(l.getId(),l.getNome(),l.getEmail(), l.getCrm(), l.getEspecialidade()));
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaMedico(@RequestBody @Valid DadosAtualizaMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizaInformacao(dados);
        return ResponseEntity.ok(new dadosCompletoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedico(@PathVariable Long id){
        //repository.deleteById(id); exclusao permanente
        var medico = repository.getReferenceById(id);//marca no banco como excluido
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity buscaMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new dadosCompletoMedico(medico));
    }
}
