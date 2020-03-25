package com.itesm.webapps.primer_parcial.action;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import sun.tools.jconsole.JConsole;

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
		System.out.println("password:" +getUsuarioBean().getPass());
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
