package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosLogin;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {
    @Autowired
    private AuthenticationManager manager;


    public ResponseEntity efetuaLogin(@RequestBody @Valid DadosLogin dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
