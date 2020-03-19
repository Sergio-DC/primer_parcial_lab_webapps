package com.itesm.webapps.primer_parcial.example;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public void dummyTest() {
		
	}
//	@Test
//	public void readFile() {
//		String filePath = new File("").getAbsolutePath();
//		filePath += "\\src\\test\\java\\com\\itesm\\webapps\\primer_parcial\\example\\scripts_db\\";
//		filePath += "create_table_usuario.sql";
//		File file = new File(filePath);
//		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader(file));
//			String ddl_content;
//			while((ddl_content = br.readLine()) != null)	
//			br.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			fail("Error al encontrar el archivo");
//		} catch(IOException ioe) {
//			ioe.printStackTrace();
//			fail("Error con los mecanismos de I/O");
//		}		
//		return;
//	}
	

//	@After
//	public void tearDown() throws Exception {
//	}
//	/*Si la tabla se encuentra vacia envar msj por consola indicando que esta vacia*/
//	@SuppressWarnings("unchecked")
//	@Test
//	public void testFetchUsuario()  {
//		 rs = StrutsJsonDAO.fetchUsuario();
//		try {
//			if(rs != null) {
//				while(rs.next()) {
//					Usuario usuario = new Usuario();
//					usuario.setId_usuario(rs.getInt(1));
//					usuario.setNombre(rs.getString(2));
//					usuario.setPass(rs.getString(3));
//					
//					lista_usuario.add(usuario); 
//				}
//			}
//			System.out.println("FETCH USUARIOS");
//			for (Usuario usuario : lista_usuario) {
//				System.out.println("id_usuario: " + usuario.getId_usuario() + "   nombre: " + usuario.getNombre() + "   Password: " + usuario.getPass());
//			}
////			assertThat(lista_usuario, hasItems(
////					new Usuario(1, "Alan", "robocop")
////					));
//			//assertArrayEquals(lista_usuario, lista_usuario_manual);
//			return;
//		} catch(SQLException e) {
//			e.printStackTrace();
//			fail("Fetching Users failed");
//		}
//	}
	
//	@Test
//	public void testFetchComentario() {
//		rs = StrutsJsonDAO.fetchComentario();
//		try {
//			if(rs != null) {
//				while(rs.next()) {
//					Comentario comentario = new Comentario();
//					comentario.setId_comentario(rs.getInt(1));
//					comentario.setId_usuario(rs.getInt(2));
//					comentario.setFecha_publicacion(rs.getDate(3));
//					comentario.setContenido(rs.getString(4));
//					comentario.setId_respuesta_a(rs.getInt(5));
//					lista_comentario.add(comentario);
//				}
//			}	
//				System.out.println("\n\n");
//				System.out.println("FETCH COMENTARIOS");
//				for (Comentario comentario : lista_comentario) {
//					System.out.println("id_comentario: " + comentario.getId_comentario() + "   id_usuario: " + comentario.getId_usuario() + 
//							"   fecha_publicacion: " + comentario.getFecha_publicacion() + "   contenido: " + comentario.getContenido() 
//							+ "   id_respuesta_a: " + comentario.getId_respuesta_a());
//				}
//				return;
//		} catch(SQLException e) {
//			e.printStackTrace();
//			fail("Fetching Comentarios Failed");
//		}
//	}
	
//	@Test
//	public void testGetUsuarioById() throws Exception {
//		int id_usuario = 1;
//		Usuario usuario = new Usuario();
//		rs = StrutsJsonDAO.getUsuarioByID(id_usuario);
//		
//		if(rs != null) {
//			while(rs.next()) {
//				
//				usuario.setId_usuario(rs.getInt(1));
//				usuario.setNombre(rs.getString(2));
//				usuario.setPass(rs.getString(3));					
//			}
//			System.out.println("\n\n");
//			System.out.println("USUARIO POR ID");
//			System.out.println("id_usuario: " + usuario.getId_usuario() + "   nombre: " + usuario.getNombre() 
//			+ "   Passord: " + usuario.getPass());
//			return;
//		}
//		
//		fail("Getting User by ID Failed");
//		
//	}
	
//	@Test
//	public void testGetComentarioById() throws Exception {
//		int id_comentario = 3;
//		Comentario comentario = new Comentario();
//		rs = StrutsJsonDAO.getComentarioByID(id_comentario);
//		
//		if(rs != null) {
//			while(rs.next()) {
//				
//				comentario.setId_comentario(rs.getInt(1));
//				comentario.setId_usuario(rs.getInt(2));		
//				comentario.setFecha_publicacion(rs.getDate(3));
//				comentario.setContenido(rs.getString(4));
//				comentario.setId_respuesta_a(rs.getInt(5));
//			}
//			System.out.println("\n\n");
//			System.out.println("COMENTARIO POR ID");
//			System.out.println("id_comentario: " + comentario.getId_comentario() + "   id_usuario: " + comentario.getId_usuario() + "   fecha_publicacion: " + comentario.getFecha_publicacion() + "   contenido: " + comentario.getContenido() + "   id_respuesta_a: " + comentario.getId_respuesta_a());
//			return;
//		}
//		
//		fail("Getting User by ID Failed");
//		
//	}
		
	@Test
	public void testEliminarComentarioMedio() throws Exception{
		int id_comentario = 5;
		boolean eliminado = StrutsJsonDAO.eliminarComentario(id_comentario);
		assertEquals("The message with ID: " + id_comentario +" was NOT deleted",true, eliminado);
	}

}
