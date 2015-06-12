drop database Grupo88;
Create database Grupo88;

create table Grupo88.Recetas(
ID_receta int(11) primary key auto_increment,
nombre varchar(45) not null
);


insert into Grupo88.Recetas(nombre)
values('pollo'),('milanesas');
    

DELIMITER $$
USE `Grupo88` $$
CREATE PROCEDURE `ObtenerRecetas` (in nombreBuscar varchar(45))
BEGIN
    select *
    from Grupo88.recetas
    where nombre = nombreBuscar;
END $$


CALL `Grupo88`.`ObtenerRecetas`('pollo');
call `Grupo88`.`todas`()
DELIMITER ;

