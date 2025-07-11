package com.jean.portfood.api.controller;

import com.jean.portfood.domain.entity.Estado;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.repository.EstadoRepository;
import com.jean.portfood.domain.service.CadastroEstadoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final CadastroEstadoService cadastroEstado;

    public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService cadastroEstado) {
        this.estadoRepository = estadoRepository;
        this.cadastroEstado = cadastroEstado;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listar(){
        var estados = estadoRepository.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
        var estado = estadoRepository.findById(estadoId);
        if (estado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado.get());
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
        estado = cadastroEstado.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId){
        try {
            cadastroEstado.remover(estadoId);
            return ResponseEntity.noContent().build();

        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@RequestBody Estado estado, @PathVariable Long estadoId){
        try {
            estado = cadastroEstado.atualizar(estado, estadoId);
            return ResponseEntity.ok(estado);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }
}
