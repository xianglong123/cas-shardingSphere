create database edu_db_2;

create table edu_db_2.course_1 (
  cid bigint(20) primary key ,
  cname varchar(50) not null ,
  user_id bigint(20) not null,
  cstatus varchar(10) not null
);

create table edu_db_2.course_2 (
                          cid bigint(20) primary key ,
                          cname varchar(50) not null ,
                          user_id bigint(20) not null,
                          cstatus varchar(10) not null
);