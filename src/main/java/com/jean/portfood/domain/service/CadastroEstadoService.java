package com.jean.portfood.domain.service;

import com.jean.portfood.domain.entity.Estado;
import com.jean.portfood.domain.exception.EntidadeEmUsoException;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;


    public CadastroEstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public void remover(Long estadoId){
        try {
            estadoRepository.remover(estadoId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Estado com codigo %d não encontrado", estadoId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Estado com codigo %d não pode ser removi", estadoId));
        }
    }

    public Estado atualizar(Estado estado, Long estadoId){

        var estadoAtual = estadoRepository.buscar(estadoId);

        if (estadoAtual == null) {
            throw new EntidadeNaoEncontradaException(String.format("Estado com codigo %d não encontrado", estadoId));
        }

        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.salvar(estadoAtual);
    }
}
