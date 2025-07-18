package com.jean.portfood.api.controller;

import com.jean.portfood.domain.entity.Estado;
import com.jean.portfood.domain.repository.EstadoRepository;
import com.jean.portfood.domain.service.CadastroEstadoService;
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
        var estado = cadastroEstado.buscarOuFalhar(estadoId);
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
        estado = cadastroEstado.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Void> remover(@PathVariable Long estadoId){

        cadastroEstado.remover(estadoId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@RequestBody Estado estado, @PathVariable Long estadoId){
        estado = cadastroEstado.atualizar(estado, estadoId);
        return ResponseEntity.ok(estado);
    }
}
