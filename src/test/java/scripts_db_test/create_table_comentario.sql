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
