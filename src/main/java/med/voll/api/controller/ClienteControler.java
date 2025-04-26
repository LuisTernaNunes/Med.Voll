package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.clientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/Clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteControler {
    @Autowired
    ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadCliente(@RequestBody @Valid DadosCadCliente DadosCliente, UriComponentsBuilder uriBuild){
        var cliente = new Cliente(DadosCliente);
        repository.save(cliente);
        var uri = uriBuild.path("clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCompletoCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaClientes>> listaClientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAllByAtivoTrue(paginacao).map(c -> new DadosListaClientes(c.getId(),c.getNome(),c.getEmail(),c.getTelefone()));
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizaCliente(@RequestBody @Valid DadosAtualizaCliente dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizainformacao(dados);
        return ResponseEntity.ok(new DadosCompletoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity apagaCliente(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        cliente.apagaCliente();
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity detalhaCliente(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosCompletoCliente(cliente));
    }
}
