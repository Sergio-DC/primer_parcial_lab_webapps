package com.itesm.webapps.primer_parcial.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Comentario;
import com.itesm.webapps.primer_parcial.pojo.ComentarioUsuario;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class PublicacionesAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private SessionMap<String,Object> sessionMap;
	public Comentario comentarioBean;
	public List<ComentarioUsuario> lista_comentario_usuario;
	private String comentario;
	private int resA;
	private int idC;
	
	public String mostrarPublicaciones() {
		System.out.println("User sesion: "+sessionMap.get("user"));
		System.out.println("id sesion: "+sessionMap.get("id"));
		System.out.println(sessionMap.get("id"));
		lista_comentario_usuario = StrutsJsonDAO.fetchComentarioMejorado();
		if(lista_comentario_usuario == null)
			return ERROR;
		else
			return SUCCESS;
	}
	/*La siguiente función permite crear un nevo contenido o crear un réplica a otro usuario*/
	public String crearNuevoContenido() {
		boolean seRegistro;
		
		if(resA == -1)//Nuevo Contenido
			seRegistro = StrutsJsonDAO.insertarComentario((Integer)sessionMap.get("id"), comentario, null);
		else//Réplica
			seRegistro = StrutsJsonDAO.insertarComentario((Integer)sessionMap.get("id"), comentario, resA);
		
		if(seRegistro){
			System.out.println("Succes");
			return SUCCESS;}
		else
			return ERROR;	
	}
	/*El siguiente método permite al usuario modificar un comentario siempre y cuando el lo haya creado
	 * Ojo: la fecha se actualiza autómaticamente cuando actualizamos el comentario
	 * */
	public String modificarContenido() {
		int noCommentModificado = 0;
		try {
			noCommentModificado = StrutsJsonDAO.modificarComentario(idC, comentario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(noCommentModificado);
		if(noCommentModificado == 0)
			return ERROR;
		else
			return SUCCESS;
	}
	
	/*El siguiente método elimina una publicación*/
	public String eliminarContenido() {
		System.out.println(idC);
		int eliminado = 0;
		try {
			eliminado = StrutsJsonDAO.eliminarComentario(idC);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(eliminado == 1)
			return SUCCESS;
		else
			return ERROR;
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

	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap)map;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getResA() {
		return resA;
	}
	public void setResA(int resA) {
		this.resA = resA;
	}

	public int getIdC() {
		return idC;
	}

	public void setIdC(int idC) {
		this.idC = idC;
	}
}
