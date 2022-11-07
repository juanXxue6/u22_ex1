create database ud22_1;
use ud22_1;
create table cliente (
id int(11) not null auto_increment,
nombre varchar(250) default null,
apellido varchar(250) default null,
direccion varchar(250) default null,
dni int(11) default null,
fecha date default null,
primary key(id)
);