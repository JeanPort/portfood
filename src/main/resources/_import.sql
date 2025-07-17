
insert into tbl_cozinha(id, nome) values (1, 'Brasileira');
insert into tbl_cozinha(id, nome) values (2, 'Japonesa');
insert into tbl_cozinha(id, nome) values (3, 'Arabe');

insert into tbl_estado(id, nome) values (1, 'Rio Grande do Sul');
insert into tbl_estado(id, nome) values (2, 'Rio Grande do Norte');
insert into tbl_estado(id, nome) values (3, 'São Paulo');

insert into tbl_cidade (id, nome, estado_id) values (1, 'Três Coroas', 1);
insert into tbl_cidade (id, nome, estado_id) values (2, 'Calorão', 2);
insert into tbl_cidade (id, nome, estado_id) values (3, 'Campinas', 3);

insert into tbl_forma_pagamento (id, descricao) values (1, 'Pix');
insert into tbl_forma_pagamento (id, descricao) values (2, 'Cartão de credito');
insert into tbl_forma_pagamento (id, descricao) values (3, 'Cartão de debito');
insert into tbl_forma_pagamento (id, descricao) values (4, 'Dinheiro');

insert into tbl_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, 'Porco na chapa', 10.90, 1, utc_timestamp, utc_timestamp, '95660000', 'Mariluz', '28', 'Perto do posto', 'Centro', 1);
insert into tbl_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (2, 'Italiany', 0.00, 3, utc_timestamp, utc_timestamp, '95650000', 'Osvaldo', '55', 'Perto do posto', 'Bom Pastor', 2);
insert into tbl_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (3, 'Temaky', 15.00, 2, utc_timestamp, utc_timestamp, '95670000', 'Carazal', '124', 'Perto do posto', 'Itatinga', 3);

insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,1);
insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,2);
insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,3);
insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,4);
insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (2,4);
insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (2,1);
insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (3,2);
insert into tbl_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (3,3);

