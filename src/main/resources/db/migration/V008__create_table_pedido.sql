create table tbl_pedido(
	id bigint not null auto_increment primary key,
    sub_total decimal(10,2) not null,
    taxa_frete decimal(10,2) not null,
    valor_total decimal(10,2) not null,
    status_pedido varchar(20) not null,
    forma_pagamento_id bigint not null,
    restaurante_id bigint not null,
    cliente_id bigint not null,
    endereco_cep varchar(50) not null,
    endereco_logradouro varchar(50) not null,
    endereco_numero varchar(50) not null,
    endereco_complemento varchar(50) not null,
    endereco_bairro varchar(50) not null,
    endereco_cidade_id bigint not null,
    data_criacao datetime not null,
    data_confirmacao datetime null,
    data_cancelamento datetime null,
    data_entrega datetime null
)engine=InnoDB;

alter table tbl_pedido add constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references tbl_forma_pagamento(id);
alter table tbl_pedido add constraint fk_pedido_restaurante foreign key (restaurante_id) references tbl_restaurante(id);
alter table tbl_pedido add constraint fk_pedido_cliente foreign key (cliente_id) references tbl_usuario(id);
alter table tbl_pedido add constraint fk_pedido_endereco_cidade foreign key (endereco_cidade_id) references tbl_cidade(id);