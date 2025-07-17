package com.jean.portfood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @CreationTimestamp
    @Column(name = "data_cadastro", columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @ManyToMany
    @JoinTable(
            name = "tbl_usuario_grupo",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private List<Grupo> grupos = new ArrayList<>();
}
