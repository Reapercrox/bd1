drop database if exists blockbuster;
create database if not exists blockbuster;
use blockbuster;

create table if not exists cliente (
	id int primary key unique auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    direccion varchar(200) default "N/A",
    celular nvarchar(50) not null
);

create table if not exists categoria (
	id int primary key unique auto_increment,
    nombreCategoria varchar(50) not null
);

create table if not exists estado (
	id int primary key unique auto_increment,
    estado varchar(30) not null
);

create table if not exists pelicula (
	id int primary key unique auto_increment,
    titulo varchar(50) not null,
    fechaSalida date,
    categoriaPelicula int not null,
    constraint categoria_fk foreign key (categoriaPelicula) references categoria(id) 
);

create table if not exists prestamo (
	id int unique auto_increment,
    fechaPrestamo timestamp not null,
    fechaDevolucion timestamp not null,
    idCliente int not null,
    idEstado int not null,
    primary key (id,idCliente),
    constraint idCliente_fk foreign key (idCliente) references cliente(id),
    constraint idEstado_fk foreign key (idEstado) references estado(id)
);
-- De esta manera se pueden rentar más de una película con un solo registro de prestamo y no solo una
create table if not exists prestamoPelicula (
	idPrestamo int not null,
    idPelicula int not null,
    primary key (idPrestamo,idPelicula),
    constraint idPrestamo_fk foreign key (idPrestamo) references prestamo(id),
    constraint idPelicula_fk foreign key (idPelicula) references pelicula(id)
);

-- Inserts
insert into cliente (nombre, apellido, direccion, celular) values ("Pedro","Sanchez","El Bosque",555222);
insert into cliente (nombre, apellido, direccion, celular) values ("Alfonso","Sanchez","El Bosque",555222);
insert into cliente (nombre, apellido, direccion, celular) values ("Lucas","Sanchez","El Bosque",555222);
insert into cliente (nombre, apellido, direccion, celular) values ("Pedro","Vargas","El Bosque",555222);
insert into cliente (nombre, apellido, direccion, celular) values ("Alfonso","Vargas","El Bosque",555222);
insert into cliente (nombre, apellido, direccion, celular) values ("Lucas","Vargas","El Bosque",555222);
insert into cliente (nombre, apellido, celular) values ("Pedro","Fernandez",555222);
insert into cliente (nombre, apellido, celular) values ("Pedro","Pascal",555222);
insert into cliente (nombre, apellido, celular) values ("Pedro","Sinclair",555222);
insert into cliente (nombre, apellido, celular) values ("Pedro","Maroto",555222);

insert into categoria(nombreCategoria) values ('Comedia');
insert into categoria(nombreCategoria) values ('Drama');
insert into categoria(nombreCategoria) values ('Sci-Fi');
insert into categoria(nombreCategoria) values ('Accion');
insert into categoria(nombreCategoria) values ('Documental');
insert into categoria(nombreCategoria) values ('Romance');
insert into categoria(nombreCategoria) values ('Terror');
insert into categoria(nombreCategoria) values ('Musical');
insert into categoria(nombreCategoria) values ('Suspenso');
insert into categoria(nombreCategoria) values ('Animacion');

insert into pelicula(titulo, fechaSalida, categoriaPelicula) values ('Ace Ventura', '1998-11-11 12:00:00', 1);
insert into pelicula(titulo, fechaSalida, categoriaPelicula) values ('Oppenheimer', '2023-08-11 12:00:00', 2);
insert into pelicula(titulo, fechaSalida, categoriaPelicula) values ('The Matrix', '1999-11-11 12:00:00', 3);
insert into pelicula(titulo, fechaSalida, categoriaPelicula) values ('Paranormal Activity', '2005-11-11 12:00:00', 7);

insert into estado(estado) values ('Activo');
insert into estado(estado) values ('Inactivo');
insert into estado(estado) values ('Atrasado');

insert into prestamo(fechaPrestamo,fechaDevolucion,idCliente,idEstado) values ('2023-09-24 09:00:00', date_add(fechaPrestamo, interval 3 day), 1, 1);
insert into prestamo(fechaPrestamo,fechaDevolucion,idCliente,idEstado) values ('2023-09-22 09:00:00', date_add(fechaPrestamo, interval 3 day), 3, 1);
insert into prestamo(fechaPrestamo,fechaDevolucion,idCliente,idEstado) values ('2023-09-20 09:00:00', date_add(fechaPrestamo, interval 3 day), 5, 3);
insert into prestamo(fechaPrestamo,fechaDevolucion,idCliente,idEstado) values ('2023-09-19 09:00:00', date_add(fechaPrestamo, interval 3 day), 7, 2);

insert into prestamoPelicula(idPrestamo,idPelicula) values (1,3);
insert into prestamoPelicula(idPrestamo,idPelicula) values (1,2);
insert into prestamoPelicula(idPrestamo,idPelicula) values (2,1);
insert into prestamoPelicula(idPrestamo,idPelicula) values (3,4);
insert into prestamoPelicula(idPrestamo,idPelicula) values (4,1);

-- Select para probar la funcionalidad entre las diferentes peliculas
select 
	p.fechaPrestamo, p.fechaDevolucion ,e.Estado ,c.nombre, c.apellido ,pe.titulo
from
	prestamo p
inner join
	estado e on p.idEstado = e.id
inner join
	cliente c on p.idCliente = c.id
inner join
	prestamoPelicula pp on p.id = pp.idPrestamo
inner join
	pelicula pe on pp.idPelicula = pe.id;
    
-- Mostrar la Cédula, Nombre, Apellido y Teléfono Celular de los clientes que han pedido prestada una película al video
select
	c.id as cedula, c.nombre, c.apellido, c.celular
from
	cliente c
inner join
	prestamo p on p.idCliente = c.id;
    
-- Mostrar la cantidad de préstamos activos por cédula.
select
	 c.id as cedula, c.nombre, p.fechaPrestamo, p.fechaDevolucion, e.estado
from
	prestamo p
inner join
	cliente c on p.idCliente = c.id
inner join
	estado e on p.idEstado = e.id
where
	e.estado = 'Activo';
    
-- Mostrar la cantidad de préstamos inactivos existentes por cédula.
select
	c.id as cedula, c.nombre, c.apellido, c.celular
from
	cliente c
inner join
	prestamo p on p.idCliente = c.id;
    
-- Mostrar la cantidad de préstamos activos por cédula.
select
	 c.id as cedula, c.nombre, p.fechaPrestamo, p.fechaDevolucion, e.estado
from
	prestamo p
inner join
	cliente c on p.idCliente = c.id
inner join
	estado e on p.idEstado = e.id
where
	e.estado = 'Inactivo';
    
-- Mostrar el total de préstamos inactivos existentes.
select
	c.nombre, p.fechaPrestamo, p.fechaDevolucion, e.estado
from
	prestamo p
inner join
	cliente c on p.idCliente = c.id
inner join
	estado e on p.idEstado = e.id
where
	e.estado = 'Inactivo';
    
-- Mostrar a todos aquellos clientes que nunca han realizado un préstamo.
select
	c.nombre, c.apellido
from
	cliente c
where
	c.id not in (select distinct p.idCliente from prestamo p);
    
-- Actualizar el campo Dirección y poner Guanacaste, del cliente con cédula 10.
update cliente c
set
	c.direccion  = 'Guanacaste'
where
	c.id = 10;

-- Mostrar la cédula, nombre, apellido de los clientes que tienen entre 1 y 3 préstamos activos.
select
	 c.nombre, p.fechaPrestamo, p.fechaDevolucion, e.estado
from
	prestamo p
inner join
	cliente c on p.idCliente = c.id
inner join
	estado e on p.idEstado = e.id
where
	e.estado = 'Activo';
    
-- Listar todas las películas de la categoría “Comedia”.
select
	pe.titulo
from
	pelicula pe
inner join
	categoria ca on pe.categoriaPelicula = ca.id
where
	ca.nombreCategoria = 'Comedia';
    
--  Listar cuántas películas existen por categoría, por ejemplo: 3 de Comedia, 2 de Drama, 4 de Ciencia Ficción, etc.
select
	count(ca.nombreCategoria), count(pe.titulo)
from
	pelicula pe
inner join
	categoria ca on pe.categoriaPelicula = ca.id
    
    