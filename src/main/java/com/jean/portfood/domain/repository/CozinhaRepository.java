package com.jean.portfood.domain.repository;

import com.jean.portfood.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {


    //List<Cozinha> listarPorNome(String nome);

}
