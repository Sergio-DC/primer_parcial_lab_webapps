package com.itesm.webapps.primer_parcial.action;

import java.util.List;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Comentario;
import com.itesm.webapps.primer_parcial.pojo.ComentarioUsuario;
import com.opensymphony.xwork2.ActionSupport;

public class PublicacionesAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public Comentario comentarioBean;
	public List<ComentarioUsuario> lista_comentario_usuario;
	
	public String mostrarPublicaciones() {
		lista_comentario_usuario = StrutsJsonDAO.fetchComentarioMejorado();
		if(lista_comentario_usuario == null)
			return ERROR;
		else
			return SUCCESS;
	}	
	
	public Comentario getComentarioBean() {
		return comentarioBean;
	}
	public void setComentarioBean(Comentario comentarioBean) {
		this.comentarioBean = comentarioBean;
	}

	public List<ComentarioUsuario> getLista_comentario_usuario() {
		return lista_comentario_usuario;
	}

	public void setLista_comentario_usuario(List<ComentarioUsuario> lista_comentario_usuario) {
		this.lista_comentario_usuario = lista_comentario_usuario;
	}
	
}
