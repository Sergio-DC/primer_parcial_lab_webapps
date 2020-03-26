package com.itesm.webapps.primer_parcial.pojo;

import java.time.LocalDate;

public class Comentario {	
	private int id_comentario;
	private int id_usuario;
	private LocalDate fecha_publicacion;
	private String contenido;
	private Integer id_respuesta_a;
	private int eliminado;
	
	public Comentario() {}
	
	public Comentario(int id_comentario, int id_usuario, LocalDate fecha_publicacion, String contenido, int id_respuesta_a, int eliminado) {
		super();
		this.id_comentario = id_comentario;
		this.id_usuario = id_usuario;
		this.setFecha_publicacion(fecha_publicacion);
		this.contenido = contenido;
		this.id_respuesta_a = id_respuesta_a;
		this.eliminado = eliminado;
	}
	public int getId_comentario() {
		return id_comentario;
	}
	public void setId_comentario(int id_comentario) {
		this.id_comentario = id_comentario;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public Integer getId_respuesta_a() {
		return id_respuesta_a;
	}

	public void setId_respuesta_a(Integer id_respuesta_a) {
		this.id_respuesta_a = id_respuesta_a;
	}

	public LocalDate getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(LocalDate fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public int getEliminado() {
		return eliminado;
	}

	public void setEliminado(int eliminado) {
		this.eliminado = eliminado;
	}
}