package med.voll.api.clientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadCliente(@NotBlank
                              String nome,
                              @NotBlank
                              @Email
                              String email,
                              @NotBlank
                              @Pattern(regexp = "\\d{11}")
                              @CPF
                              String cpf,
                              @NotBlank
                              String telefone,
                              @NotNull @Valid
                              DadosEndereco endereco) {
}
