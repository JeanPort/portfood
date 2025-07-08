package com.jean.portfood.api.controller;

import com.jean.portfood.domain.entity.Cozinha;
import com.jean.portfood.domain.exception.EntidadeEmUsoException;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.repository.CozinhaRepository;
import com.jean.portfood.domain.service.CadastroCozinhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    
    private final CozinhaRepository cozinhaRepository;
    private final CadastroCozinhaService cadastroCozinha;

    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinha) {
        this.cozinhaRepository = cozinhaRepository;
        this.cadastroCozinha = cadastroCozinha;
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cozinha>> listar(){
        var cozinhas = cozinhaRepository.listar();
        return ResponseEntity.ok(cozinhas);
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
        var cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cozinha);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha){
        cozinha = cadastroCozinha.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){

        try {
            cozinha = cadastroCozinha.atualizar(cozinhaId, cozinha);
            return ResponseEntity.ok(cozinha);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }


    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> remover(@PathVariable Long cozinhaId){
        try {
            cadastroCozinha.remover(cozinhaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch (EntidadeNaoEncontradaException ex){
            return ResponseEntity.notFound().build();
        }

    }
}
