package com.jean.portfood.domain.repository;

import com.jean.portfood.domain.entity.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
    List<Restaurante> findComFreteGratis(String nome);
}
