--drop table if exists accounts;
--drop table if exists users;
--drop table if exists questions;
--drop table if exists answers;

create table if not exists accounts(
    id int not null auto_increment primary key,
    username VARCHAR(30),
    password VARCHAR(30)
) engine=InnoDB;

create table if not exists users(
    id int not null auto_increment primary key,
    account_id int not null,
    nickname VARCHAR(30),
    url VARCHAR(100)
) engine=InnoDB;

create table if not exists questions(
    id int auto_increment primary key,
    creator_id int not null,
    title VARCHAR(30),
    description VARCHAR(255),
    create_time date,
    answer_count int default 0,
    view_count int default 0,
    like_count int default 0
) engine=InnoDB;

create table if not exists answers(
    id int auto_increment primary key,
    question_id int not null,
    answerer_id int not null,
    description text,
    answer_time date,
    support_count int default 0
) engine=InnoDB;

