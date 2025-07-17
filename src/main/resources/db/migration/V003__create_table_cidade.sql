
create table tbl_cidade(
	id bigint not null auto_increment primary key,
    nome varchar(50) not null,
    estado_id bigint not null
)engine=InnoDB;

alter table tbl_cidade add constraint fk_cidade_estado
foreign key (estado_id) references tbl_estado(id);
