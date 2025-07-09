package com.jean.portfood.infrastructure.repository;

import com.jean.portfood.domain.entity.Estado;
import com.jean.portfood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImp implements EstadoRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("select e from Estado e", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Long estadoId) {
        var estado = buscar(estadoId);
        if (estado == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(estado);
    }
}
