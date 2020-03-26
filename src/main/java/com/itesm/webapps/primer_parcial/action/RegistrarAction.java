package com.itesm.webapps.primer_parcial.action;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Usuario;
import com.opensymphony.xwork2.ActionSupport;

public class RegistrarAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String pass;
	public String user;
	public String passC;

	public String registrarUsuario() {
		System.out.println("Entro");
		if(passC.contentEquals(pass)) {
			int registro_repetido = StrutsJsonDAO.insertarUsuario(user, pass);
			if(registro_repetido == -1)//si el registro_repetido es -1 signfica que no se pudo insertar el usuario
				return INPUT;
			return SUCCESS;
		} else
			return INPUT;
	}

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

	public String getPassC() {
		return passC;
	}

	public void setPassC(String passC) {
		this.passC = passC;
	}
}
