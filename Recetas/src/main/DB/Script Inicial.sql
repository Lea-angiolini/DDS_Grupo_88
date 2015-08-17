drop database if exists Grupo88;
Create database Grupo88 character set utf8 collate utf8_bin;

DROP USER 'llevaYtrae'@'localhost';

CREATE USER 'llevaYtrae'@'localhost' identified by 'gil';
GRANT SELECT, UPDATE, DELETE, INSERT, EXECUTE ON grupo88.* TO 'llevaYtrae'@'localhost';


create table Grupo88.Recetas(
	idReceta int primary key auto_increment,
	nombre varchar(45) not null,
	creador  varchar(30) references Usuarios,
    descripcion varchar(200),
    idDificultad int references dificultad,
    calorias int not null,
    grupoAlimenticio int references grupoalim,
    temporada int references temporadas,
    ingredientePrincipal int references ingredientes,
    puntajeTotal int default 0,
    vecesCalificada int default 0
    
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

CREATE TABLE Grupo88.relTipoIngGrupoAlim(
	idTipoIngrediente int references idTipoIngrediente,
	idGrupoAlim int references idGrupoAlim,
    primary key (idTipoIngrediente, idGrupoAlim)
);

CREATE TABLE Grupo88.tipoIngrediente(
	idTipoIngrediente int auto_increment primary key,
    descripcion varchar(30) not null
);  

CREATE TABLE grupo88.GrupoAlim(
	idGrupoAlim int auto_increment primary key,
    descripcion varchar (50) not null
);

CREATE TABLE Grupo88.Ingredientes(
	idIngrediente int auto_increment primary key,
    nombre varchar(30) not null,
    caloriasPorcion int not null,
    tipoIngrediente int not null
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
    nombreGrupo varchar(30) unique,
    detalle varchar(255) not null,
    creador varchar(30) references Usuarios
);

CREATE TABLE Grupo88.Usuarios(
	nombreUsuario varchar(30) primary key,
	clave varchar(120) not null,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    mail varchar(60) not null,
    fechaNac date,
    sexo char(1) not null,
    altura int not null,
    idComplexion int references Complexiones,
    idDieta int references dietas,
    idRutina int references Rutinas
);

CREATE TABLE Grupo88.Rutinas(
	idRutina int auto_increment primary key,
    rutina varchar(45) not null
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

CREATE TABLE Grupo88.Historial(
	idHistorial int auto_increment primary key,
    fecha datetime not null,
    idReceta int not null,
    usuario varchar(30) references usuarios,
    -- cantVecesUsada int
    calificacionUsuario int default 0
    -- foreign key (usuario)
    -- references Grupo88.usuarios(nombreUsuario)
);

CREATE TABLE Grupo88.Temporadas(
	idTemporada int auto_increment primary key,
    nombreTemporada varchar(50) not null
);

CREATE TABLE Grupo88.amigos(
	nombreUsuario1 int references Usuarios,
    nombreUsuario2 int references Usuarios,
    PRIMARY KEY(nombreUsuario1,nombreUsuario2),
    check(nombreUsuario1 <> nombreUsuario2)
);

CREATE TABLE Grupo88.historicoConsultas(
	idHistorico int auto_increment PRIMARY KEY,
	idReceta int references recetas,
    username varchar(30) references Usuarios,
    fecha date
);

CREATE TABLE Grupo88.relgruporeceta(
	idGrupo int references grupos,
	idReceta int references Recetas,
	PRIMARY KEY(idGrupo,idReceta)
);

insert into Grupo88.Rutinas (rutina)
values ('Sedentaria con algo de ejercicio'),
	   ('Sedentaria con nada de ejercicio'),
       ('Sedentaria con ejercicio'),
       ('Activa con ejercicio'),
       ('Activa sin ejercicio');

insert into Grupo88.dificultad(descripcion)
values('Facil'),
	  ('Media'),
	  ('Dificil');
      
insert into grupo88.condiciones(condicion)
values('Diabetes'),('Hipertencion'),('Celiasis');
      
insert into grupo88.complexion(complexion)
values('Pequeña') ,
	  ('Mediana'),
      ('Grande');
 
insert into Grupo88.relRecetaIngredientes (idReceta,idIngrediente,cantidad)
values (1,31,1),(1,21,1),
	   (3,34,1),(3,13,5),
       (4,3,2),(4,29,2);

insert into grupo88.condimento (nombre)
values ('Sal'),('Pimienta Negra'),('Paprika'),('Oregano'),('Laurel'),('Aji Molido'),('Azafran');
      
insert into grupo88.tipoingrediente(descripcion)
values ('Cereales y derivados'),('Legumbres'),('Huevos'),('Azucares y dulces'),('Verduras y Hortalizas'),
	   ('Frutas'),('Frutos Secos'),('Lacteos y derivados'),('Carnes'), ('Pescados y Mariscos'), 
       ('Aceites y grasas'), ('Otros');

insert into grupo88.GrupoAlim (descripcion)
values ('Grupo 1: Leche y Derivados'), 
	   ('Grupo 2: Carnes, Huevos y Pescado'), 
       ('Grupo 3: Papas, Legumbres, Frutos Secos'),
       ('Grupo 4: Verduras y Hortalizas'),
       ('Grupo 5: Frutas'),
       ('Grupo 6: Harinas, Cereales, Dulces'),
       ('Grupo 7: Grasas, Aceites y Mantequilla');

insert into Grupo88.usuarios
values('jorge','pass','Jorge','Gomez','',null,'M',170,3,2,1),
	  ('maria','pass','Maria','Rodriguez','',null,'F',150,1,4,2),
      ('carlos', 'pass', 'Carlos', 'Batata','@gmail.com',null,'M', 160, 2, 1, 3);

insert into Grupo88.Recetas(nombre,creador,idDificultad,calorias,grupoAlimenticio,temporada,ingredientePrincipal)
values('Pollo al horno','jorge',2,1000,2,1,31),
	  ('milanesas napolitana','maria',1,800,2,3,34),
      ('Pulpo en su tinta',null,3,1200,2,5,35),
      ('Ñoquis','maria',2,760,6,4,3),
      ('Pan con queso',null,1,1000,1,1,29),
      ('Pizza','jorge',1,1150,6,2,3),
      ('Asado','jorge',2,940,2,2,32);
    

insert into Grupo88.Historial(fecha,idReceta,usuario)
values ('2013-08-27',3,'jorge'),
	   ('2013-08-29', 1, 'jorge');

insert into Grupo88.Temporadas(NombreTemporada)
values('Verano'),('Otoño'),('Invierno'),('Primavera'),('Navidad'),('Pascua'),('Fechas de Final');

insert into Grupo88.ingredientes(nombre,caloriasPorcion, tipoIngrediente)
values ('Arroz',354,1), ('Avena',367,1),('Harina',150, 1), -- cereales y derivados
	   ('Lentejas',336,2),('Poroto',316,2), -- legumbres
       ('Clara de Huevo', 48, 3),('Yema de Huevo',368, 3),('Huevo Duro', 147, 3), -- huevos
       ('Cacao', 366, 4),('Chocolate', 550, 4),('Miel',300, 4), -- azucares y dulces
       ('Aceitunas Verdes',132, 5),('Ajo',169,5),('Apio', 20,5),('Arvejas',78,5),('Alcachofa',64,5),('Acelga', 33,5),
	   ('Repollo',19,5),('Zanahoria',42,5), -- verduras y hortalizas
       ('Ciruela',44,6), ('Limon',39,6),('Manzana',52,6),('Pomelo',30,6), ('Banana',90,6), -- frutas
       ('Almendras',620,7),('Avellanas',675,7),('Mani',560,7), -- frutos secos
       ('Leche',68,8),('Queso cremoso',245,8),('Yogurt Natural',62,8), -- lacteos
       ('Pollo',750, 9),('Vacio',1200, 9), ('Jamon Cocido',126,9), ('Milanesa',690,9), -- carnes
	   ('Pulpo',57, 10),('Anchoas',175,10),('Sardinas',151,10), -- pescados y mariscos
       ('Manteca',670,11 ), ('Aceite de girasol',900,11) -- aceites y grasas
       ;
       
insert into Grupo88.dietas(tipoDieta)
values ('Normal'),('Ovolactovegetariano'),('Vegetariano'),('Vegano');

insert into  Grupo88.pasos(idReceta,numeroPaso,descripcion)
values (1,1,'Descongelar el pollo'),(1,2,'Sacarle lo que no sirve'),
		(1,3,'Mandarlo al horno'),(1,4,'Agregar papa si se quiere'),
        (1,5,'Disfrutar del pollo');
        
 insert into Grupo88.grupos(nombreGrupo,creador,detalle)
 values('Locomia','jorge','Aca se come de todo'),
		('Putos','carlos','cualquier cosa ponele');
        
insert into Grupo88.relusuariocondicion()
values ('jorge',1),('jorge',3);

insert into Grupo88.relusuariogrupo()
values ('maria',1),('carlos',1),('carlos',2);

insert into Grupo88.relgruporeceta()
values (1,1);

DELIMITER $$
USE `Grupo88` $$


CREATE PROCEDURE raise_error(
    in mensaje varchar(500)
)
BEGIN

	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = mensaje;
END $$




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
	SET bResp = false;
    
    SELECT true
    into bResp
    FROM usuarios 
	WHERE nombreUsuario like username AND
		  clave like pass;
END $$

CREATE PROCEDURE SP_CargarUsuario(
	IN username varchar(30)
)
BEGIN

	SELECT us.*, com.complexion, rut.rutina, die.tipoDieta FROM usuarios us
    JOIN complexion com 
		ON us.idComplexion = com.idComplexion
    JOIN rutinas rut
		ON rut.idRutina = us.idRutina
	JOIN dietas die
		ON us.idDieta = die.idDieta
    where nombreUsuario = username;
END $$


CREATE PROCEDURE SP_CargarCondPreexUsuario(
	IN username varchar(30)
)
BEGIN

	SELECT cond.idCondicion, cond.condicion
    FROM relusuariocondicion rel
    JOIN condiciones cond
    ON cond.idCondicion = rel.idCondicion
    WHERE rel.nombreUsuario = username;
    
END $$


CREATE PROCEDURE SP_RecetasPopulares(
)
BEGIN

	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion 
    FROM recetas rec 
    JOIN dificultad dif
    ON rec.idDificultad = dif.idDificultad
    LIMIT 10;
END $$

CREATE PROCEDURE SP_HistorialUsuario(
in username varchar(30) 
)
BEGIN

	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion 
    FROM recetas rec 
    JOIN dificultad dif
    ON rec.idDificultad = dif.idDificultad
    JOIN Historial his
    ON his.usuario = username
    WHERE rec.idReceta = his.idReceta
    LIMIT 10;
END $$

CREATE PROCEDURE SP_RecetasUsuario(
in username varchar(30) 
)
BEGIN

	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion, dif.idDificultad
    FROM recetas rec 
    JOIN dificultad dif
    ON rec.idDificultad = dif.idDificultad
    WHERE rec.creador = username;
END $$

CREATE PROCEDURE SP_BuscarRecetas(
in dificultadB int,
in temporadaB int,
in ingredienteB int,
in grupoAlim int,
in calificacion int,
in caloriasMax int,
in caloriasMin int
)
BEGIN
	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad
	FROM Grupo88.recetas rec
    JOIN Grupo88.dificultad dif
    ON dif.idDificultad = rec.idDificultad
	WHERE (dificultadB = -1 or rec.idDificultad = dificultadB) AND
		  (temporadaB = -1 or rec.temporada = temporadaB) AND
          (ingredienteB = -1 or rec.ingredientePrincipal = ingredienteB) AND
          (grupoAlim = -1 or rec.grupoAlimenticio = grupoAlim) AND
          rec.calorias < caloriasMax AND
          rec.calorias > caloriasMin;
END $$


CREATE PROCEDURE SP_RegistrarUsuario(
in username_ varchar(30),
in pass_ varchar(30),
in mail_ varchar(30),
in nombre_ varchar(30),
in apellido_ varchar(30),
in fechaNac_ date,
in sexo_ varchar(1),
in altura_ int,
in idComplexion int,
in idDieta int,
in IdRutina int,
out respuesta varchar(90)
)
BEGIN
	
	declare rutinaID int;
    
    
	DECLARE exit HANDLER FOR SQLSTATE '23000' call raise_error('El usuario ingresado ya existe');

		INSERT INTO usuarios(nombreUsuario,clave,mail,nombre,apellido,sexo,fechaNac,altura,idComplexion,idDieta,idRutina)
			VALUES(username_,pass_,mail_,nombre_,apellido_,sexo_,fechaNac_,altura_,idComplexion ,idDieta,idRutina);
END $$

CREATE PROCEDURE SP_modificarPerfil(
in username_ varchar(30),
in pass_ varchar(30),
in nombre_ varchar(30),
in apellido_ varchar(30),
in mail_ varchar(30),
in fechaNac_ date,
in sexo_ varchar(1),
in altura_ int,
in idComplexion int,
in idDieta int,
in IdRutina int

)
BEGIN

	UPDATE usuarios usu
	SET usu.clave=pass_, usu.nombre= nombre_, usu.apellido = apellido_, usu.mail=mail_, usu.fechaNac = fechaNac_, usu.sexo = sexo_, 
		usu.altura=altura_,	usu.idComplexion = idComplexion, usu.idDieta = idDieta, usu.idRutina = idRutina
    WHERE usu.nombreUsuario=username_;
    
    
END$$



CREATE PROCEDURE SP_RegistrarCondPreexUsuario(
in username varchar(30),
in idCondPreex int
)
BEGIN
	 INSERT INTO relusuariocondicion
		VALUES (username, idCondPreex);
END $$

CREATE PROCEDURE SP_ObtenerReceta(
	IN idReceta int,
    IN username varchar(30)
)
BEGIN
	declare calificacion int;
    
    set calificacion = (select his.calificacionUsuario from Grupo88.historial his
    where his.idReceta = idReceta
    and	his.usuario = username
	order by his.fecha desc
    limit 1);

	 SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad, grupoalim.descripcion as 'grpAlim',
			tem.nombreTemporada, tem.idTemporada, ing.nombre as 'IngPrincipal', ing.idIngrediente, ing.caloriasPorcion, ing.tipoIngrediente, IFNULL(calificacion,-1) as 'calificacion'
     FROM recetas rec
     JOIN dificultad dif
     ON dif.idDificultad = rec.idDificultad 
     JOIN grupoalim
     ON rec.grupoAlimenticio = grupoalim.idGrupoAlim
     JOIN temporadas tem
     ON tem.idTemporada = rec.temporada
     JOIN ingredientes ing
     ON ing.idIngrediente = rec.ingredientePrincipal
	 WHERE rec.idReceta = idReceta;
     
END $$

CREATE PROCEDURE SP_ObtenerIngredientesReceta(
	IN idReceta int
)
BEGIN

	SELECT ing.nombre as 'ingrediente', rel.cantidad FROM relrecetaingredientes rel
     JOIN ingredientes ing
     ON rel.idIngrediente = ing.idIngrediente
     WHERE rel.idReceta = idReceta;
     
END $$

CREATE PROCEDURE SP_ObtenerCondimentosReceta(
	IN idReceta int
)
BEGIN
	SELECT cond.nombre as 'condimento' FROM relrecetcondimento rel
     JOIN condimento cond
     ON rel.idCondimento = cond.idCondimento
     WHERE rel.idReceta = idReceta;
     
END $$

CREATE PROCEDURE SP_ObtenerPasosReceta(
	IN idReceta int
)
BEGIN

	SELECT * FROM pasos pa 
    WHERE pa.idReceta = idReceta;
    
END $$

CREATE PROCEDURE SP_agregarAHistorial(
	IN idReceta int, 
    IN username varchar(30)
)
BEGIN

	
    INSERT INTO grupo88.historial (fecha, idReceta, usuario)
    VALUES (current_date(), idReceta, username);
    
    
END $$

CREATE PROCEDURE SP_calUltimaConfirmacion(
	IN idRec int, 
    IN username varchar(30),
    IN calificacion int
)
BEGIN
	declare ultCal int;
    set ultCal = (select calificacionUsuario 
		  from Grupo88.historial his
          WHERE his.idReceta =  idRec and
		  his.usuario = username
		  ORDER BY his.fecha, his.idHistorial desc
		  LIMIT 1);
          
	if ( ultCal = 0 )
		then
			BEGIN
			UPDATE Grupo88.recetas rec
            SET puntajeTotal = puntajeTotal + calificacion,
				vecesCalificada = vecesCalificada + 1
			WHERE rec.idReceta = idRec;
            END;
		else
			BEGIN
			UPDATE Grupo88.recetas rec
            SET puntajeTotal = puntajeTotal + calificacion - ultCal
			WHERE rec.idReceta = idRec;
            END;
	end if;
    
    UPDATE grupo88.historial his
    SET calificacionUsuario = calificacion
    WHERE his.idReceta = idRec and
		  his.usuario = username
	ORDER BY his.fecha, his.idHistorial desc
    LIMIT 1;
    
    
END $$

CREATE PROCEDURE SP_cargarGruposUsuario(
	IN username varchar(30))
BEGIN

	SELECT * from Grupo88.grupos gr
    JOIN relusuariogrupo rel
    ON rel.nombreUsuario = username
    WHERE rel.idGrupo = gr.idGrupo;
    
END$$

CREATE PROCEDURE SP_cargarGrupos()
BEGIN

	SELECT * from Grupo88.grupos;
    
END$$

CREATE PROCEDURE SP_salirGrupo(
IN username varchar(30),
IN idGrupoIN int)
BEGIN
	
    DELETE FROM relusuariogrupo 
    WHERE nombreUsuario = username and
		  idGrupo = idGrupoIN;
    
END$$

CREATE PROCEDURE SP_entrarGrupo(
IN username varchar(30),
IN idGrupoIN int)
BEGIN
	
    INSERT INTO relusuariogrupo 
    VALUES(username,idGrupoIN);
    
END$$

CREATE PROCEDURE SP_agregarNuevoGrupo(
IN nombre varchar(30),
IN creador varchar(30),
IN detalle varchar (255))

BEGIN

	INSERT INTO grupos(nombreGrupo,detalle,creador)
    VALUES (nombre,detalle,creador);
    
	select idGrupo from grupos
    where nombreGrupo = nombre;

END$$

CREATE PROCEDURE SP_agregarHistorico(
IN username varchar(30),
IN idReceta int)
BEGIN

	INSERT INTO historicoConsultas(username,idReceta,fecha)
    VALUES(username,idReceta,current_date());
    
END$$

CREATE PROCEDURE SP_grupoTieneReceta(
IN idGrupo int,
IN idReceta int)
BEGIN

	select * from relgruporeceta rel
    where rel.idGrupo = idGrupo and
			rel.idReceta = idReceta;
    
END$$

CREATE PROCEDURE SP_agregarRecetaGrupo(
IN idGrupo int,
IN idReceta int)
BEGIN
	INSERT INTO relgruporeceta(idGrupo,idReceta)
    VALUES (idGrupo,idReceta);
END$$
    
CREATE PROCEDURE SP_agregarReceta(
IN creador varchar(30),
IN nombreReceta varchar(30),
IN descripcion varchar(200),
IN dificultad int,
IN calorias int,
IN grpAlim int,
IN temporada int,
IN ingPrinc int,
OUT idReceta int
)
BEGIN

	START TRANSACTION;
	INSERT INTO recetas(creador,nombre, descripcion, idDificultad,calorias,grupoAlimenticio,temporada,ingredientePrincipal)
    VALUES (creador,nombreReceta, descripcion, dificultad,calorias,grpAlim,temporada,ingPrinc);
    
    SET idReceta = (SELECT last_insert_id() FROM recetas limit 1);
    
	COMMIT;
    
END$$    

CREATE PROCEDURE SP_TOPHrecetas()
BEGIN
	SELECT R.nombre AS nombreReceta, count(*) AS cantidad 
    FROM Historial AS H 
    JOIN Usuarios AS U ON H.usuario = U.nombreUsuario 
    JOIN Recetas AS R ON H.idReceta = R.idReceta 
    WHERE U.sexo = 'H'
    GROUP BY R.nombre ORDER BY cantidad DESC;
END$$

CREATE PROCEDURE SP_TOPMrecetas()
BEGIN
	SELECT R.nombre AS nombreReceta, count(*) AS cantidad 
    FROM Historial AS H 
    JOIN Usuarios AS U ON H.usuario = U.nombreUsuario 
    JOIN Recetas AS R ON H.idReceta = R.idReceta 
    WHERE U.sexo = 'M'
    GROUP BY R.nombre ORDER BY cantidad DESC;
END$$

CREATE PROCEDURE SP_TOPdificultad()
BEGIN
	SELECT D.descripcion AS dificultad, count(*) AS cantidad 
    FROM Historial AS H 
    JOIN Recetas AS R ON H.idReceta = R.idReceta 
    JOIN dificultad AS D ON R.idDificultad = D.idDificultad
    GROUP BY D.descripcion ORDER BY cantidad DESC;
END$$

CREATE PROCEDURE SP_TOPrecetas()
BEGIN
	SELECT R.nombre AS nombreReceta, count(*) AS cantidad 
    FROM Historial AS H 
    JOIN Recetas AS R ON H.idReceta = R.idReceta 
    GROUP BY R.nombre ORDER BY cantidad DESC;
END$$

CREATE PROCEDURE SP_obtenerGrupo(
IN idGrupo int)
BEGIN
	SELECT * FROM grupos gr
    WHERE gr.idGrupo = idGrupo;
END$$

CREATE PROCEDURE SP_obtenerRecetasGrupo(
IN idGrupo int)
BEGIN
	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad, grupoalim.descripcion as 'grpAlim',
			tem.nombreTemporada, tem.idTemporada, ing.nombre as 'IngPrincipal', ing.idIngrediente, ing.caloriasPorcion, ing.tipoIngrediente
     FROM recetas rec
     JOIN dificultad dif
     ON dif.idDificultad = rec.idDificultad 
     JOIN grupoalim
     ON rec.grupoAlimenticio = grupoalim.idGrupoAlim
     JOIN temporadas tem
     ON tem.idTemporada = rec.temporada
     JOIN ingredientes ing
     ON ing.idIngrediente = rec.ingredientePrincipal
     JOIN relgruporeceta rel
     ON rel.idGrupo = idGrupo
	 WHERE rec.idReceta = rel.idReceta;
END$$

CREATE PROCEDURE SP_agregarPasoAReceta(
IN idRecetaNva int,
IN nroPaso int,
IN descrip varchar(2000))
-- IN fotoPaso blob)


BEGIN
	 INSERT INTO pasos (idReceta, numeroPaso,descripcion)
		VALUES (IdRecetaNva,nroPaso, descrip ); -- FALTA LA FOTO

END$$
DELIMITER ;
