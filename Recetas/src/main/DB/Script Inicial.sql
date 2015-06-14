CREATE USER 'llevaYtrae'@'localhost' identified by 'gil';
GRANT SELECT, UPDATE, DELETE, INSERT ON grupo88.* TO 'llevaYtrae'@'localhost';



drop database if exists Grupo88;
Create database Grupo88;

create table Grupo88.Recetas(
	idReceta int(11) primary key auto_increment,
	nombre varchar(45) not null,
      creador  varchar(30) references Usuarios ,
    idDificultad int references dificultad
    
);

CREATE TABLE Grupo88.dificultad(
	idDificultad int auto_increment primary key,
    descripcion varchar(30) not null
);

CREATE TABLE Grupo88.relRecetCondimento(
	idReceta int(11) references Recetas(idReceta),
    idCondimento int references Condimento,
    primary key(idReceta, idCondimento)
);

CREATE TABLE Grupo88.Condimento(
	idCondimento int auto_increment primary key,
    nombre varchar(30) not null
);

CREATE TABLE Grupo88.relRecetaIngredientes(
	idReceta int(11) references Recetas,
    idIngrediente int references Ingredientes,
    cantidad int not null,
    primary key(idReceta, idIngrediente)
);

CREATE TABLE Grupo88.Ingredientes(
	idIngrediente int auto_increment primary key,
    nombre varchar(30) not null,
    caloriasPorcion int not null
);

CREATE TABLE Grupo88.Pasos(
	idReceta int(11) references Recetas,
    numeroPaso int,
    descripcion varchar(2000) not null,
    foto blob null,
    
    unique(idReceta, numeroPaso)
);

CREATE TABLE Grupo88.relUsuarioGrupo(
	nombreUsuario varchar(30) references Usuarios,
    idGrupo int references Grupos,
    primary key(nombreUsuario, idGrupo)
);

CREATE TABLE Grupo88.Grupos(
	idGrupo int auto_increment primary key,
    nombreGrupo varchar(30),
    creador varchar(30) references Usuarios
);

CREATE TABLE Grupo88.Usuarios(
	nombreUsuario varchar(30) primary key,
	clave varchar(120) not null,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    sexo char(1) not null,
    altura int not null,
    idComplexion int references Complexiones,
    idRutina int references Rutinas
);

CREATE TABLE Grupo88.Complexion(
	idComplexion int auto_increment primary key,
    complexion varchar(30) not null
);

CREATE TABLE Grupo88.relUsuarioCondicion(
	nombreUsuario varchar(30) references Usuarios,
    idCondicion int references Condiciones,
    primary key(nombreUsuario, idCondicion)
);

CREATE TABLE Grupo88.Condiciones(
	idCondicion int auto_increment primary key,
    condicion varchar(30) not null
);

CREATE TABLE Grupo88.relUsuarioDietas(
	nombreUsuario varchar(30) references Usuarios,
    idDieta int references Dietas,
    primary key (nombreUsuario, idDieta)
);

CREATE TABLE Grupo88.Dietas(
	idDieta int auto_increment primary key,
    tipoDieta varchar(30) not null
);

CREATE TABLE Grupo88.relUsuarioPreferencia(
	
    nombreUsuario varchar(30) references Usuarios,
    idPreferencia int references PreferenciasAlimenticias,
    primary key (nombreUsuario, idPreferencia)
);

CREATE TABLE Grupo88.PreferenciasAlimenticias(
	idPreferencia int auto_increment primary key,
    descripcion varchar(120) not null
);

insert into Grupo88.usuarios
values('jorge','pass','jorge','gomez','M',170,null,null),
	  ('maria','pass','maria','rodriguez','F',150,null,null);

insert into Grupo88.Recetas(nombre,creador,idDificultad)
values('Pollo al horno','jorge',null),
	  ('milanesas napolitana','maria',null),
      ('Pulpo en su tinta',null,null);
    

DELIMITER $$
USE `Grupo88` $$
CREATE PROCEDURE `ObtenerRecetas` (in nombreBuscar varchar(45))
BEGIN
    select *
    from Grupo88.recetas
    where nombre = nombreBuscar;
END $$

USE `Grupo88` $$
CREATE PROCEDURE `SP_Login`(
	in username varchar(30),
	in pass varchar(120),
    out bResp boolean
)
BEGIN
	
    IF EXISTS( SELECT 1 FROM usuarios 
				WHERE nombreUsuario = username AND
				clave = pass)
	THEN
		SET bResp = true;
	ELSE
		SET bResp = false;
	END IF;
END $$

CREATE PROCEDURE SP_CargarUsuario(
	IN username varchar(30)
)
BEGIN

	SELECT * FROM usuarios where nombreUsuario = username;
END $$
DELIMITER ;

