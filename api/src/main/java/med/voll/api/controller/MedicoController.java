package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
     /*   System.out.println("Cadastrando medico...");
        System.out.println("Medico: " + dados.nome());*/
        medicoRepository.save(new Medico(dados));

    }

    @GetMapping
    @Transactional(readOnly = true)
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 3, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository
                .findAll(paginacao)
                .map(DadosListagemMedico::new);
    }

    @GetMapping("/{ativo}")
    @Transactional(readOnly = true)
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 3, sort = {"nome"}) Pageable paginacao, @PathVariable Boolean ativo) {
        return medicoRepository
                .findAllByAtivo(ativo, paginacao)
                .map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaMedico dados) {
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
//        medicoRepository.deleteById(id);
        Medico medico = medicoRepository.getReferenceById(id);
        medico.inativar();
    }
}
