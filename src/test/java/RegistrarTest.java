import org.apache.struts2.StrutsTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itesm.webapps.primer_parcial.RegistrarAction;
import com.itesm.webapps.primer_parcial.StrutsJsonDAO;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class RegistrarTest extends StrutsTestCase {
	/*
	 * El siguiente test pone a prueba la alta de un nuevo usuario, verificando que
	 * en caso de que todos los campos esten llenados correctamente, el usuario se registre
	 * en la BD y el dispatcher redirija al jsp 'usuario_registrado.jsp'
	 **/
	@Test
	public void testRegistarUsuario() throws Exception{
		//Crear tablas y llenarlas de registros de prueba
		StrutsJsonDAO.dropTable("comentario");//Si la tabla comentario no existe no tomar acción
		StrutsJsonDAO.dropTable("usuario");//Si la tabla comentario no existe no tomar acción
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");
		StrutsJsonDAO.create_table("comentario");
		StrutsJsonDAO.populate_table("comentario");
		
		
		request.setParameter("usuarioBean.nombre", "Alondra");
		request.setParameter("usuarioBean.pass", "pass123");
		request.setParameter("confirmar_pass", "pass123");
		
		ActionProxy actionProxy = getActionProxy("/registrar.action");
		RegistrarAction action = (RegistrarAction) actionProxy.getAction();
		
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		String result = actionProxy.execute();
		assertEquals("El método 'registrarUsuario()' debio retornar " + 
		ActionSupport.SUCCESS + " pero no lo hizo", ActionSupport.SUCCESS, result);
	}
	/**
	 * El siguiente test pone a prueba la alta de un usuario pero en esta ocasión
	 * se dará de alta un nombre de usuario ya existente, por lo tanto el dispatcher 
	 * redirigirá al jsp 'registrar.jsp'
	 */
	@Test
	public void testRegistrarUsuarioRepetido() throws Exception {
		//Crear tablas y llenarlas de registros de prueba
			StrutsJsonDAO.dropTable("comentario");//Si la tabla comentario no existe no tomar acción
			StrutsJsonDAO.dropTable("usuario");//Si la tabla comentario no existe no tomar acción
			StrutsJsonDAO.create_table("usuario");
			StrutsJsonDAO.populate_table("usuario");
			StrutsJsonDAO.create_table("comentario");
			StrutsJsonDAO.populate_table("comentario");
			
			
			request.setParameter("usuarioBean.nombre", "Fernando");
			request.setParameter("usuarioBean.pass", "hola");
			request.setParameter("confirmar_pass", "hola");
			
			ActionProxy actionProxy = getActionProxy("/registrar.action");
			RegistrarAction action = (RegistrarAction) actionProxy.getAction();
			
			assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
			String result = actionProxy.execute();
			assertEquals("El método 'registrarUsuario()' debio retornar " + 
			ActionSupport.INPUT + " pero no lo hizo", ActionSupport.INPUT, result);
	}
	
	/*El siguiente test se asegura de que en caso de que el usuario quiera registrarse
	 *  con un nombre de usuario valido pero el 'password' y el 'password de confirmación' sean
	 *  distintos, el dispatcher lo redireccionará al jsp 'registrar.jsp'
	 * */
	@Test
	public void testRegistarUsuarioConfirmarConPasswordDistinto() throws Exception{
		StrutsJsonDAO.dropTable("comentario");
		StrutsJsonDAO.dropTable("usuario");
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");
		StrutsJsonDAO.create_table("comentario");
		StrutsJsonDAO.populate_table("comentario");
		
		request.setParameter("usuarioBean.nombre", "Valeria");
		request.setParameter("usuarioBean.pass", "dummy");
		request.setParameter("confirmar_pass", "dummi");
		
		ActionProxy actionProxy = getActionProxy("/registrar.action");
		RegistrarAction action = (RegistrarAction) actionProxy.getAction();
		
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		String result = actionProxy.execute();
		assertEquals("El método 'registrarUsuario()' debio retornar " + 
		ActionSupport.INPUT + " pero no lo hizo", ActionSupport.INPUT, result);
	}

}
