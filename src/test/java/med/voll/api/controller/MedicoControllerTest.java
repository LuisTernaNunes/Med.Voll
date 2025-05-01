package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medicos.DadosCadastroMedicos;
import med.voll.api.domain.medicos.Especialidade;
import med.voll.api.domain.medicos.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class MedicoControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroMedicos> dadosCadastroMedicosTester;


    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Retorno HTTP 400, Informaçoes invalidas")
    @WithMockUser
    void medico_cenario1() throws Exception{
        var response = mvc.perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Retorno 201, Cadastro relizado")
    @WithMockUser
    void cadastroMedico_Cenario1() throws Exception{

        var response = mvc.perform(post("/medicos").contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicosTester.write(new DadosCadastroMedicos("luis","luis@gmail.com","43900218525",
                        "312585",Especialidade.CARDIOLOGIA,new DadosEndereco("Rua 0","Belo Monte","85470000","Itapetininga",
                        "pr","355","Apartamento"))).getJson())).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}