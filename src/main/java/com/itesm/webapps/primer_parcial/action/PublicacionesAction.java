package com.itesm.webapps.primer_parcial.action;

import java.util.ArrayList;
import java.util.List;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Comentario;
import com.itesm.webapps.primer_parcial.pojo.ComentarioUsuario;
import com.opensymphony.xwork2.ActionSupport;

public class PublicacionesAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public Comentario comentarioBean;
	public List<ComentarioUsuario> lista_comentario_usuario;
	public ArrayList<Integer> registros_eliminados;
	
	public String mostrarPublicaciones() {
		lista_comentario_usuario = StrutsJsonDAO.fetchComentarioMejorado();
		if(lista_comentario_usuario == null)
			return ERROR;
		else
			return SUCCESS;
	}
	/*La siguiente función permite crear un nevo contenido o crear un réplica a otro usuario*/
	public String crearNuevoContenido() {
		boolean seRegistro;
		
		if(comentarioBean.getId_respuesta_a() == -1)//Nuevo Contenido			
			seRegistro = StrutsJsonDAO.insertarComentario(comentarioBean.getId_usuario(), comentarioBean.getContenido(), null);
		else//Réplica
			seRegistro = StrutsJsonDAO.insertarComentario(comentarioBean.getId_usuario(), comentarioBean.getContenido(), comentarioBean.getId_respuesta_a());	
		
		if(seRegistro)
			return SUCCESS;
		else
			return ERROR;	
	}
	/*El siguiente método permite al usuario modificar un comentario siempre y cuando el lo haya creado
	 * Ojo: la fecha se actualiza autómaticamente cuando actualizamos el comentario
	 * */
	public String modificarContenido() {
		int noCommentModificado = 0;
		try {
			noCommentModificado = StrutsJsonDAO.modificarComentario(comentarioBean.getId_comentario(), comentarioBean.getId_usuario(), comentarioBean.getContenido());
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
		boolean eliminado = false;
		System.out.println("id_Comentario recibido: " + comentarioBean.getId_comentario());
		try {
			registros_eliminados = new ArrayList<Integer>();
			eliminado = StrutsJsonDAO.eliminarComentario(comentarioBean.getId_comentario(), registros_eliminados);
			for (Integer item : registros_eliminados) {
				System.out.println("Comentarios Eliminados:" + item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		System.out.println("Registro eliminado: " + eliminado);
		if(eliminado)
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
	public ArrayList<Integer> getRegistros_eliminados() {
		return registros_eliminados;
	}
	public void setRegistros_eliminados(ArrayList<Integer> registros_eliminados) {
		this.registros_eliminados = registros_eliminados;
	}
}
