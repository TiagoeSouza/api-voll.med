package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null, quando unico medico cadastrado não está dísponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        // giver ou arrange
        LocalDateTime proximaSegundaAs10 = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .toLocalDate().atTime(10, 0);

        Medico medico = cadastrarMedico("Medico Test", "medico.test@voll.med", "989898", Especialidade.PEDIATRIA);
        Paciente paciente = cadastrarPaciente("Paciente Test", "paciente.test@voll.med", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        // when ou act
        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.PEDIATRIA, proximaSegundaAs10);

        // then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico, quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        // givern ou arrange
        LocalDateTime proximaSegundaAs10 = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .toLocalDate().atTime(10, 0);

        Medico medico = cadastrarMedico("Medico Test", "medico.test@voll.med", "989898", Especialidade.PEDIATRIA);

        // when ou act
        Medico medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.PEDIATRIA, proximaSegundaAs10);

        // then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }


    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        Medico medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        Paciente paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "11 900806621",
                crm,
                especialidade,
                dadosEndereco());
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "11 900805599",
                cpf,
                dadosEndereco());
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Rua Dos Bobos",
                "Sem Teto",
                "0000000",
                "São Paulo",
                "SP",
                null,
                null
        );
    }
}
