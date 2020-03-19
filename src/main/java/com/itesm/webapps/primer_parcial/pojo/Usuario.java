package com.itesm.webapps.primer_parcial.pojo;

public class Usuario {
	
	public Usuario() {}
	public Usuario(int id_usuario, String nombre, String pass) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.pass = pass;
	}
	private int id_usuario;
	private String nombre;
	private String pass;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
}
