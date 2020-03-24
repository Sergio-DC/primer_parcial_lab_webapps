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
	
	@Test
	public void testCrearNuevoContenido() throws Exception{
		StrutsJsonDAO.dropTable("comentario");
		StrutsJsonDAO.dropTable("usuario");
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");
		StrutsJsonDAO.create_table("comentario");
		StrutsJsonDAO.populate_table("comentario");
		//Al ponerlo como -1 estamos simulando una publicación nueva, es decir que no
		//es una réplica
		String id_respuesta_a = "-1";
		request.setParameter("comentarioBean.id_usuario", "4");
		request.setParameter("comentarioBean.contenido", "Soy un contenido de testing");
		request.setParameter("comentarioBean.id_respuesta_a", id_respuesta_a);
		
		ActionProxy actionProxy = getActionProxy("/crear_nuevo_contenido.action");
		PublicacionesAction action = (PublicacionesAction) actionProxy.getAction();
		
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		assertNotNull("El objecto 'actionProxy' es nulo pero no debería serlo", actionProxy);
		String result = actionProxy.execute();
		assertEquals("El método 'crearNuevoContenido()' debio retornar " + 
		ActionSupport.SUCCESS + " pero no lo hizo", ActionSupport.SUCCESS, result);
	}
	
	@Test
	public void testModificarPublicacion() throws Exception{
		StrutsJsonDAO.dropTable("comentario");
		StrutsJsonDAO.dropTable("usuario");
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");
		StrutsJsonDAO.create_table("comentario");
		StrutsJsonDAO.populate_table("comentario");
		
		request.setParameter("comentarioBean.id_comentario", "7");
		request.setParameter("comentarioBean.id_usuario", "3");
		request.setParameter("comentarioBean.contenido", "Soy una actulización del comentario anterior");
	
		ActionProxy actionProxy = getActionProxy("/modificar_contenido.action");
		PublicacionesAction action = (PublicacionesAction) actionProxy.getAction();
		
		assertNotNull("El objecto 'action' es nulo pero no debería serlo", action);
		String result = actionProxy.execute();
		
		assertEquals("El método 'modificarPublicacion()' debio retornar " + 
		ActionSupport.SUCCESS + " pero no lo hizo", ActionSupport.SUCCESS, result);
	}

}
