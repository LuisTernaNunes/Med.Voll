package med.voll.api.domain.clientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;



public record DadosCadCliente(@NotBlank
                              String nome,
                              @NotBlank
                              @Email
                              String email,
                              @NotBlank
                              @Pattern(regexp = "\\d{11}")
                              @NotBlank
                              String cpf,
                              @NotBlank
                              String telefone,
                              @NotNull @Valid
                              DadosEndereco endereco) {
}
