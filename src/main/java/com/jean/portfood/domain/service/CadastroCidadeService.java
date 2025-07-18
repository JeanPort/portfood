package com.jean.portfood.domain.service;

import com.jean.portfood.domain.entity.Cidade;
import com.jean.portfood.domain.exception.EntidadeEmUsoException;
import com.jean.portfood.domain.exception.EntidadeNaoEncontradaException;
import com.jean.portfood.domain.exception.NegocioException;
import com.jean.portfood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_NAO_ENCONTRADA = "Cidade com codigo %d não encontrado";
    private static final String MSG_CIDADE_EM_CONFLITO = "Cidade com codigo %d não pode ser removida";

    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstadoService;

    public CadastroCidadeService(CidadeRepository cidadeRepository, CadastroEstadoService cadastroEstadoService) {
        this.cidadeRepository = cidadeRepository;

        this.cadastroEstadoService = cadastroEstadoService;
    }

    @PostMapping
    public Cidade salvar(Cidade cidade) {
        try {
            var estadoId = cidade.getEstado().getId();
            var estado = cadastroEstadoService.buscarOuFalhar(estadoId);
            cidade.setEstado(estado);
            return cidadeRepository.save(cidade);
        }catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    public void remover(Long cidadeId){
        try {
            cidadeRepository.deleteById(cidadeId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_CONFLITO, cidadeId));
        }
    }

    public Cidade atualizar(Cidade cidade, Long cidadeId){
        var cidadeAtual = buscarOuFalhar(cidadeId);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return salvar(cidadeAtual);
    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }
}
