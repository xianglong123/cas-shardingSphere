create database course_db;

create table course_db.course_1 (
  cid bigint(20) primary key ,
  cname varchar(50) not null ,
  user_id bigint(20) not null,
  cstatus varchar(10) not null
);

create table course_db.course_2 (
                          cid bigint(20) primary key ,
                          cname varchar(50) not null ,
                          user_id bigint(20) not null,
                          cstatus varchar(10) not null
);