create database user_db;

create table user_db.t_user (
  user_id bigint(20) primary key ,
  username varchar(100) not null ,
  ustatus varchar(50) not null
);

create table if not exists user_db.t_log_20230907
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

create table if not exists user_db.t_log_20230908
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

create table if not exists user_db.t_log_20230909
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

-- ---------------------
create table if not exists user_db.t_log_202308
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

create table if not exists user_db.t_log_202309
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

