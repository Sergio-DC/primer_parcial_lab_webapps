package com.itesm.webapps.primer_parcial;

import com.itesm.webapps.primer_parcial.pojo.Usuario;
import com.opensymphony.xwork2.ActionSupport;

public class RegistrarAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public Usuario usuarioBean;
	public String confirmar_pass;

	public String registrarUsuario() {		
		if(confirmar_pass.contentEquals(getUsuarioBean().getPass())) {
			int registro_repetido = StrutsJsonDAO.insertarUsuario(getUsuarioBean().getNombre(), getUsuarioBean().getPass());
			if(registro_repetido == -1)//si el registro_repetido es -1 signfica que no se pudo insertar el usuario
				return INPUT;
			return SUCCESS;
		} else
			return INPUT;
	}

	public Usuario getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public String getConfirmar_pass() {
		return confirmar_pass;
	}

	public void setConfirmar_pass(String confirmar_pass) {
		this.confirmar_pass = confirmar_pass;
	}
	
}
