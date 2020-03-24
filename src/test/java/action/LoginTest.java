package action;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.itesm.webapps.primer_parcial.action.LoginAction;
import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class LoginTest extends StrutsTestCase{
	@Test	
	public void testLoginAccess() throws Exception {
		StrutsJsonDAO.dropTable("usuario");
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");

		request.setParameter("usuarioBean.nombre", "Juan");
		request.setParameter("usuarioBean.pass", "azul32");
		
		ActionProxy actionProxy = getActionProxy("/login.action");
		LoginAction action = (LoginAction) actionProxy.getAction();
			
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		String result = actionProxy.execute();
		//String r = action.validar();
		assertEquals("El método 'validar()' debio retornar " + ActionSupport.SUCCESS + " pero no lo hizo", ActionSupport.SUCCESS, result);
	}
	/*El siguiente test tiene por objetivo validar el correcto direccionamiento por parte del dispatcher
	 * a la opción INPUT
	 * */
	@Test	
	public void testLoginIncorrectCredentials() throws Exception {
		StrutsJsonDAO.dropTable("usuario");
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");

		request.setParameter("usuarioBean.nombre", "Juan");
		request.setParameter("usuarioBean.pass", "azul-54");
		
		ActionProxy actionProxy = getActionProxy("/login.action");
		LoginAction action = (LoginAction) actionProxy.getAction();
			
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		String result = actionProxy.execute();
		//String r = action.validar();
		assertEquals("El método 'validar()' debio retornar " + ActionSupport.INPUT + " pero no lo hizo", ActionSupport.INPUT, result);
	}

}
