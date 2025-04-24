package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.clientes.DadosCompletoCliente;
import med.voll.api.domain.usuario.*;
import med.voll.api.infra.security.TokenService;
import med.voll.api.infra.security.dadosTokenJWT;
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
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity efetuaLogin(@RequestBody @Valid DadosLogin dados){
        System.out.println(dados.login()+" "+dados.senha());
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new dadosTokenJWT(tokenJWT));
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
