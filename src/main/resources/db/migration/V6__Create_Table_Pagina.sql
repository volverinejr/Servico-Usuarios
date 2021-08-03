create table pagina (
	id bigint not null auto_increment,
	
	nome varchar(100) not null,
	rota varchar(100) not null,
	menu_id bigint not null,
	sistema_id bigint not null,

	created_by varchar(255),
	created_date datetime(6),
	
	last_modified_by varchar(255),
	last_modified_date datetime(6),
	
	primary key (id)
) engine=InnoDB;


alter table pagina 
add constraint FKtm73jtiago93b1qd403px6y5 foreign key (menu_id) references menu (id);


alter table pagina 
add constraint FK2qgsntjjalgfxxhectsu0ge7k foreign key (sistema_id) references sistema (id);


INSERT INTO pagina ( nome, rota, menu_id, sistema_id ) VALUES ( 'Sistema', 'sistema', 1, 1 );
INSERT INTO pagina ( nome, rota, menu_id, sistema_id ) VALUES ( 'Menu', 'menu', 1, 1 );
INSERT INTO pagina ( nome, rota, menu_id, sistema_id ) VALUES ( 'Página', 'pagina', 1, 1 );


INSERT INTO pagina ( nome, rota, menu_id, sistema_id ) VALUES ( 'Permissão', 'permissao', 2, 1 );
INSERT INTO pagina ( nome, rota, menu_id, sistema_id ) VALUES ( 'Usuário', 'user', 2, 1 );
