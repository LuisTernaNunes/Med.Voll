package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.clientes.Cliente;
import med.voll.api.clientes.ClienteRepository;
import med.voll.api.clientes.DadosCadCliente;
import med.voll.api.clientes.DadosListaClientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Clientes")
public class ClienteControler {
    ClienteRepository repository;

    public void cadCliente(@RequestBody @Valid DadosCadCliente cliente){
        repository.save(new Cliente(cliente));
    }

    public Page<DadosListaClientes> listaClientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(c -> new DadosListaClientes(c.getId(),c.getNome(),c.getEmail(),c.getTelefone()));
    }
}
