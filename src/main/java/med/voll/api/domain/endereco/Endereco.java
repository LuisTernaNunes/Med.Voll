package med.voll.api.domain.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(DadosEndereco endereco) {
        this.logradouro =  endereco.logradouro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.numero = endereco.numero();
        this.complemento =  endereco.complemento();
    }

    public void atualizaInformacao(DadosEndereco dados) {
        if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }
        if(dados.cep() != null){
            this.cep = dados.cep();
        }
        if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }
        if(dados.uf() != null){
            this.uf = dados.uf();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.numero() != null){
            this.numero = dados.numero();
        }
        if(dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        }
    }
}
