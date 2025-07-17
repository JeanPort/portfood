create table tbl_item_pedido(
	id	bigint not null primary key auto_increment,
    quantidade int not null,
    preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    observacao varchar(255) null,
    produto_id bigint not null,
    pedido_id bigint not null,

    unique key uk_item_pedido_produto (pedido_id, produto_id),
    constraint fk_item_pedido_pedido foreign key (pedido_id) references tbl_pedido(id),
    constraint fk_item_pedido_produto foreign key (produto_id) references tbl_produto(id)
)engine=InnoDB;