package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivo(Boolean ativo, Pageable paginacao);

    @Query("""
            select m.ativo from paciente m where m.id = :id
            """)
    Boolean findAtivoById(@NotNull Long id);
}
