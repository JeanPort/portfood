create table tbl_restaurante(
	id bigint not null auto_increment primary key,
    nome varchar(50) not null,
    taxa_frete decimal(10,2) not null,
    ativo boolean not null,
    aberto boolean not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    cozinha_id bigint not null,
    endereco_cep varchar(50) null,
    endereco_logradouro varchar(50) null,
    endereco_numero varchar(50) null,
    endereco_complemento varchar(50) null,
    endereco_bairro varchar(50) null,
    endereco_cidade_id bigint null
)engine=InnoDB;

alter table tbl_restaurante add constraint fk_retaurante_cozinha foreign key (cozinha_id) references tbl_cozinha(id);
alter table tbl_restaurante add constraint fk_retaurante_cidade foreign key (endereco_cidade_id) references tbl_cidade(id);