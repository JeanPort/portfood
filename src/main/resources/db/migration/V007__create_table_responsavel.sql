create table tbl_restaurante_responsavel(
	restaurante_id bigint not null,
    responsavel_id bigint not null
)engine=InnoDB;

alter table tbl_restaurante_responsavel add constraint fk_restaurante_responsavel_restaurante foreign key (restaurante_id) references tbl_restaurante(id);
alter table tbl_restaurante_responsavel add constraint fk_restaurante_responsavel_responsavel foreign key (responsavel_id) references tbl_usuario(id);