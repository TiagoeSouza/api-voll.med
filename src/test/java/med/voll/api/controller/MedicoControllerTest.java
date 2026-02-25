package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medico.*;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosEndereco> dadosEnderecoJson;

//    @Autowired
//    private JacksonTester<Especialidade> especialidadeJson;

    @MockBean
    private MedicoRepository medicoRepository;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;


    @Test
    @DisplayName("Http 400, body invalido")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Http 500, por não enviar o body")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/medicos"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    @DisplayName("Deveria devolver http 201, quando informações validas")
    @WithMockUser
    void cadastrar_cenario3() throws Exception {

        DadosCadastroMedico dadosCadastroMedico = new DadosCadastroMedico(
                "Médico Test - Controller",
                "medico.teste@controller.com",
                "11985156678",
                "98990",
                Especialidade.GERAL,
                dadosEndereco()
        );

        Medico medico = new Medico(dadosCadastroMedico);

        DadosDetalhamentoMedico dadosDetalhamentoMedico = new DadosDetalhamentoMedico(medico);
        when(medicoRepository.save(any())).thenReturn(medico);

        MockHttpServletResponse response = mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroMedicoJson.write(
                                dadosCadastroMedico
                        ).getJson())
                )
                .andReturn().getResponse();

        String jsonEsperado = dadosDetalhamentoMedicoJson.write(
                dadosDetalhamentoMedico
        ).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(jsonEsperado);
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Rua Dos Bobos",
                "Sem numero",
                "00068001",
                "Perdida",
                "SP",
                "CASA",
                "0"
        );
    }
}