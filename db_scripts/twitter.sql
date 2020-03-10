create database twitter;
use database twitter;

CREATE TABLE usuario(
id_usuario INTEGER NOT NULL AUTOINCREMENT,
nombre VARCHAR(50) NOT NULL UNIQUE,
pass VARCHAR(50) NOT NULL
PRIMARY KEY(id_usuario)
);

CREATE TABLE comentario(
id_comentario INTEGER NOT NULL AUTOINCREMENT,
id_usuario INTEGER  NOT NULL,
fecha_publicacion DATE NOT NULL,
contenido VARCHAR(150),
id_respuesta_a INTEGER,
PRIMARY KEY (id_comentario),
FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
FOREIGN KEY(id_respuesta_a) REFERENCES comentario(id_comentario)

);