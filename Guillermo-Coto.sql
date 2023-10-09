drop database if exists examen1;
create database if not exists examen1;
use examen1;

create table if not exists EstadoCivil (
	Id int primary key unique auto_increment,
    Estado varchar(30) not null
);

create table if not exists Empleado (
	Id int unique auto_increment,
    Nombre varchar(50) not null,
    Direccion varchar(200) default "N/A",
    Telefono nvarchar(50) not null,
    SalarioBase decimal not null,
    IdEstadoCivil int not null,
    primary key (Id),
    constraint IdEstadoCivil_fk foreign key (IdEstadoCivil) references EstadoCivil(Id)
);

create table if not exists TipoId (
	Id int unique auto_increment,
    Tipo varchar(50) not null,
    primary key (Id)
);

create table if not exists Proveedor (
	Id int unique auto_increment,
    Nombre varchar(50) not null,
    Direccion varchar(200) default "N/A",
    Telefono nvarchar(50) not null,
    ContactoEncargado nvarchar(50) not null,
    IdTipoId int not null,
    primary key (Id),
    constraint IdTipoId_fk foreign key (IdTipoId) references TipoId(Id)
);

create table if not exists 	Departamento (
	Id int unique auto_increment,
    Nombre varchar(50) not null,
    Departamento varchar(100) not null ,
    IdEmpleado int not null,
    primary key (Id),
    constraint IdEmpleado_fk foreign key (IdEmpleado) references Empleado(Id)
);

create table if not exists EstadoSolicitud (
	Id int unique auto_increment,
    Estado nvarchar(20) not null,
    primary key (Id)
);

create table if not exists Solicitud (
	Id int unique auto_increment,
    FechaSolicitud timestamp not null,
    NombreBeneficiario varchar(200) not null,
    MontoSolicitado decimal not null,
    TipoSolicitante nchar not null,
    IdDepartamentoSolicitante int,
    IdProveedorSolicitante int,
    IdEstadoSolicitud int not null,
    primary key (Id),
    constraint IdDepartamento_fk foreign key (IdDepartamentoSolicitante) references Departamento(Id),
    constraint IdProveedor_fk foreign key (IdProveedorSolicitante) references Proveedor(Id),
    constraint IdEstado_fk foreign key (IdEstadoSolicitud) references EstadoSolicitud(Id)
);

create table if not exists TipoBeneficiario (
	Id int unique auto_increment,
    Tipo nvarchar(50) not null,
    primary key(Id)
);

create table if not exists EstadoCheque (
	Id int unique auto_increment,
    Estado nvarchar(50) not null,
    primary key(Id)
);

create table if not exists Emisor (
	Id int unique auto_increment,
    Nombre nvarchar(50) not null,
    primary key(Id)
);

create table if not exists Cheque (
	Id int unique auto_increment,
    FechaEmision timestamp not null,
    IdTipoBeneficiario int not null,
    IdEstado int not null,
    IdEmisor int not null,
    IdSolicitud int not null,
    primary key (Id),
    constraint IdTipoBeneficiario_fk foreign key (IdTipoBeneficiario) references TipoBeneficiario(Id),
    constraint IdEstadoCheque_fk foreign key (IdEstado) references EstadoCheque(Id),
    constraint IdEmisor_fk foreign key (IdEmisor) references Emisor(Id),
    constraint IdSolicitud_fk foreign key (IdSolicitud) references Solicitud (Id)
);

create table if not exists TipoCuenta (
	Id int unique auto_increment,
    TipoCuenta nvarchar(50) not null,
    primary key(Id)
);

create table if not exists EstadoCuenta (
	Id int unique auto_increment,
    Estado nvarchar(50) not null,
    primary key(Id)
);

create table if not exists Moneda (
	Id int unique auto_increment,
    Descripcion nvarchar(50) not null,
    Pais nvarchar(50) not null,
    FechaTipoCambio timestamp,
    MontoTipoCambio decimal not null,
    primary key(Id)
);

create table if not exists CatalogoCuentas (
	Id int unique auto_increment,
    Descripcion nvarchar(100) not null,
    IdTipoCuenta int not null,
    IdEstado int not null,
    IdMoneda int not null,
    IdCheque int not null,
    primary key (Id),
    constraint IdTipoCuenta_fk foreign key (IdTipoCuenta) references TipoCuenta(Id),
    constraint IdMoneda_fk foreign key (IdMoneda) references Moneda(Id),
    constraint IdEstadoCuenta_fk foreign key (IdEstado) references EstadoCuenta(Id),
    constraint IdCheque_fk foreign key (IdCheque) references Cheque(Id)
);

insert into EstadoCivil (Estado) values ("Casado");
insert into EstadoCivil (Estado) values ("Soltero");
insert into EstadoCivil (Estado) values ("Viudo");
insert into EstadoCivil (Estado) values ("Union Libre");

insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Pedro","El Bosque",555222,500000,1);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Alfonso","El Bosque",555222,500000,2);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Lucas","El Bosque",555222,500000,3);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Adam","El Bosque",555222,500000,4);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Bryan","El Bosque",555222,600000,2);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Leon","El Bosque",555222,600000,1);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Marco","La Rueda",555222,400000,4);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Nicolas","La Rueda",555222,400000,2);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Gerardo","Sinclair",555222,400000,1);
insert into Empleado (Nombre, Direccion, Telefono, SalarioBase, IdEstadoCivil) values ("Arnulfo","Sinclair",555222,400000,1);


insert into Departamento (Nombre, Departamento, IdEmpleado) values ("CompraEquipo","Encargado de compras de equipo",4);

