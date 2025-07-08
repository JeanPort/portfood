package com.jean.portfood.domain.service;

import com.jean.portfood.domain.entity.Restaurante;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.repository.CozinhaRepository;
import com.jean.portfood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
