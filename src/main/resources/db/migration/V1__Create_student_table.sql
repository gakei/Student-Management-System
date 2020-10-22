create table StuManageSys.student
(
	id int auto_increment,
	name varchar(50) not null,
	sex int not null comment '0是男，1是女',
	age int not null,
	grade int not null comment '1是大一，2是大二，3是大三，4是大四',
	constraint student_pk
		primary key (id)
);

