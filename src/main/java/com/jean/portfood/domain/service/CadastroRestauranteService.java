package com.jean.portfood.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.portfood.domain.entity.Restaurante;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.repository.CozinhaRepository;
import com.jean.portfood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public Restaurante salvar(Restaurante restaurante) {
        var cozinhaId = restaurante.getCozinha().getId();
        var cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha com codigo %d não encontrada", cozinhaId));
        }

        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante, Long restauranteId){
        var cozinhaId = restaurante.getCozinha().getId();
        var restauranteAtual = restauranteRepository.buscar(restauranteId);
        var cozinha = cozinhaRepository.buscar(cozinhaId);

        if (restauranteAtual == null){
            throw new EntidadeNaoEncontradaException(
                    String.format("O restaurante com o codigo %d não existe", restauranteId)
            );
        }

        if (cozinha == null){
            throw new EntidadeNaoEncontradaException(
                    String.format("A cozinha com o codigo %d não existe", cozinhaId)
            );
        }

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "dataCadastro", "dataAtualizacao", "cozinha");
        restauranteAtual.setCozinha(cozinha);
        return restauranteRepository.salvar(restauranteAtual);
    }

    public Restaurante atualizarParcial(Map<String, Object> campos, Long restauranteId){

        var restaurante = restauranteRepository.buscar(restauranteId);
        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com codigo %d não encontrado", restauranteId));
        }

        merge(campos, restaurante);

        return atualizar(restaurante, restauranteId);
    }

    private static void merge(Map<String, Object> campos, Restaurante restauranteDestino) {

        var mapper = new ObjectMapper();
        var restauranteOrigem = mapper.convertValue(campos, Restaurante.class);

        campos.forEach((nomeProp, valorProp) -> {
            var field = ReflectionUtils.findField(Restaurante.class, nomeProp);
            field.setAccessible(true);
            var novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}
