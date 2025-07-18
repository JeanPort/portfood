create table tbl_restaurante_forma_pagamento(
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,

    primary key (restaurante_id, forma_pagamento_id),
    constraint fk_restaurante_forma_pagamento_restaurante foreign key (restaurante_id) references tbl_restaurante(id),
    constraint fk_restaurante_forma_pagamento_pagamento foreign key (forma_pagamento_id) references tbl_forma_pagamento(id)
)engine=InnoDB;