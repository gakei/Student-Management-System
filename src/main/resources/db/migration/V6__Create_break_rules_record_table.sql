create table break_rules_record
(
	id int auto_increment,
	type int not null comment '违规类别',
	stu_name varchar(50) not null comment '违规学生姓名',
	constraint break_rules_record_pk
		primary key (id)
);

create unique index break_rules_record_stu_name_uindex
	on break_rules_record (stu_name);

