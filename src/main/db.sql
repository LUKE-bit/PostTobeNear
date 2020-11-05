create database Post;
use Post;

drop table if exists orders;
create table orders (
    orderId int primary key auto_increment,
    type varchar(10),
    money int,
    time datetime,
    description varchar(100),
    isTake int,
    postId int,
    takeId int
);

drop table if exists User;
create table User (
    userId int primary key auto_increment,
    account varchar(50) unique,
    password varchar(50)
);



