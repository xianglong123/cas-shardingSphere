create database user_db;

create table t_user (
  user_id bigint(20) primary key ,
  username varchar(100) not null ,
  ustatus varchar(50) not null
);

create table if not exists user_db.t_log_20221207
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

create table if not exists user_db.t_log_20221208
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

create table if not exists user_db.t_log_20221209
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

-- ---------------------
create table if not exists user_db.t_log_202211
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

create table if not exists user_db.t_log_202212
(
	log_id bigint not null
		primary key,
	request_time datetime null,
	msg varchar(50) null
);

