package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
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
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        Medico medico = new Medico(dados);
        medicoRepository.save(medico);

        URI uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

//    @GetMapping
//    @Transactional(readOnly = true)
//    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 3, sort = {"nome"}) Pageable paginacao) {
//        Page<DadosListagemMedico> page = medicoRepository
//                .findAll(paginacao)
//                .map(DadosListagemMedico::new);
//        return ResponseEntity.ok(page);
//    }

    @GetMapping()
    @Transactional(readOnly = true)
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 3, sort = {"nome"}) Pageable paginacao,
                                                            @RequestParam(required = false) Boolean ativo) {
        Page<DadosListagemMedico> page = ativo != null
                ? medicoRepository.findAllByAtivo(ativo, paginacao).map(DadosListagemMedico::new)
                : medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaMedico dados) {
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
//        medicoRepository.deleteById(id);
        Medico medico = medicoRepository.getReferenceById(id);
        medico.inativar();

        return ResponseEntity.noContent().build();
    }
}
