
create table tbl_forma_pagamento(
	id bigint not null auto_increment primary key,
    descricao varchar(50) not null
)engine=InnoDB;

create table tbl_permicao(
	id bigint not null auto_increment primary key,
    nome varchar(50) not null,
    descricao varchar(100) null
)engine=InnoDB;

create table tbl_grupo(
	id bigint not null auto_increment primary key,
    nome varchar(50) not null
)engine=InnoDB;

create table tbl_grupo_permicao(
	grupo_id bigint not null,
    permicao_id bigint not null,

    primary key (grupo_id, permicao_id)
)engine=InnoDB;

alter table tbl_grupo_permicao add constraint fk_grupo_permicao_grupo foreign key (grupo_id) references tbl_grupo(id);
alter table tbl_grupo_permicao add constraint fk_grupo_permicao_permicao foreign key (permicao_id) references tbl_permicao(id);

create table tbl_usuario(
	id bigint not null auto_increment primary key,
    nome varchar(50) not null,
    email varchar(50) not null unique,
    senha varchar(20) not null,
    data_cadastro datetime not null
)engine=InnoDB;

create table tbl_usuario_grupo(
	usuario_id bigint not null,
    grupo_id bigint not null,

    primary key(usuario_id, grupo_id)
)engine=InnoDB;

alter table tbl_usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references tbl_usuario(id);
alter table tbl_usuario_grupo add constraint fk_usuario_grupo_grupo foreign key (grupo_id) references tbl_grupo(id);
