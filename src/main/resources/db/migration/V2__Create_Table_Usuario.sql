create table usuario (
   id bigint not null auto_increment,
   
    username varchar(20) not null,
    password varchar(100) not null,

    nome varchar(100) not null,
    apelido varchar(20) not null,
    email varchar(100) not null,
    
    enabled bit not null,
    
    created_by varchar(255),
    created_date datetime(6),
    last_modified_by varchar(255),
    last_modified_date datetime(6),
    
    primary key (id)
) engine=InnoDB;

alter table usuario 
add constraint UK_5171l57faosmj8myawaucatdw unique (email);


alter table usuario 
add constraint UK_863n1y3x0jalatoir4325ehal unique (username);


INSERT INTO usuario ( username, PASSWORD, nome, apelido, email, enabled ) 
VALUES ( 'Miro', '$2a$16$88GMSfA5Wr.zSmcQSkNAO.StT8KvUE0wi49BH3i.ZJ.2QI/nk2n5W', 'Claudemiro Júnior', 'Miro', 'volverinejr@gmail.com', 1 );





