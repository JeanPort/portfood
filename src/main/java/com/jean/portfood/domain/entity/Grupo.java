package com.jean.portfood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_grupo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;

    @ManyToMany
    @JoinTable(name = "tbl_grupo_permicao",
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "permicao_id"))
    private List<Permicao> permicoes = new ArrayList<>();

}
