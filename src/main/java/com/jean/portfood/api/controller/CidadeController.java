package com.jean.portfood.api.controller;

import com.jean.portfood.domain.entity.Cidade;
import com.jean.portfood.domain.repository.CidadeRepository;
import com.jean.portfood.domain.service.CadastroCidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeRepository cidadeRepository;
    private final CadastroCidadeService cadastroCidade;

    public CidadeController(CidadeRepository cidadeRepository, CadastroCidadeService cadastroCidade) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroCidade = cadastroCidade;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        var cidades = cidadeRepository.findAll();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId){
        var cidade = cadastroCidade.buscarOuFalhar(cidadeId);
        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
        cidade = cadastroCidade.salvar(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Void> remover(@PathVariable Long cidadeId){
        cadastroCidade.remover(cidadeId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade, @PathVariable Long cidadeId){
        cidade = cadastroCidade.atualizar(cidade, cidadeId);
        return ResponseEntity.ok(cidade);
    }
}
