package com.itesm.webapps.primer_parcial.example;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.itesm.webapps.primer_parcial.pojo.*;

import com.itesm.webapps.primer_parcial.StrutsJsonDAO;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

public class StrutsJsonDAOTest {
	List<Usuario> lista_usuario, lista_usuario_manual;
	List<Comentario> lista_comentario;
	ResultSet rs;
	
	@Before
	public void setUp() throws Exception {
		lista_usuario = new ArrayList<Usuario>();
		lista_comentario = new ArrayList<Comentario>();
		lista_usuario_manual = new ArrayList<Usuario>();
		
		lista_usuario_manual.add(new Usuario(1, "Alan", "robocop"));
//		lista_usuario_manual.add(new Usuario(2, "Carlos", "cora-98"));
//		lista_usuario_manual.add(new Usuario(3, "David", "rojo23"));
//		lista_usuario_manual.add(new Usuario(4, "Ernesto", "barcelona"));
//		lista_usuario_manual.add(new Usuario(5, "Fernando", "madrid"));	
		
		StrutsJsonDAO.dropTable("comentario");//Si la tabla comentario no existe no tomar acción
		StrutsJsonDAO.dropTable("usuario");//Si la tabla comentario no existe no tomar acción
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
	public void testCrearComentario() {
		//Creamos al usuario Mariana para que pueda responderle al Usuario David
		int generatedKey = StrutsJsonDAO.insertarUsuario("Mariana", "tec45");
		
		boolean isRegistered = StrutsJsonDAO.insertarComentario(generatedKey, "Hola David soy Mariana y ya te comente", 7);
		assertTrue("Hubo un error al insertar el COMENTARIO", isRegistered);
	}
	@Test
	public void testModificarComentarioCreadoPorElMismoUsuario() {
		//Se modificara el comentario 6 realizado por el usuario 1(Carlos)
		//Se pondrá a pueba la correcta modificación del comentario realizado por el mismo usuario 
		//que creo el comentario/publicación
		int rowsAffected = StrutsJsonDAO.modificarComentario(6, 1, "He modificado mi publicación");
		assertEquals(1, rowsAffected);
	}
	
	@Test
	public void testModificarComentarioCreadoPorOtroUsuario() {
		//Se modificara el comentario 6 realizado por el usuario 1(Carlos)
		//Se pondrá a pueba la correcta modificación del comentario realizado por el mismo usuario 
		//que creo el comentario/publicación
		int rowsAffected = StrutsJsonDAO.modificarComentario(6, 2, "He modificado mi publicación");
		assertEquals(0, rowsAffected);
	}
	
	/*Si la tabla se encuentra vacia envar msj por consola indicando que esta vacia*/
	@Test
	public void testFetchUsuario()  {
		 rs = StrutsJsonDAO.fetchTabla("usuario");
		try {
			if(rs != null) {
				while(rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId_usuario(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setPass(rs.getString(3));
					
					lista_usuario.add(usuario); 
				}
			}
			System.out.println("FETCH USUARIOS");
//			for (Usuario usuario : lista_usuario) {
//				System.out.println("id_usuario: " + usuario.getId_usuario() + "   nombre: " + usuario.getNombre() + "   Password: " + usuario.getPass());
//			}
//			assertThat(lista_usuario, hasItems(
//					new Usuario(1, "Alan", "robocop")
//					));
			//assertArrayEquals(lista_usuario, lista_usuario_manual);
			return;
		} catch(SQLException e) {
			e.printStackTrace();
			fail("Fetching Users failed");
		}
	}
	
	@Test
	public void testFetchComentario() {
		rs = StrutsJsonDAO.fetchTabla("comentario");
		try {
			if(rs != null) {
				while(rs.next()) {
					Comentario comentario = new Comentario();
					comentario.setId_comentario(rs.getInt(1));
					comentario.setId_usuario(rs.getInt(2));
					comentario.setFecha_publicacion(rs.getDate(3).toLocalDate());
					comentario.setContenido(rs.getString(4));
					comentario.setId_respuesta_a(rs.getInt(5));
					lista_comentario.add(comentario);
				}
			}	
//				System.out.println("\n\n");
//				System.out.println("FETCH COMENTARIOS");
//				for (Comentario comentario : lista_comentario) {
//					System.out.println("id_comentario: " + comentario.getId_comentario() + "   id_usuario: " + comentario.getId_usuario() + 
//							"   fecha_publicacion: " + comentario.getFecha_publicacion() + "   contenido: " + comentario.getContenido() 
//							+ "   id_respuesta_a: " + comentario.getId_respuesta_a());
//				}
				return;
		} catch(SQLException e) {
			e.printStackTrace();
			fail("Fetching Comentarios Failed");
		}
	}
	
	@Test
	public void testGetUsuarioById() throws Exception {
		int id_usuario = 1;
		Usuario usuario_actual;
		Usuario usuario_esperado = new Usuario(1, "Carlos", "cora-98");
		usuario_actual = StrutsJsonDAO.getUsuarioByID(id_usuario);
		assertEquals(usuario_esperado.getId_usuario(), usuario_actual.getId_usuario());
		assertEquals(usuario_esperado.getNombre(), usuario_actual.getNombre());
		assertEquals(usuario_esperado.getPass(), usuario_actual.getPass());
	}
	
	@Test
	public void testGetComentarioById() throws Exception {
		int id_comentario = 3;
		Comentario comentario_actual = new Comentario();
		Comentario comentario_esperado = new Comentario(3,5, LocalDate.of(2020, Month.MARCH, 17), "Hola Carlos soy Fernando -> Alan", 2);
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
