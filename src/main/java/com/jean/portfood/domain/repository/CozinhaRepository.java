package com.jean.portfood.domain.repository;

import com.jean.portfood.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {

    List<Cozinha> findByNomeContaining(String nome);

}
