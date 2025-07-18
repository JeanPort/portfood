package com.jean.portfood.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jean.portfood.domain.entity.Restaurante;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.exception.NegocioException;
import com.jean.portfood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.Map;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "O restaurante com o codigo %d não existe";
    private final RestauranteRepository restauranteRepository;
    private final CadastroCozinhaService cadastroCozinhaService;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CadastroCozinhaService cadastroCozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroCozinhaService = cadastroCozinhaService;
    }

    public Restaurante salvar(Restaurante restaurante) {
        try {
            var cozinhaId = restaurante.getCozinha().getId();
            var cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
            restaurante.setCozinha(cozinha);
            return restauranteRepository.save(restaurante);
        }catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public Restaurante atualizar(Restaurante restaurante, Long restauranteId){

        var restauranteAtual = buscarOuFalhar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "dataCadastro", "formasPagamento");

        return salvar(restauranteAtual);
    }

    public Restaurante atualizarParcial(Map<String, Object> campos, Long restauranteId){

        var restaurante = buscarOuFalhar(restauranteId);

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

    public Restaurante buscarOuFalhar(Long restauranteId){
        return restauranteRepository.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)
        ));
    }
}
