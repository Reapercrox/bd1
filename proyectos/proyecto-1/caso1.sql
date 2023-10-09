drop database if exists caso1;
create database if not exists caso1;
use caso1;

create table if not exists SegmentoCliente (
	Id int primary key unique auto_increment,
    NombreGerente varchar(50) not null,
    Descripcion varchar(200) default "N/A",
    CodigoSegmento nvarchar(50) not null,
    UltimaVisitaDoctor timestamp not null
);

create table if not exists CompaniaxSegmento(
	IdCompania int not null,
    IdSegmento int not null,
    primary key (IdCompania, IdSegmento),
    constraint IdSegmentoCompania_fk foreign key (IdSegmento) references SegmentoCliente(Id)
);

create table if not exists Compania (
	Id int unique auto_increment,
    Nombre varchar(50) not null,
    Direccion varchar(200) default "N/A",
    CodigoCompania nvarchar(50) not null,
    NumeroPatronal int not null,
    NombreGerente nvarchar(50) not null,
    CedulaGerente int not null,
    NombrePresidente nvarchar(50) not null,
    IdSegmentoCliente int not null,
    primary key (Id),
    constraint IdCompaniaSegmento_fk foreign key (IdSegmentoCliente) references CompaniaxSegmento(IdCompania)
);

create table if not exists TipoProveedor (
	Id int unique auto_increment,
    Tipo varchar(50) not null,
    primary key (Id)
);

create table if not exists Proveedor (
	Id int unique auto_increment,
    Nombre varchar(50) not null,
    Cedula int not null,
    CodigoProveedor int not null,
    Direccion varchar(200) default "N/A",
    Telefono nvarchar(50) not null,
    NombreContacto nvarchar(50) not null,
    IdTipoProveedor int not null,
    IdCompania int not null,
    primary key (Id),
    constraint IdTipoProveedor_fk foreign key (IdTipoProveedor) references TipoProveedor(Id),
    constraint IdCompania_fk foreign key (IdCompania) references Compania(Id)
);

create table if not exists 	OrdenesPedido (
	Id int unique auto_increment,
    NumeroOrden int not null,
    UsuarioComprador nvarchar(50) not null,
    UsuarioAprobador nvarchar(50) not null,
    MontoTotalOrden int not null,
    IdProveedor int not null,
    IdCompania int not null,
    primary key (Id),
    constraint IdProveedor_fk foreign key (IdProveedor) references Proveedor(Id),
    constraint IdCompaniaOrden_fk foreign key (IdCompania) references Compania(Id)
);

create table if not exists TipoArticulo (
	Id int unique auto_increment,
    Tipo varchar(50) not null,
    primary key (Id)
);

create table if not exists Articulo (
	Id int unique auto_increment,
    CodigoArticulo int not null,
    Descripcion varchar(200) not null,
    PrecioUnitario decimal not null,
    Stock int not null,
    FechaUltimaCompra timestamp not null,
    FechaUltimoInventario timestamp not null,
    IdTipoArticulo int not null,
    primary key (Id),
    constraint IdTipoArticulo_fk foreign key (IdTipoArticulo) references TipoArticulo(Id)
);

create table if not exists PrecioVentaArticulo (
	Id int unique auto_increment,
    IdArticulo int not null,
    Precio int not null,
    primary key (Id),
    constraint IdPrecioArticuo_fk foreign key (IdArticulo) references TipoArticulo(Id)
);


create table if not exists DetalleOrden (
	Id int unique auto_increment,
    IdOrden int not null,
    IdArticulo int not null,
    Cantidad int not null,
    PrecioNegociado int not null,
    TotalLinea int not null,
    NumeroLinea int not null,
    primary key (Id),
    constraint IdOrden_fk foreign key (IdOrden) references OrdenesPedido(Id),
    constraint IdArticulo_fk foreign key (IdArticulo) references Articulo(Id)
);