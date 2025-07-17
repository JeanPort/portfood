package com.jean.portfood.infrastructure.repository;

import com.jean.portfood.domain.entity.Restaurante;
import com.jean.portfood.domain.repository.RestauranteRepository;
import com.jean.portfood.domain.repository.RestauranteRepositoryQueries;
import com.jean.portfood.infrastructure.repository.spec.RestauranteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired @Lazy
    private RestauranteRepository repository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpql = "select r from Restaurante r where r.nome like :nome and r.taxaFrete between :taxaInicial and :taxaFinal";

        return entityManager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaFreteInicial)
                .setParameter("taxaFinal", taxaFreteFinal)
                .getResultList();

    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return repository.findAll(RestauranteSpec.comFreteGratis().and(RestauranteSpec.comNomeSemelhante(nome)));
    }
}
