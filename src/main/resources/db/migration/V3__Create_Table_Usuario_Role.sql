create table usuario_role (
	usuario_id bigint not null,
	role_id bigint not null
) engine=InnoDB;


alter table usuario_role 
add constraint UKkklnhqdg21r58ek7bt4f44k8t unique (usuario_id, role_id);



alter table usuario_role 
add constraint FKe7gfguqsiox6p89xggm8g2twf foreign key (role_id) references role (id);


alter table usuario_role 
add constraint FKpc2qjts6sqq4hja9f6i3hf0ep foreign key (usuario_id) references usuario (id);


INSERT INTO usuario_role ( usuario_id, role_id ) VALUES ( 1, 1 );
INSERT INTO usuario_role ( usuario_id, role_id ) VALUES ( 1, 2 );

