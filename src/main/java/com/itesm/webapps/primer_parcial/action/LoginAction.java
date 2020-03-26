package com.itesm.webapps.primer_parcial.action;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import sun.tools.jconsole.JConsole;

public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	public String pass;
	public String user;

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String validar() {
		System.out.println("User: " +getUser()+ " password: " +getPass());
		Usuario usuario = StrutsJsonDAO.getUsuarioByNameAndPass(getUser(), getPass());
		if(usuario.getNombre() != null)
			return SUCCESS;
		else
			return INPUT;
	}
	
	public String validarStuff() {
		return null;
	}

	
}
