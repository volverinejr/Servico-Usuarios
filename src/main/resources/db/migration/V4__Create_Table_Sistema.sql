create table sistema (
	id bigint not null auto_increment,
	nome varchar(100) not null,

	created_by varchar(255),
	created_date datetime(6),
	
	last_modified_by varchar(255),
	last_modified_date datetime(6),
	
	primary key (id)
) engine=InnoDB;


alter table sistema 
add constraint UK_sistemaa0jvuncak7b0sqo2fi unique (nome);


INSERT INTO sistema ( nome) VALUES ( 'Matrix' );
