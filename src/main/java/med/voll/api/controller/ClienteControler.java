package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.clientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Clientes")
public class ClienteControler {
    @Autowired
    ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadCliente(@RequestBody @Valid DadosCadCliente cliente){
        repository.save(new Cliente(cliente));
    }
    @GetMapping
    public Page<DadosListaClientes> listaClientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(c -> new DadosListaClientes(c.getId(),c.getNome(),c.getEmail(),c.getTelefone()));
    }
    @PutMapping
    @Transactional
    public void atualizaCliente(@RequestBody @Valid DadosAtualizaCliente dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizainformacao(dados);
    }
}
