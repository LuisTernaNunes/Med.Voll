package med.voll.api.domain.usuario;

public record DadosOkUsuario(String login, String senha) {
    public DadosOkUsuario(Usuario user){
        this(user.getLogin(),user.getSenha());
    }
}
