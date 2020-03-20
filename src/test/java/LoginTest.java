import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.itesm.webapps.primer_parcial.LoginAction;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class LoginTest extends StrutsTestCase{
	@Test	
	public void testLoginAccess() throws Exception {
		request.setParameter("usuarioBean.nombre", "Carlos");
		request.setParameter("usuarioBean.pass", "cora-98");
		
		ActionProxy actionProxy = getActionProxy("/login.action");
		LoginAction action = (LoginAction) actionProxy.getAction();
			
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		String result = actionProxy.execute();
		//String r = action.validar();
		assertEquals("El método 'validar()' debio retornar " + ActionSupport.SUCCESS + " pero no lo hizo", ActionSupport.SUCCESS, result);
	}

}
