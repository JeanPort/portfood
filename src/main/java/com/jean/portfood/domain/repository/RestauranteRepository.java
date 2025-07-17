package com.jean.portfood.domain.repository;

import com.jean.portfood.domain.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
    //@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    //List<Restaurante> findAll();


}
