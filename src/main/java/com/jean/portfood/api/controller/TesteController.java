package com.jean.portfood.api.controller;


import com.jean.portfood.domain.entity.Restaurante;
import com.jean.portfood.domain.repository.RestauranteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping
public class TesteController {

    private final RestauranteRepository restauranteRepository;

    public TesteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("/restaurantes/buscar")
    public ResponseEntity<List<Restaurante>> find(
            @RequestParam String nome,
            @RequestParam BigDecimal taxaInicial,
            @RequestParam BigDecimal taxaFinal){
        var restaurantes = restauranteRepository.find(nome, taxaInicial, taxaFinal);
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/restaurantes/frete-gratis")
    public ResponseEntity<List<Restaurante>> findComFreteGratis(@RequestParam String nome){
        var restaurantes = restauranteRepository.findComFreteGratis(nome);
        return ResponseEntity.ok(restaurantes);
    }


}
