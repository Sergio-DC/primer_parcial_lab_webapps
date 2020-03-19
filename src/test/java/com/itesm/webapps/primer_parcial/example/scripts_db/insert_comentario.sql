INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(1, '2020-03-17', "Hola soy el primer comentario de Carlos", NULL);
INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(2, '2020-03-17', "Hola Carlos tu comentario es bueno soy Alan", 1);
INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(5, '2020-03-18', "Hola Carlos soy Fernando -> Alan", 2);
INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(4, '2020-03-19', "Hola Carlos soy Ernesto :)->Alan", 2);
INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(5, '2020-03-19', "Hola Ernest soy otra vez Fernando->", 4);
INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(1, '2020-03-19', "Hola Ernest soy Carlos->", 4);
INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(3, '2020-03-19', "Soy David y no me comentaron", NULL);
