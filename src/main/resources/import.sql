
insert into tbl_cozinha(id, nome) values (1, "Brasileira");
insert into tbl_cozinha(id, nome) values (2, "Japonesa");
insert into tbl_cozinha(id, nome) values (3, "Arabe");

insert into tbl_estado(id, nome) values (1, "Rio Grande do Sul");
insert into tbl_estado(id, nome) values (2, "Rio Grande do Norte");
insert into tbl_estado(id, nome) values (3, "São Paulo");

insert into tbl_cidade (id, nome, estado_id) values (1, "Três Coroas", 1);
insert into tbl_cidade (id, nome, estado_id) values (2, "Calorão", 2);
insert into tbl_cidade (id, nome, estado_id) values (3, "Campinas", 3);

insert into tbl_forma_pagamento (id, descricao) values (1, "Pix");
insert into tbl_forma_pagamento (id, descricao) values (2, "Cartão de credito");
insert into tbl_forma_pagamento (id, descricao) values (3, "Cartão de debito");
insert into tbl_forma_pagamento (id, descricao) values (4, "Dinheiro");

insert into tbl_restaurante (id, nome, taxa_frete, cozinha_id) values (1, "Porco na chapa", 10.90, 1);

