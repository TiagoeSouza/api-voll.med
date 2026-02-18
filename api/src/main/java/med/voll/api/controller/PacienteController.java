package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        pacienteRepository.save(paciente);

        URI uri = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping()
    @Transactional(readOnly = true)
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault Pageable paginacao,
                                                              @RequestParam(required = false) Boolean ativo) {
        Page<DadosListagemPaciente> page = ativo != null
                ? pacienteRepository.findAllByAtivo(ativo, paginacao).map(DadosListagemPaciente::new)
                : pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaPaciente dados) {
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();

        return ResponseEntity.noContent().build();
    }
}