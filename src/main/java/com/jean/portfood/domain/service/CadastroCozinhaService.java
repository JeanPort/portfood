package com.jean.portfood.domain.service;

import com.jean.portfood.domain.entity.Cozinha;
import com.jean.portfood.domain.exception.EntidadeEmUsoException;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public void remover(Long id){
        try {
            cozinhaRepository.remover(id);
        }catch (DataIntegrityViolationException d){
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de codigo %d não pode ser removida pois esta em uso", id)
            );
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Entidade de codigo %d não encontrada", id)
            );
        }
    }

    public Cozinha atualizar(Long id, Cozinha cozinha){

        var cozinhaAtual = cozinhaRepository.buscar(id);
        if (cozinhaAtual == null) {
            throw new EntidadeNaoEncontradaException(String.format("Entidade de codigo %d não encontrada", id));
        }
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaRepository.salvar(cozinhaAtual);
    }
}
