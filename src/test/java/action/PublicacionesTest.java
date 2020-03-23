package action;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.struts2.StrutsTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itesm.webapps.primer_parcial.action.PublicacionesAction;
import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.Comentario;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class PublicacionesTest extends StrutsTestCase{

	@Test
	public void testCargarContenidoCreado() throws Exception{
		StrutsJsonDAO.dropTable("comentario");
		StrutsJsonDAO.dropTable("usuario");
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");
		StrutsJsonDAO.create_table("comentario");
		StrutsJsonDAO.populate_table("comentario");
	
		ActionProxy actionProxy = getActionProxy("/cargar_contenido.action");
		PublicacionesAction action = (PublicacionesAction) actionProxy.getAction();
		
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		String result = actionProxy.execute();
		assertEquals("El método 'mostrarPublicaciones()' debio retornar " + 
		ActionSupport.SUCCESS + " pero no lo hizo", ActionSupport.SUCCESS, result);
	}

}
