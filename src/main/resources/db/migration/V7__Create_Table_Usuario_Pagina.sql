create table usuario_pagina (
	usuario_id bigint not null,
	pagina_id bigint not null
) engine=InnoDB;

    
alter table usuario_pagina 
add constraint UKmklr1hyfynpc721vnxvg39x6t unique (usuario_id, pagina_id);


alter table usuario_pagina 
add constraint FKon6wyd96gmdgbqwkff8ipjytb foreign key (pagina_id) references pagina (id);


alter table usuario_pagina 
add constraint FKar9wqe3ljm8rghas7c1vd96ux foreign key (usuario_id) references usuario (id);


INSERT INTO usuario_pagina ( usuario_id, pagina_id ) VALUES ( 1, 1 );
INSERT INTO usuario_pagina ( usuario_id, pagina_id ) VALUES ( 1, 2 );
INSERT INTO usuario_pagina ( usuario_id, pagina_id ) VALUES ( 1, 3 );
INSERT INTO usuario_pagina ( usuario_id, pagina_id ) VALUES ( 1, 4 );
INSERT INTO usuario_pagina ( usuario_id, pagina_id ) VALUES ( 1, 5 );

