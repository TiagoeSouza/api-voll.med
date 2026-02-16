package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente paciente) {
/*        System.out.println("Cadastrando Paciente ...");
        System.out.println(paciente);*/

        pacienteRepository.save(new Paciente(paciente));
    }

    @GetMapping
    @Transactional(readOnly = true)
    public Page<DadosListagemPaciente> listar(@PageableDefault Pageable paginacao) {
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

    @GetMapping("/{ativo}")
    @Transactional(readOnly = true)
    public Page<DadosListagemPaciente> listar(@PageableDefault Pageable paginacao, @PathVariable Boolean ativo) {
        return pacienteRepository
                .findAllByAtivo(ativo, paginacao)
                .map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaPaciente dados) {
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }
}