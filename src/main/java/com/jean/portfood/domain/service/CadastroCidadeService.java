package com.jean.portfood.domain.service;

import com.jean.portfood.domain.entity.Cidade;
import com.jean.portfood.domain.exception.EntidadeEmUsoException;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.repository.CidadeRepository;
import com.jean.portfood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CadastroCidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    @PostMapping
    public Cidade salvar(Cidade cidade) {
        var estadoId = cidade.getEstado().getId();
        var estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado com codigo %d não encontrado", estadoId)));

        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void remover(Long cidadeId){
        try {
            cidadeRepository.deleteById(cidadeId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Cidade com codigo %d não encontrado", cidadeId));
        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cidade com codigo %d não pode ser removida", cidadeId));
        }
    }

    public Cidade atualizar(Cidade cidade, Long cidadeId){
        var estadoId = cidade.getEstado().getId();
        var cidadeAtual = cidadeRepository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cidade com codigo %d não encontrado", cidadeId)));
        var estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado com codigo %d não encontrado", estadoId)));


        BeanUtils.copyProperties(cidade, cidadeAtual, "id", "estado");
        cidadeAtual.setEstado(estado);
        return cidadeRepository.save(cidadeAtual);
    }
}
