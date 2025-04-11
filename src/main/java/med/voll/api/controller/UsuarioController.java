package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.clientes.DadosCompletoCliente;
import med.voll.api.domain.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity efetuaLogin(@RequestBody @Valid DadosLogin dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @Transactional
    @RequestMapping("/usuario")
    public ResponseEntity cadUsuario(@RequestBody @Valid DadosCadUsuario dados){
        Usuario usuario = new Usuario(dados);
        repository.save(usuario);
        return ResponseEntity.ok(new DadosOkUsuario(usuario));
    }

}
