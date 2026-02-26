package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosListagemConsultasPaciente(
        @NotNull
        Long idConsulta,
        @NotBlank
        String nomePaciente,

        @NotBlank
        String nomeMedico,

        @NotNull
        LocalDateTime data
) {
    public DadosListagemConsultasPaciente(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getPaciente().getNome(),
                consulta.getMedico().getNome(),
                consulta.getData()
        );
    }
}
