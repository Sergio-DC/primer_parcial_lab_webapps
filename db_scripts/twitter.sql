create database twitter;
use twitter;

CREATE TABLE usuario(
id_usuario INTEGER NOT NULL AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL UNIQUE,
pass VARCHAR(50) NOT NULL,
PRIMARY KEY (id_usuario)
);

CREATE TABLE comentario(
id_comentario INTEGER NOT NULL AUTO_INCREMENT,
id_usuario INTEGER  NOT NULL,
fecha_publicacion DATE NOT NULL,
contenido VARCHAR(150),
id_respuesta_a INTEGER,
eliminado BOOLEAN NOT NULL DEFAULT 0,
PRIMARY KEY (id_comentario),
FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
FOREIGN KEY(id_respuesta_a) REFERENCES comentario(id_comentario)
);

INSERT INTO usuario(nombre, pass) VALUES("Alan", "robocop");
INSERT INTO usuario(nombre, pass) VALUES("Carlos", "cora-98");
INSERT INTO usuario(nombre, pass) VALUES("David", "rojo23");
INSERT INTO usuario(nombre, pass) VALUES("Ernesto", "barcelona");
INSERT INTO usuario(nombre, pass) VALUES("Fernando", "madrid");

INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a)
VALUES(1, '2020-03-17', "Hola soy el primer comentario de Alan", NULL);

INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a)
VALUES(2, '2020-03-17', "Hola Alan tu comentario es bueno", 1);

INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a)
VALUES(5, '2020-03-18', "Hola Carlos soy David :)", 2);

/*Borrar comentarios hoja*/
DELETE FROM comentario WHERE id_comentario = 3;

/*Borrar comentarios de enmedio*/
/*if(id_respuesta_a != NULL)
     DELETE FROM comentario WHERE 
*/
DELETE FROM comentario WHERE id_respuesta_a = 2;
