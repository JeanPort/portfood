
create table tbl_produto(
	id bigint not null auto_increment primary key,
    nome varchar(50) not null,
    descricao varchar(255),
    preco decimal(10,2) not null,
    ativo boolean not null
)engine=InnoDB;

create table tbl_foto_produto(
	id bigint not null auto_increment primary key,
    nome_arquivo varchar(50) not null,
    descricao varchar(255),
    contentType varchar(255) not null,
    tamanho long not null,
    produto_id bigint not null
)engine=InnoDB;

alter table tbl_foto_produto add constraint fk_foto_produto foreign key (produto_id) references tbl_produto(id)