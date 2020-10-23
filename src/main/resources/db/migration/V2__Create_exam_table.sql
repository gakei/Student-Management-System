create table examination
(
	id int auto_increment,
	exam_time Date null comment '考试时间',
	exam_name varchar(50) not null comment '考试名称',
	charge_teacher varchar(50) not null comment '监考老师',
	exam_place varchar(50) not null comment '考试地点',
	constraint examination_pk
		primary key (id)
);

