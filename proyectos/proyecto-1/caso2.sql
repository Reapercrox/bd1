drop database if exists caso2;
create database if not exists caso2;
use caso2;

create table if not exists PERSONA(
idPersona int primary key unique auto_increment,
cedula varchar (20) ,
nombre varchar (50),
tipo varchar (20)
);

create table if not exists PersonaFisica(
idPersonaFisica int primary key unique auto_increment,
fechaNacimiento Datetime ,
idPersona int,
  foreign key (idPersona) references PERSONA(idPersona)
);

create table if not exists PersonaJurididca(
idPersonaJuridica int primary key unique auto_increment,
nombreComercial varchar(50) ,
idPersona int,
 foreign key (idPersona) references PERSONA(idPersona)
);

create table if not exists CuentaCobrar(
idCuentaCobrar int primary key unique auto_increment,
monto decimal (18,2),
numero varchar(20),
fechaVencimiento Datetime, 
idPersona int,
  foreign key  (idPersona) references PERSONA(idPersona)

) ;

create table if not exists MetodoPago(
idMetodoPago  int primary key unique auto_increment,
codigo varchar (10) ,
nombre varchar (20)

);


create table if not exists Abono(
idAbono  int primary key unique auto_increment,
monto decimal (18,2),
numeroCuota int ,
cuentaCobrar int ,
metodoPago int ,
  foreign key (cuentaCobrar) references CuentaCobrar(idCuentaCobrar),
    foreign key (metodoPago) references MetodoPago(idMetodoPago)
);


insert into PERSONA(cedula,nombre,tipo) values ('307890526','Guillermo Coto','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('304010808','Rita Alvarez','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('305220967','Joseth Coto','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('105960325','Daniela Navarro','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('107840123','Katherine Fuentes','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('306980147','Juan Martinez','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('117950426','Alonso Vidal','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('117590462','Carmen Alvarez','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('119970336','Jenny Gomez','Fisica');
insert into PERSONA(cedula,nombre,tipo) values ('301110785','Marcelo Bonilla','Fisica');

insert into PersonaFisica (fechaNacimiento,idPersona) values ('1998-09-18',1) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('1979-10-10',2) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('2000-02-17',3) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('2001-06-22',4) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('1999-02-05',5) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('1989-05-10',6) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('2010-12-15',7) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('1979-10-11',8) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('1994-03-08',9) ;
insert into PersonaFisica (fechaNacimiento,idPersona) values ('2015-08-30',10) ;


insert into PersonaJurididca (nombreComercial,idPersona) values ('Coca Cola Company', 2);
insert into PersonaJurididca (nombreComercial,idPersona) values ('DepositoLaAurora', 3 );
insert into PersonaJurididca (nombreComercial,idPersona) values ('Epa', 4);
insert into PersonaJurididca (nombreComercial,idPersona) values ('Burger King', 5);
insert into PersonaJurididca (nombreComercial,idPersona) values ('Trova', 6);
insert into PersonaJurididca (nombreComercial,idPersona) values ('Servicentro El Guarco', 7);
insert into PersonaJurididca (nombreComercial,idPersona) values ('Subway', 8);
insert into PersonaJurididca (nombreComercial,idPersona) values ('El Studio', 9);
insert into PersonaJurididca (nombreComercial,idPersona) values ('Cinemax', 10);
insert into PersonaJurididca (nombreComercial,idPersona) values ('People Fitness', 11);


insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (2000.10,'123','2023-10-11',1);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (35200,'100','2023-10-12',2);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (15500,'101','2023-10-13',3);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (2010.3,'102','2023-10-14',4);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (963521.5,'103','2023-10-15',5);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (741235,'104','2023-10-20',6);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (1200,'105','2023-10-21',7);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (6352,'106','2023-10-11',8);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (17500.10,'107','2023-10-27',9);
insert into CuentaCobrar (monto,numero,fechaVencimiento,idPersona) values (77000,'108','2023-11-11',10);


insert into MetodoPago (codigo,nombre) values ('TAR','Tarjeta');
insert into MetodoPago (codigo,nombre) values ('Pay','Paypal');
insert into MetodoPago (codigo,nombre) values ('SINP','SINPE MOVIL');
insert into MetodoPago (codigo,nombre) values ('LT','Letra de cambio');
insert into MetodoPago (codigo,nombre) values ('TFR','Transferencia');
insert into MetodoPago (codigo,nombre) values ('TC','Tarjeta de comercio');
insert into MetodoPago (codigo,nombre) values ('EFECT','Efectivo');
insert into MetodoPago (codigo,nombre) values ('TD','Tarjeta de debito');
insert into MetodoPago (codigo,nombre) values ('TDC','Tarjeta de credito');
insert into MetodoPago (codigo,nombre) values ('CH','Cheques');



insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (1000.05,1,1,1);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (4521,2,2,2);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (52147,3,3,3);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (1147,4,4,4);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (78965.15,5,5,5);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (369.25,6,6,6);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (914.45,7,7,7);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (11111,8,8,8);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (65899,9,9,9);
insert into Abono (monto,numeroCuota,cuentaCobrar,metodoPago) values (140.50,10,10,10);

