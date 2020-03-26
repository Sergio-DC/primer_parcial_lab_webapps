package com.itesm.webapps.primer_parcial.action;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import sun.tools.jconsole.JConsole;

import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private SessionMap<String,Object> sessionMap;
	public String pass;
	public String user;
	public String autenticado = "false";//Indica si algún usuario se autenticado

	public String getAutenticado() {
		return autenticado;
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

	public String validar() {
		if(sessionMap!=null){
			sessionMap.invalidate();
		}
		Usuario usuario = StrutsJsonDAO.getUsuarioByNameAndPass(getUser(), getPass());
		System.out.println(usuario.getId_usuario());
		if(usuario.getNombre() != null) {
			this.autenticado = "true";
			//System.out.println(user);
			sessionMap.put("user",user);
			sessionMap.put("id",(Integer)usuario.getId_usuario());

			return SUCCESS;
		} else {
			this.autenticado = "false";
			return INPUT;
		}
	}
	
	public String validarStuff() {
		return null;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap)map;
	}
}
