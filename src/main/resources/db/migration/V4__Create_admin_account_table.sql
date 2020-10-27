create table admin_account
(
	id int auto_increment,
	username varchar(50) not null,
	password varchar(50) not null,
	constraint admin_account_pk
		primary key (id)
);

create unique index admin_account_username_uindex
	on admin_account (username);

