package com.jean.portfood.jpa;

import com.jean.portfood.domain.entity.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    EntityManager entityManager;

    public List<Cozinha> listar(){
        TypedQuery<Cozinha> selectCFromCozinhaC = entityManager.createQuery("select c from cozinha c", Cozinha.class);
        return  selectCFromCozinhaC.getResultList();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return entityManager.merge(cozinha);
    }

    public Cozinha buscar(Long id){
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    public void remove(Cozinha cozinha) {
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }
}
