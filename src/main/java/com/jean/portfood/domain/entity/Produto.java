package com.jean.portfood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tbl_produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}
