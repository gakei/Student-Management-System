create table dormitory
(
	id int auto_increment,
	dormitory_no varchar(50) not null comment '寝室号',
	dormitory_mem_num int not null comment '寝室人数',
	constraint dormitory_pk
		primary key (id)
);

create unique index dormitory_dormitory_no_uindex
	on dormitory (dormitory_no);