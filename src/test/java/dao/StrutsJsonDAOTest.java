package dao;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import com.itesm.webapps.primer_parcial.dao.StrutsJsonDAO;
import com.itesm.webapps.primer_parcial.pojo.*;

/**
 * El siguiente conjunto de pruebas evalua el correcto funcionamiento de los métodos de la clase StrutsJsonDAOTest,
 * enfocandóse en lós métodos CRUD que interactuan con la Base de Datos.
 * 
 * Para poder ejecutar el siguiente set de pruebas, se debe crear una base de datos con el nombre 'twitter' 
 * y seleccionarla ($ use twitter)
 **/
public class StrutsJsonDAOTest {
	List<Usuario> lista_usuario, lista_usuario_manual;
	ResultSet rs;
	
	@Before
	public void setUp() throws Exception {
		lista_usuario = new ArrayList<Usuario>();
		
		StrutsJsonDAO.dropTable("comentario");
		StrutsJsonDAO.dropTable("usuario");
		StrutsJsonDAO.create_table("usuario");
		StrutsJsonDAO.populate_table("usuario");
		StrutsJsonDAO.create_table("comentario");
		StrutsJsonDAO.populate_table("comentario");
	}
	
	@Test
	public void testCrearUsuario() {
		int generatedKey = StrutsJsonDAO.insertarUsuario("Mariana", "tec45");
		assertEquals("Hubo un error al insertar el registro de USUARIO", 6, generatedKey);
	}
	
	@Test
	public void testCrearUsuarioRepetido() {
		int generatedKey = StrutsJsonDAO.insertarUsuario("Ernesto", "aqua19");
		assertEquals("No se esperaba que el id generado fuera distinto de -1",-1, generatedKey);
	}
	
	@Test
	public void testCrearNuevaPublicacion() {
		boolean isRegistered = StrutsJsonDAO.insertarComentario(2, "Estoy aprendiendo a utilizar la función de publicaciones", null);
		assertTrue("Hubo un error al crear una nueva publicación", isRegistered);
	}
	
	/*La siguiente prueba simula a un usuario respondiendo el comentario de otra persona*/
	@Test
	public void testResponderPublicacion() {
		//Creamos al usuario Mariana para que pueda responderle al Usuario David
		int generatedKey = StrutsJsonDAO.insertarUsuario("Mariana", "tec45");
		
		boolean isRegistered = StrutsJsonDAO.insertarComentario(generatedKey, "Hola David soy Mariana y ya te comente", 7);
		assertTrue("Hubo un error al responderle al mensaje con ID: " + 7, isRegistered);
	}
	@Test
	public void testModificarComentarioCreadoPorElMismoUsuario() throws Exception{
		//Se modificara el comentario 6 realizado por el usuario 1(Carlos)
		//Se pondrá a pueba la correcta modificación del comentario realizado por el mismo usuario 
		//que creo el comentario/publicación
		int rowsAffected = StrutsJsonDAO.modificarComentario(6, 1, "He modificado mi publicación");
		assertEquals(1, rowsAffected);
		
		rowsAffected = StrutsJsonDAO.modificarComentario(5, 5, "Soy una actulización del comentario anterior");
		assertEquals(1, rowsAffected);
	}
	
	@Test
	public void testModificarOtroComentarioCreadoPorElMismoUsuario() throws Exception{
		//Se modificara el comentario 6 realizado por el usuario 1(Carlos)
		//Se pondrá a pueba la correcta modificación del comentario realizado por el mismo usuario 
		//que creo el comentario/publicación
		int rowsAffected = StrutsJsonDAO.modificarComentario(7, 3, "Soy una actulización del comentario anterior");
		assertEquals(1, rowsAffected);
	}
	
	@Test
	public void testModificarComentarioCreadoPorOtroUsuario() throws Exception{
		//Se modificara el comentario 6 realizado por el usuario 1(Carlos)
		//Se pondrá a pueba la correcta modificación del comentario realizado por el mismo usuario 
		//que creo el comentario/publicación
		int rowsAffected = StrutsJsonDAO.modificarComentario(6, 2, "He modificado mi publicación");
		assertEquals(0, rowsAffected);
	}
	
	/*Si la tabla se encuentra vacia envar msj por consola indicando que esta vacia*/
	@Test
	public void testFetchUsuario()  {
		 List<Usuario> lista_usuario = StrutsJsonDAO.fetchUsuario();
		 for (Usuario usuario : lista_usuario) {
			System.out.println(usuario.getNombre());
		}
		 assertNotEquals("La 'lista de usuario' es nula pero no debería serlo",
					null, lista_usuario);
	}
	
	@Test
	public void testFetchComentario() {
		List<Comentario> lista_comentario =  StrutsJsonDAO.fetchComentario();
		 for (Comentario comentario : lista_comentario) {
				System.out.println(comentario.getContenido());
			}
		assertNotEquals("La 'lista de comentarios' es nula pero no debería serlo",
				null, lista_comentario);
	}
	
	@Test
	public void testFetchComentarioMejorado() {
		List<ComentarioUsuario> lista_comentario_usuario =  StrutsJsonDAO.fetchComentarioMejorado();
		 for (ComentarioUsuario comentario_usuario : lista_comentario_usuario) {
				System.out.println(comentario_usuario.getContenido());
			}
		assertNotEquals("La 'lista de COMENTARIO y USUARIO' es nula pero no debería serlo",
				null, lista_comentario_usuario);
	}
	
	@Test
	public void testGetUsuarioById() throws Exception {
		int id_usuario = 1;
		Usuario usuario_actual;
		Usuario usuario_esperado = new Usuario(1, "Juan", "azul32");
		usuario_actual = StrutsJsonDAO.getUsuarioByID(id_usuario);
		assertEquals(usuario_esperado.getId_usuario(), usuario_actual.getId_usuario());
		assertEquals(usuario_esperado.getNombre(), usuario_actual.getNombre());
		assertEquals(usuario_esperado.getPass(), usuario_actual.getPass());
	}
	
	@Test
	public void testGetComentarioById() throws Exception {
		int id_comentario = 3;
		Comentario comentario_actual = new Comentario();
		Comentario comentario_esperado = new Comentario(3,5, LocalDate.of(2020, Month.MARCH, 17), "Hola Juan soy Fernando", 2);
		comentario_actual = StrutsJsonDAO.getComentarioByID(id_comentario);
		assertEquals(comentario_esperado.getId_comentario(), comentario_actual.getId_comentario());
		assertEquals(comentario_esperado.getId_usuario(), comentario_actual.getId_usuario());
		assertEquals(comentario_esperado.getFecha_publicacion(), comentario_actual.getFecha_publicacion());
		assertEquals(comentario_esperado.getContenido(), comentario_actual.getContenido());
		assertEquals(comentario_esperado.getId_respuesta_a(), comentario_actual.getId_respuesta_a());
	}
		
	@Test
	public void testEliminarComentario() throws Exception{
		int id_comentario = 5;
		boolean eliminado = StrutsJsonDAO.eliminarComentario(id_comentario);
		assertTrue("The message with ID: " + id_comentario +" was NOT deleted",eliminado);
	}

}
