package com.jean.portfood.api.controller;

import com.jean.portfood.domain.entity.Cozinha;
import com.jean.portfood.domain.repository.CozinhaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    
    private final CozinhaRepository cozinhaRepository;

    public CozinhaController(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }
    
    @GetMapping
    public ResponseEntity<List<Cozinha>> listar(){
        var cozinhas = cozinhaRepository.listar();
        return ResponseEntity.ok(cozinhas);
    }
}
