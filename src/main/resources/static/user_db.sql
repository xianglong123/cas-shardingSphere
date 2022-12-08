create database user_db;

create table t_user (
  user_id bigint(20) primary key ,
  username varchar(100) not null ,
  ustatus varchar(50) not null
);

