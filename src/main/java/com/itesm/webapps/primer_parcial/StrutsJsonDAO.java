package com.itesm.webapps.primer_parcial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.struts2.util.tomcat.buf.UDecoder;

import java.sql.Date;

import com.itesm.webapps.primer_parcial.pojo.Comentario;
import com.itesm.webapps.primer_parcial.pojo.Usuario;

public class StrutsJsonDAO {
	
	// database connection method
	public static Connection conn() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/twitter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&zerodatetimebehavior=Converttonull", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Inserta un registro de tipo usuario
	 * @param nombre
	 * @param password
	 * @return devuelve el id_usuario que fue registrado
	 */
	public static int insertarUsuario(String nombre, String password) {
		String insertQuery = "INSERT INTO usuario (nombre, pass)";
		insertQuery += "VALUES(?,?)";
		int generatedKey = -1;
		try {
			
			PreparedStatement ps = conn().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nombre);
			ps.setString(2, password);
			System.out.println("Nombre: " + nombre + "  Pass: " + password);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()) {generatedKey = rs.getInt(1);}
			System.out.println("GEN KEY: " + generatedKey);
			return generatedKey;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}		
	}
	/**
	 * Inserta un registro en la tabla comentario
	 * @param id_usuario
	 * @param contenido
	 * @param id_respuesta_a determina a que mensaje se realizo la réplica
	 * @return true si el registro se inserto correctamente
	 */
	public static boolean insertarComentario(int id_usuario, String contenido, int id_respuesta_a) {
		String sql = "INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(?,?,?,?)";
		try {
			PreparedStatement statement = conn().prepareStatement(sql);
			statement.setInt(1, id_usuario);
			//statement.setDate(2, new Date(fecha_publicacion.getTime()));
			statement.setDate(2, new Date(new java.util.Date().getTime()));
			statement.setString(3, contenido);
			statement.setInt(4, id_respuesta_a);
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	/**
	 * Recupera todos los registros de una tabla especificada
	 * @nombre_tabla tabla de la que se recuperarán los registros 
	 **/
	public static ResultSet fetchTabla(String nombre_tabla) {
		ResultSet rs = null;
		try {
			Statement stmt = conn().createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + nombre_tabla);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	/**Recupera un registro de la tabla Usuario
	 * @tabla de ahí recuperaremos el registro
	 * @id identificador de un registro
	 * */
	public static Usuario getUsuarioByID(int id_usuario) throws Exception {
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(sql);
			ps.setString(1, Integer.toString(id_usuario));
			rs = ps.executeQuery();
			if(rs != null) {
				while (rs.next()) {
					usuario.setId_usuario(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setPass(rs.getString(3));
				}
				return usuario;
			}
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	/** Recupera un registro de la tabla Comentario
	 * @id
	 * */
	public static Comentario getComentarioByID(int id_comentario) throws Exception {
		ResultSet rs = null;
		Comentario comentario = new Comentario();
		String sql = "SELECT * FROM comentario WHERE id_comentario = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(sql);
			ps.setString(1, Integer.toString(id_comentario));
			rs = ps.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					comentario.setId_comentario(rs.getInt(1));
					comentario.setId_usuario(rs.getInt(2));
					comentario.setFecha_publicacion(rs.getDate(3).toLocalDate());
					comentario.setContenido(rs.getString(4));
					comentario.setId_respuesta_a(rs.getInt(5));
					
					return comentario;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * La siguiente función elimina el comentario elegido junto con sus hijos
	 * o réplicas que tenga asociados
	 * @id_comentario número de comentario que se eliminará 
	 **/
	public static boolean eliminarComentario(int id_comentario) throws Exception {
		ResultSet rs = null;		
		String sql_busqueda = "SELECT * FROM comentario WHERE id_respuesta_a = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(sql_busqueda);
			ps.setString(1, Integer.toString(id_comentario));
			rs = ps.executeQuery();
			if(rs != null) {//El comentario tiene réplicas
				while(rs.next()) {
					//Pasamos el id_comentario de los comentarios que se encuentran un nivel abajo
					eliminarComentario(rs.getInt(1));
				}
			} 
			//El comentario ya no tiene réplicas
			String sql = "DELETE FROM comentario WHERE id_comentario = ?";
			ps = conn().prepareStatement(sql);
			ps.setString(1, Integer.toString(id_comentario));
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	/**
	 * Esta función permite a un usuario que haya accedido a su cuenta, modificar un comentario
	 * de su interés, solo podrá modificar comentarios que hayan sido creados por el
	 * @param id_comentario
	 * @param id_usuario
	 * @param contenido
	 * @return
	 */
	public static int modificarComentario(int id_comentario, int id_usuario, String contenido) {
		String modify_query = "UPDATE comentario SET fecha_publicacion = ?, contenido = ?";
		modify_query += "WHERE id_comentario = ? AND id_usuario = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(modify_query);
			ps.setDate(1, new Date(new java.util.Date().getTime()));
			ps.setString(2 , contenido);
			ps.setInt(3, id_comentario);
			ps.setInt(4, id_usuario);
			int rowsAffected = ps.executeUpdate();
			
			return rowsAffected;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}		
		
	}
	
	/*Funciones para testing*/
	public static boolean dropTable(String table) {
		String sql = "DROP TABLE " + table;
		
		try {
			Statement statement = conn().createStatement();
			statement.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public static boolean create_table(String table) {
		String filePath = new File("").getAbsolutePath();
		filePath += "\\src\\test\\java\\com\\itesm\\webapps\\primer_parcial\\example\\scripts_db\\";
		filePath += "create_table_"+ table + ".sql";
		String ddl_statement = "";//Contiene la sentencia ddl para crear una tabla
		File file = new File(filePath);
		BufferedReader br;
		
		try {
			//Se obtiene la sentencia ddl del txt
			br = new BufferedReader(new FileReader(file));
			String input_string;
			while((input_string = br.readLine()) != null) {
				ddl_statement += input_string;
			}
			Statement statement = conn().createStatement();
			statement.executeUpdate(ddl_statement);
			br.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @table nombre de la tabla que será llenada por registros de prueba
	 **/
	public static boolean populate_table(String table) {
		String filePath = new File("").getAbsolutePath();
		filePath += "\\src\\test\\java\\com\\itesm\\webapps\\primer_parcial\\example\\scripts_db\\";
		filePath += "insert_"+ table + ".sql";
		BufferedReader br;	
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			String input_string;
			while((input_string = br.readLine()) != null) {
				PreparedStatement ps = conn().prepareStatement(input_string);
				ps.execute();
			}
			br.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}