package com.jean.portfood.api.controller;

import com.jean.portfood.domain.entity.Estado;
import com.jean.portfood.domain.repository.EstadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listar(){
        var estados = estadoRepository.listar();
        return ResponseEntity.ok(estados);
    }
}
