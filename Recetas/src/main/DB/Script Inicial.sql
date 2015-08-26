DROP DATABASE IF EXISTS Grupo88;
CREATE DATABASE Grupo88 CHARACTER SET utf8 COLLATE utf8_bin;

DROP USER 'llevaYtrae'@'localhost';

CREATE USER 'llevaYtrae'@'localhost' IDENTIFIED BY 'gil';
GRANT SELECT, UPDATE, DELETE, INSERT, EXECUTE ON grupo88.* TO 'llevaYtrae'@'localhost';

-- ----------------------------------  CREACION DE TABLAS

CREATE TABLE Grupo88.dificultad(
	idDificultad INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(30) NOT NULL
);

CREATE TABLE Grupo88.tipoIngrediente(
	idTipoIngrediente INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(30) NOT NULL
);  

CREATE TABLE Grupo88.Condimento(
	idCondimento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

CREATE TABLE Grupo88.GrupoAlim(
	idGrupoAlim INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR (50) NOT NULL
);

CREATE TABLE Grupo88.Ingredientes(
	idIngrediente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    caloriasPorcion INT NOT NULL,
    tipoIngrediente INT NOT NULL
);

CREATE TABLE Grupo88.Temporadas(
	idTemporada INT AUTO_INCREMENT PRIMARY KEY,
    nombreTemporada VARCHAR(50) NOT NULL
);


CREATE TABLE Grupo88.Condiciones(
	idCondicion INT AUTO_INCREMENT PRIMARY KEY,
    condicion VARCHAR(30) NOT NULL
);

CREATE TABLE Grupo88.PreferenciasAlimenticias(
	idPreferencia INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(120) NOT NULL
);

CREATE TABLE Grupo88.Complexion(
	idComplexion INT AUTO_INCREMENT PRIMARY KEY,
    complexion VARCHAR(30) NOT NULL
);

CREATE TABLE Grupo88.Rutinas(
	idRutina INT AUTO_INCREMENT PRIMARY KEY,
    rutina VARCHAR(45) NOT NULL
);

CREATE TABLE Grupo88.Dietas(
	idDieta INT AUTO_INCREMENT PRIMARY KEY,
    tipoDieta VARCHAR(30) NOT NULL
);


CREATE TABLE Grupo88.Usuarios(
	nombreUsuario VARCHAR(30) PRIMARY KEY,
	clave VARCHAR(120) NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    mail VARCHAR(60) NOT NULL,
    fechaNac DATE,
    sexo CHAR(1) NOT NULL,
    altura INT NOT NULL,
    idComplexion INT, -- REFERENCES Complexiones,
    idDieta INT, -- REFERENCES dietas,
    idRutina INT, -- REFERENCES Rutinas
    FOREIGN KEY (idComplexion) REFERENCES Grupo88.Complexion(idComplexion),
    FOREIGN KEY (idDieta) REFERENCES Grupo88.dietas(idDieta),
    FOREIGN KEY (idRutina) REFERENCES Grupo88.rutinas(idRutina)
);

CREATE TABLE Grupo88.Recetas(
	idReceta INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(45) NOT NULL,
	creador  VARCHAR(30), -- REFERENCES Usuarios,
    descripcion VARCHAR(200),
    idDificultad INT, -- REFERENCES dificultad,
    calorias INT NOT NULL,
    grupoAlimenticio INT, -- REFERENCES grupoalim,
    temporada INT, -- REFERENCES temporadas,
    ingredientePrincipal INT, -- REFERENCES ingredientes,
    puntajeTotal INT DEFAULT 0,
    vecesCalificada INT DEFAULT 0,
    FOREIGN KEY (creador) REFERENCES Grupo88.Usuarios(nombreUsuario),
    FOREIGN KEY (idDificultad) REFERENCES Grupo88.dificultad(idDificultad),
    FOREIGN KEY (grupoAlimenticio) REFERENCES Grupo88.grupoalim(idGrupoAlim),
    FOREIGN KEY (temporada) REFERENCES Grupo88.temporadas(idTemporada),
    FOREIGN KEY (ingredientePrincipal) REFERENCES Grupo88.ingredientes(idIngrediente)
);

CREATE TABLE Grupo88.Grupos(
	idGrupo INT AUTO_INCREMENT PRIMARY KEY,
    nombreGrupo VARCHAR(30) UNIQUE,
    detalle VARCHAR(255) NOT NULL,
    creador VARCHAR(30), -- REFERENCES Usuarios
    FOREIGN KEY (creador) REFERENCES Grupo88.usuarios(nombreUsuario)
);

CREATE TABLE Grupo88.Pasos(
	idReceta INT(11), -- REFERENCES Recetas,
    numeroPaso INT,
    descripcion VARCHAR(2000) NOT NULL,
    foto BLOB NULL,
    UNIQUE(idReceta, numeroPaso),
    FOREIGN KEY(idReceta) REFERENCES Grupo88.recetas(idReceta)
);

CREATE TABLE Grupo88.amigos(
	nombreUsuario1 VARCHAR(30) REFERENCES Usuarios,
    nombreUsuario2 VARCHAR(30) REFERENCES Usuarios,
    PRIMARY KEY(nombreUsuario1,nombreUsuario2),
    CHECK(nombreUsuario1 <> nombreUsuario2)
    
);

CREATE TABLE Grupo88.Historial(
	idHistorial INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    idReceta INT NOT NULL,
    usuario VARCHAR(30), --  REFERENCES usuarios,
    cantVecesUsada INT,
    calificacionUsuario INT default 0,
    FOREIGN KEY (usuario) REFERENCES Grupo88.usuarios(nombreUsuario)
);


CREATE TABLE Grupo88.historicoConsultas(
	idHistorico INT auto_increment PRIMARY KEY,
	idReceta INT, -- REFERENCES recetas,
    username VARCHAR(30), -- REFERENCES Usuarios,
    fecha DATE,
    FOREIGN KEY (idReceta) REFERENCES Grupo88.recetas(idReceta),
    FOREIGN KEY (username) REFERENCES Grupo88.usuarios(nombreUsuario)
);


CREATE TABLE Grupo88.relRecetCondimento(
	idReceta INT(11) REFERENCES Recetas(idReceta),
    idCondimento INT REFERENCES Condimento,
    PRIMARY KEY(idReceta, idCondimento)
);

CREATE TABLE Grupo88.relRecetaIngredientes(
	idReceta INT(11) REFERENCES Recetas,
    idIngrediente INT REFERENCES Ingredientes,
    cantidad INT NOT NULL,
    PRIMARY KEY(idReceta, idIngrediente)
);

CREATE TABLE Grupo88.relTipoIngGrupoAlim(
	idTipoIngrediente INT REFERENCES idTipoIngrediente,
	idGrupoAlim INT REFERENCES idGrupoAlim,
    PRIMARY KEY (idTipoIngrediente, idGrupoAlim)
);

CREATE TABLE Grupo88.relUsuarioGrupo(
	nombreUsuario VARCHAR(30) REFERENCES Usuarios,
    idGrupo INT REFERENCES Grupos,
    PRIMARY KEY(nombreUsuario, idGrupo)
);

CREATE TABLE Grupo88.relUsuarioCondicion(
	nombreUsuario VARCHAR(30) REFERENCES Usuarios,
    idCondicion INT REFERENCES Condiciones,
    PRIMARY KEY(nombreUsuario, idCondicion)
);


CREATE TABLE Grupo88.relUsuarioDietas(
	nombreUsuario VARCHAR(30) REFERENCES Usuarios,
    idDieta INT REFERENCES Dietas,
    PRIMARY KEY (nombreUsuario, idDieta)
);


CREATE TABLE Grupo88.relUsuarioPreferencia(
	
    nombreUsuario VARCHAR(30) REFERENCES Usuarios,
    idPreferencia INT REFERENCES PreferenciasAlimenticias,
    PRIMARY KEY (nombreUsuario, idPreferencia)
);

CREATE TABLE Grupo88.relgruporeceta(
	idGrupo INT REFERENCES grupos,
	idReceta INT REFERENCES Recetas,
	PRIMARY KEY(idGrupo,idReceta)
);

CREATE TABLE Grupo88.relCondPreexIngNoComestible(
	idCond INT REFERENCES condiciones,
    idIngNoComestible INT REFERENCES ingredientes,
    PRIMARY KEY(idCond,idIngNoComestible)
);

CREATE TABLE Grupo88.relDietaTipoIngNoComestible(
	idDieta INT REFERENCES dietas,
    idTipoIngNoComestible INT REFERENCES tipoingrediente,
    PRIMARY KEY(idDieta, idTipoIngNoComestible)
);
-- ----------------------------------- INSERT EN TABLAS

INSERT INTO Grupo88.dificultad(descripcion)
VALUES('Facil'),
	  ('Media'),
	  ('Dificil');
      
INSERT INTO Grupo88.tipoingrediente(descripcion)
VALUES ('Cereales y derivados'),('Legumbres'),('Huevos'),('Azucares y dulces'),('Verduras y Hortalizas'),
	   ('Frutas'),('Frutos Secos'),('Lacteos y derivados'),('Carnes'), ('Pescados y Mariscos'), 
       ('Aceites y grasas'), ('Otros');
       
INSERT INTO Grupo88.condimento (nombre)
VALUES ('Sal'),('Pimienta Negra'),('Paprika'),('Oregano'),('Laurel'),('Aji Molido'),('Azafran');
      
INSERT INTO Grupo88.GrupoAlim (descripcion)
VALUES ('Grupo 1: Leche y Derivados'), 
	   ('Grupo 2: Carnes, Huevos y Pescado'), 
       ('Grupo 3: Papas, Legumbres, Frutos Secos'),
       ('Grupo 4: Verduras y Hortalizas'),
       ('Grupo 5: Frutas'),
       ('Grupo 6: Harinas, Cereales, Dulces'),
       ('Grupo 7: Grasas, Aceites y Mantequilla');

INSERT INTO Grupo88.ingredientes(nombre,caloriasPorcion, tipoIngrediente)
VALUES ('Arroz',354,1), ('Avena',367,1),('Harina',150, 1), -- cereales y derivados
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
       
INSERT INTO Grupo88.Temporadas(NombreTemporada)
VALUES('Verano'),('Otoño'),('Invierno'),('Primavera'),('Navidad'),('Pascua'),('Fechas de Final');
       
INSERT INTO Grupo88.condiciones(condicion)
VALUES('Diabetes'),('Hipertencion'),('Celiasis'), ('Mariposon');

INSERT INTO Grupo88.complexion(complexion)
VALUES('Pequeña') ,
	  ('Mediana'),
      ('Grande');
 
INSERT INTO Grupo88.Rutinas (rutina)
VALUES ('Sedentaria con algo de ejercicio'),
	   ('Sedentaria con nada de ejercicio'),
       ('Sedentaria con ejercicio'),
       ('Activa con ejercicio'),
       ('Activa sin ejercicio');

INSERT INTO Grupo88.dietas(tipoDieta)
VALUES ('Normal'),('Ovolactovegetariano'),('Vegetariano'),('Vegano'), ('Solo Cebollita');
      

INSERT INTO Grupo88.usuarios
VALUES('jorge','pass','Jorge','Gomez','',null,'M',170,3,2,1),
	  ('maria','pass','Maria','Rodriguez','',null,'F',150,1,4,2),
      ('carlos', 'pass', 'Carlos', 'Batata','@gmail.com',null,'M', 160, 2, 1, 3);

INSERT INTO Grupo88.Recetas(nombre,creador,idDificultad,calorias,grupoAlimenticio,temporada,ingredientePrincipal)
VALUES('Pollo al horno','jorge',2,1000,2,1,31),
	  ('milanesas napolitana','maria',1,800,2,3,34),
      ('Pulpo en su tinta',null,3,1200,2,5,35),
      ('Ñoquis','maria',2,760,6,4,3),
      ('Pan con queso',null,1,1000,1,1,29),
      ('Pizza','jorge',1,1150,6,2,3),
      ('Asado','jorge',2,940,2,2,32);
    

INSERT INTO Grupo88.Historial(fecha,idReceta,usuario)
VALUES ('2013-08-27',3,'jorge'),
	   ('2013-08-29', 1, 'jorge');


INSERT INTO  Grupo88.pasos(idReceta,numeroPaso,descripcion)
VALUES (1,1,'Descongelar el pollo'),(1,2,'Sacarle lo que no sirve'),
		(1,3,'Mandarlo al horno'),(1,4,'Agregar papa si se quiere'),
        (1,5,'Disfrutar del pollo');
        
INSERT INTO Grupo88.grupos(nombreGrupo,creador,detalle)
VALUES('Locomia','jorge','Aca se come de todo'),
		('Putos','carlos','cualquier cosa ponele');
        
INSERT INTO Grupo88.relusuariocondicion()
VALUES ('jorge',1),('jorge',3);

INSERT INTO Grupo88.relusuariogrupo()
VALUES ('maria',1),('carlos',1),('carlos',2);

INSERT INTO Grupo88.relgruporeceta()
VALUES (1,1);

INSERT INTO Grupo88.relRecetaIngredientes (idReceta,idIngrediente,cantidad)
VALUES (1,31,1),(1,21,1),
	   (3,34,1),(3,13,5),
       (4,3,2),(4,29,2);
       
INSERT INTO Grupo88.relCondPreexIngNoComestible()
VALUES (1,1),(1,3),(2,3);


DELIMITER $$
USE `Grupo88` $$


-- ------------------------------------------------STORE PROCEDURES

CREATE PROCEDURE raise_error(
    IN mensaje VARCHAR(500)
)
BEGIN

	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = mensaje;
END $$


CREATE PROCEDURE `ObtenerRecetas` (IN nombreBuscar VARCHAR(45))
BEGIN
    SELECT *
    FROM Grupo88.recetas
    WHERE nombre = nombreBuscar;
END $$

USE `Grupo88` $$
CREATE PROCEDURE `SP_Login`(
	IN username VARCHAR(30),
	IN pass VARCHAR(120),
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
	IN username VARCHAR(30)
)
BEGIN

	SELECT us.*, com.complexion, rut.rutina, die.tipoDieta FROM usuarios us
    JOIN complexion com 
		ON us.idComplexion = com.idComplexion
    JOIN rutinas rut
		ON rut.idRutina = us.idRutina
	JOIN dietas die
		ON us.idDieta = die.idDieta
    WHERE nombreUsuario = username;
END $$


CREATE PROCEDURE SP_CargarCondPreexUsuario(
	IN username VARCHAR(30)
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
IN username VARCHAR(30) 
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
IN username VARCHAR(30) 
)
BEGIN

	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion, dif.idDificultad, rec.descripcion
    FROM recetas rec 
    JOIN dificultad dif
    ON rec.idDificultad = dif.idDificulrecetastad
    WHERE rec.creador = username;
END $$

CREATE PROCEDURE SP_BuscarRecetas(
IN dificultadB INT,
IN temporadaB INT,
IN ingredienteB INT,
IN grupoAlim INT,
IN calificacion INT,
IN caloriasMax INT,
IN caloriasMin INT
)
BEGIN
	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad, rec.descripcion
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
IN username_ VARCHAR(30),
IN pass_ VARCHAR(30),
IN mail_ VARCHAR(30),
IN nombre_ VARCHAR(30),
IN apellido_ VARCHAR(30),
IN fechaNac_ DATE,
IN sexo_ VARCHAR(1),
IN altura_ INT,
IN idComplexion INT,
IN idDieta INT,
IN IdRutina INT,
out respuesta VARCHAR(90)
)
BEGIN
	
	DECLARE rutinaID INT;
    
    
	DECLARE exit HANDLER FOR SQLSTATE '23000' call raise_error('El usuario ingresado ya existe');

		INSERT INTO usuarios(nombreUsuario,clave,mail,nombre,apellido,sexo,fechaNac,altura,idComplexion,idDieta,idRutina)
			VALUES(username_,pass_,mail_,nombre_,apellido_,sexo_,fechaNac_,altura_,idComplexion ,idDieta,idRutina);
END $$

CREATE PROCEDURE SP_modificarPerfil(
IN username_ VARCHAR(30),
IN nombre_ VARCHAR(30),
IN apellido_ VARCHAR(30),
IN mail_ VARCHAR(30),
IN fechaNac_ DATE,
IN sexo_ VARCHAR(1),
IN altura_ INT,
IN idComplexion INT,
IN idDieta INT,
IN IdRutina INT

)
BEGIN

	UPDATE usuarios usu
	SET  usu.nombre= nombre_, usu.apellido = apellido_, usu.mail=mail_, usu.fechaNac = fechaNac_, usu.sexo = sexo_, 
		usu.altura=altura_,	usu.idComplexion = idComplexion, usu.idDieta = idDieta, usu.idRutina = idRutina
    WHERE usu.nombreUsuario=username_;
    
    
END$$



CREATE PROCEDURE SP_RegistrarCondPreexUsuario(
IN username VARCHAR(30),
IN idCondPreex INT
)
BEGIN
	 INSERT INTO relusuariocondicion
		VALUES (username, idCondPreex);
END $$

CREATE PROCEDURE SP_ObtenerReceta(
	IN idReceta INT,
    IN username VARCHAR(30)
)
BEGIN
	DECLARE calificacion INT;
    
    set calificacion = (SELECT his.calificacionUsuario FROM Grupo88.historial his
    WHERE his.idReceta = idReceta
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
	IN idReceta INT
)
BEGIN

	SELECT ing.nombre as 'ingrediente', rel.cantidad FROM relrecetaingredientes rel
     JOIN ingredientes ing
     ON rel.idIngrediente = ing.idIngrediente
     WHERE rel.idReceta = idReceta;
     
END $$

CREATE PROCEDURE SP_ObtenerCondimentosReceta(
	IN idReceta INT
)
BEGIN
	SELECT cond.nombre as 'condimento' FROM relrecetcondimento rel
     JOIN condimento cond
     ON rel.idCondimento = cond.idCondimento
     WHERE rel.idReceta = idReceta;
     
END $$

CREATE PROCEDURE SP_ObtenerPasosReceta(
	IN idReceta INT
)
BEGIN

	SELECT * FROM pasos pa 
    WHERE pa.idReceta = idReceta;
    
END $$

CREATE PROCEDURE SP_agregarAHistorial(
	IN idReceta INT, 
    IN username VARCHAR(30)
)
BEGIN

	
    INSERT INTO Grupo88.historial (fecha, idReceta, usuario)
    VALUES (current_date(), idReceta, username);
    
    
END $$

CREATE PROCEDURE SP_calUltimaConfirmacion(
	IN idRec INT, 
    IN username VARCHAR(30),
    IN calificacion INT
)
BEGIN
	DECLARE ultCal INT;
    set ultCal = (SELECT calificacionUsuario 
		  FROM Grupo88.historial his
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
    
    UPDATE Grupo88.historial his
    SET calificacionUsuario = calificacion
    WHERE his.idReceta = idRec and
		  his.usuario = username
	ORDER BY his.fecha, his.idHistorial desc
    LIMIT 1;
    
    
END $$

CREATE PROCEDURE SP_cargarGruposUsuario(
	IN username VARCHAR(30))
BEGIN

	SELECT * FROM Grupo88.grupos gr
    JOIN relusuariogrupo rel
    ON rel.nombreUsuario = username
    WHERE rel.idGrupo = gr.idGrupo;
    
END$$

CREATE PROCEDURE SP_cargarGrupos()
BEGIN

	SELECT * FROM Grupo88.grupos;
    
END$$

CREATE PROCEDURE SP_salirGrupo(
IN username VARCHAR(30),
IN idGrupoIN INT)
BEGIN
	
    DELETE FROM relusuariogrupo 
    WHERE nombreUsuario = username and
		  idGrupo = idGrupoIN;
    
END$$

CREATE PROCEDURE SP_entrarGrupo(
IN username VARCHAR(30),
IN idGrupoIN INT)
BEGIN
	
    INSERT INTO relusuariogrupo 
    VALUES(username,idGrupoIN);
    
END$$

CREATE PROCEDURE SP_agregarNuevoGrupo(
IN nombre VARCHAR(30),
IN creador VARCHAR(30),
IN detalle VARCHAR (255))

BEGIN

	INSERT INTO grupos(nombreGrupo,detalle,creador)
    VALUES (nombre,detalle,creador);
    
	SELECT idGrupo FROM grupos
    WHERE nombreGrupo = nombre;

END$$

CREATE PROCEDURE SP_agregarHistorico(
IN username VARCHAR(30),
IN idReceta INT)
BEGIN

	INSERT INTO historicoConsultas(username,idReceta,fecha)
    VALUES(username,idReceta,current_date());
    
END$$

CREATE PROCEDURE SP_grupoTieneReceta(
IN idGrupo INT,
IN idReceta INT)
BEGIN

	SELECT * FROM relgruporeceta rel
    WHERE rel.idGrupo = idGrupo and
			rel.idReceta = idReceta;
    
END$$

CREATE PROCEDURE SP_agregarRecetaGrupo(
IN idGrupo INT,
IN idReceta INT)
BEGIN
	INSERT INTO relgruporeceta(idGrupo,idReceta)
    VALUES (idGrupo,idReceta);
END$$
    
CREATE PROCEDURE SP_agregarReceta(
IN creador VARCHAR(30),
IN nombreReceta VARCHAR(30),
IN descripcion VARCHAR(200),
IN dificultad INT,
IN calorias INT,
IN grpAlim INT,
IN temporada INT,
IN ingPrinc INT,
OUT idReceta INT
)
BEGIN

	START TRANSACTION;
	INSERT INTO recetas(creador,nombre, descripcion, idDificultad,calorias,grupoAlimenticio,temporada,ingredientePrincipal)
    VALUES (creador,nombreReceta, descripcion, dificultad,calorias,grpAlim,temporada,ingPrinc);
    
    SET idReceta = (SELECT last_insert_id() FROM recetas limit 1);
    
	COMMIT;
    
END$$    

CREATE PROCEDURE SP_TOPRecetas(
IN sexo char,
IN dias int)
BEGIN
		select his.idReceta, rec.nombre, us.sexo, count(*) as cantidad from historicoconsultas his
	JOIN usuarios us
	on us.nombreUsuario = his.username
	JOIN recetas rec
	on rec.idReceta = his.idReceta
	WHERE DATE_SUB(CURDATE(),INTERVAL dias DAY) <= fecha and
			us.sexo = sexo
	group by his.idReceta, us.sexo
	ORDER BY count(*) desc
	LIMIT 10;

END$$
/*
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
*/
CREATE PROCEDURE SP_obtenerGrupo(
IN idGrupo INT)
BEGIN
	SELECT * FROM grupos gr
    WHERE gr.idGrupo = idGrupo;
END$$

CREATE PROCEDURE SP_obtenerRecetasGrupo(
IN idGrupo INT)
BEGIN
	SELECT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad, grupoalim.descripcion as 'grpAlim',
			tem.nombreTemporada, tem.idTemporada, ing.nombre as 'IngPrincipal', ing.idIngrediente, ing.caloriasPorcion, ing.tipoIngrediente,
            rec.descripcion
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

CREATE PROCEDURE SP_obtenerUsuariosGrupo(
IN idGrupo int)
BEGIN
	
    SELECT us.*, com.complexion, rut.rutina, die.tipoDieta FROM relusuariogrupo rel
    JOIN usuarios us
		ON us.nombreUsuario = rel.nombreUsuario
    JOIN complexion com 
		ON us.idComplexion = com.idComplexion
    JOIN rutinas rut
		ON rut.idRutina = us.idRutina
	JOIN dietas die
		ON us.idDieta = die.idDieta
    where rel.idGrupo = idGrupo;
END$$
    
CREATE PROCEDURE SP_agregarPasoAReceta(
IN idRecetaNva INT,
IN nroPaso INT,
IN descrip VARCHAR(2000))
-- IN fotoPaso blob)


BEGIN
	 INSERT INTO pasos (idReceta, numeroPaso,descripcion)
		VALUES (IdRecetaNva,nroPaso, descrip ); -- FALTA LA FOTO. que foto

END$$

CREATE PROCEDURE SP_recetasHome(
IN username varchar(30))
BEGIN
	
    IF EXISTS (SELECT 1 FROM historial where usuario = username)
    THEN
		BEGIN
<<<<<<< HEAD
			SELECT DISTINCT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad
			FROM Grupo88.recetas rec
			JOIN Grupo88.dificultad dif
			ON dif.idDificultad = rec.idDificultad
			JOIN Grupo88.historial his
			ON his.usuario = username and
				his.idReceta = rec.idReceta
			ORDER BY his.idHistorial DESC
			LIMIT 10;
		END;
        ELSE
        BEGIN
			IF EXISTS(SELECT 1 FROM historicoconsultas hiscon where hiscon.username = username)
            THEN
				BEGIN
					SELECT DISTINCT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad
					FROM Grupo88.recetas rec
					JOIN Grupo88.dificultad dif
					ON dif.idDificultad = rec.idDificultad
					JOIN Grupo88.historicoconsultas hiscon
					ON hiscon.username = username and
						hiscon.idReceta = rec.idReceta
					ORDER BY hiscon.idHistorico DESC
					LIMIT 10;
				END;
                ELSE
                BEGIN
					CALL SP_mejoresCalificadas;
                END;
                END IF;
			END;
    END IF;
	
END$$

CREATE PROCEDURE SP_mejoresCalificadas()
BEGIN

	SELECT DISTINCT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad
=======
			SELECT DISTINCT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad,
							rec.descripcion
			FROM Grupo88.recetas rec
			JOIN Grupo88.dificultad dif
			ON dif.idDificultad = rec.idDificultad
			JOIN Grupo88.historial his
			ON his.usuario = username and
				his.idReceta = rec.idReceta
			ORDER BY his.idHistorial DESC
			LIMIT 10;
		END;
        ELSE
        BEGIN
			IF EXISTS(SELECT 1 FROM historicoconsultas hiscon where hiscon.username = username)
            THEN
				BEGIN
					SELECT DISTINCT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad,
									rec.descripcion
					FROM Grupo88.recetas rec
					JOIN Grupo88.dificultad dif
					ON dif.idDificultad = rec.idDificultad
					JOIN Grupo88.historicoconsultas hiscon
					ON hiscon.username = username and
						hiscon.idReceta = rec.idReceta
					ORDER BY hiscon.idHistorico DESC
					LIMIT 10;
				END;
                ELSE
                BEGIN
					CALL SP_mejoresCalificadas;
                END;
                END IF;
			END;
    END IF;
	
END$$

CREATE PROCEDURE SP_mejoresCalificadas()
BEGIN

	SELECT DISTINCT rec.idReceta, rec.nombre, rec.creador, dif.descripcion as 'dificultad', rec.idDificultad,
					rec.descripcion
>>>>>>> branch 'master' of https://github.com/Lea-angiolini/DDS_Grupo_88.git
					FROM Grupo88.recetas rec
					JOIN Grupo88.dificultad dif
					ON dif.idDificultad = rec.idDificultad
					ORDER BY rec.puntajeTotal DESC
					LIMIT 10;

END$$
DELIMITER ;
