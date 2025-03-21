package med.voll.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.endereco.Endereco;
import med.voll.api.medicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid DadosCadastroMedicos medico){
        repository.save(new Medico(medico));
    }
    @GetMapping
    public Page<DadosListaMedico> listaMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(l -> new DadosListaMedico(l.getId(),l.getNome(),l.getEmail(), l.getCrm(), l.getEspecialidade()));
    }

    @PutMapping
    @Transactional
    public void atualizaMedico(@RequestBody @Valid DadosAtualizaMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizaInformacao(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMedico(@PathVariable Long id){
        //repository.deleteById(id); exclusao permanente
        var medico = repository.getReferenceById(id);//marca no banco como excluido
        medico.excluir();
    }
}
