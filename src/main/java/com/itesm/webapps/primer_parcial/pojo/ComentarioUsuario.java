package com.itesm.webapps.primer_parcial.pojo;

import java.time.LocalDate;

public class ComentarioUsuario {
	private int id_comentario;
	private LocalDate fecha_publicacion;
	private String contenido;
	private int id_respuesta_a;
	private int eliminado;
	
	private int id_usuario;
	private String nombre;
	public int getId_comentario() {
		return id_comentario;
	}
	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}
	public LocalDate getFecha_publicacion() {
		return fecha_publicacion;
	}
	public void setFecha_publicacion(LocalDate fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getId_respuesta_a() {
		return id_respuesta_a;
	}
	public void setId_respuesta_a(int id_respuesta_a) {
		this.id_respuesta_a = id_respuesta_a;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEliminado() {
		return eliminado;
	}

	public void setEliminado(int eliminado) {
		this.eliminado = eliminado;
	}
}
