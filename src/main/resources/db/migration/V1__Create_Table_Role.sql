create table role (
	id bigint not null auto_increment,
	nome varchar(100) not null,

	created_by varchar(255),
	created_date datetime(6),
	
	last_modified_by varchar(255),
	last_modified_date datetime(6),
	
	primary key (id)
) engine=InnoDB;


alter table role 
add constraint UK_psbnsrja0jvuncak7b0sqo2fi unique (nome);


INSERT INTO role ( nome ) VALUES ( 'ROLE_ADMIN' );
INSERT INTO role ( nome ) VALUES ( 'ROLE_MATRIX_ADMIN' );
