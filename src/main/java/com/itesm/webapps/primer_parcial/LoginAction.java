package com.itesm.webapps.primer_parcial;

import com.itesm.webapps.primer_parcial.pojo.Usuario;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	public Usuario usuarioBean;
	
	public Usuario getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public String validar() {
		Usuario usuario = StrutsJsonDAO.getUsuarioByNameAndPass(getUsuarioBean().getNombre(), getUsuarioBean().getPass());
		if(usuario.getNombre() != null)
			return SUCCESS;
		else
			return INPUT;
	}
	
	public String validarStuff() {
		return null;
	}

	
}
