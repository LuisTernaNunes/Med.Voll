package med.voll.api.controller;

import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    UsuarioRepository repository;
}
