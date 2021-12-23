USER tp3


CREATE TABLE IF NOT EXISTS role
(
	idRole int auto_increment,
	tagRole varchar(255) not null,
	labelRole varchar(255) not null,
	constraint role_pk
		primary key (idRole)
);

CREATE TABLE IF NOT EXISTS user (
	idUser int auto_increment,
	login varchar(255) not null,
	password varchar(255) not null,
	salt varchar(255) not null,
	role int not null,
	constraint user_pk
		primary key (idUser),
	constraint user_role_idRole_fk
		foreign key (role) references role (idRole)
);

create table SecurityData
(
	idSecurity int auto_increment,
	tagSecurity varchar(255) null,
	status boolean default false null,
	constraint SecurityData_pk
		primary key (idSecurity)
);