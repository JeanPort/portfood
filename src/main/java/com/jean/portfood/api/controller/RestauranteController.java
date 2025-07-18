package com.jean.portfood.api.controller;

import com.jean.portfood.domain.entity.Restaurante;
import com.jean.portfood.domain.repository.RestauranteRepository;
import com.jean.portfood.domain.service.CadastroRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final CadastroRestauranteService cadastroRestaurante;

    public RestauranteController(RestauranteRepository restauranteRepository, CadastroRestauranteService cadastroRestaurante) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestaurante = cadastroRestaurante;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar(){
        var restaurantes = restauranteRepository.findAll();
        return ResponseEntity.ok(restaurantes);
    }


    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        var restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante){
        restaurante = cadastroRestaurante.salvar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    }


    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long restauranteId){
        restaurante = cadastroRestaurante.atualizar(restaurante, restauranteId);
        return ResponseEntity.ok(restaurante);
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizarParcial(@RequestBody Map<String, Object> campos, @PathVariable Long restauranteId){
        var restaurante = cadastroRestaurante.atualizarParcial(campos, restauranteId);
        return ResponseEntity.ok(restaurante);
    }
}
