package med.voll.api.domain.medicos;

import med.voll.api.domain.clientes.Cliente;
import med.voll.api.domain.clientes.DadosCadCliente;
import med.voll.api.domain.consultas.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    MedicoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Medicos nao disponiveis na data")
    void escolherMedicoNaoLivreData() {
        var proximaSegunda = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = cadastrarMedico("Joao","joao@irineumail.com","123412",Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Roberval","Roberval@Gmail.com","10034512354");
        cadastrarConsulta(medico,paciente,proximaSegunda);


        var medicoLivre = repository.escolherMedicoLivreData(Especialidade.CARDIOLOGIA,proximaSegunda);
        assertThat(medicoLivre).isNull();
    }


    @Test
    @DisplayName("Medicos disponiveis na data")
    void escolherMedicoLivreData() {
        var proximaSegunda = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var medico = cadastrarMedico("Joao","joao@irineumail.com","123412",Especialidade.CARDIOLOGIA);

        var medicoLivre = repository.escolherMedicoLivreData(Especialidade.CARDIOLOGIA,proximaSegunda);
        assertThat(medicoLivre).isEqualTo(medico);
    }



    //metodos para carregar no banco de dados
    private void cadastrarConsulta(Medico medico, Cliente paciente, LocalDateTime data) {
        em.persist(new Consulta(paciente, medico,data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Cliente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Cliente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedicos dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedicos(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadCliente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadCliente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}