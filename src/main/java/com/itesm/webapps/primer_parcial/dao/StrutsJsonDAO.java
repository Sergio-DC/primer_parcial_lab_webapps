package com.itesm.webapps.primer_parcial.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

import com.itesm.webapps.primer_parcial.pojo.Comentario;
import com.itesm.webapps.primer_parcial.pojo.ComentarioUsuario;
import com.itesm.webapps.primer_parcial.pojo.Usuario;

public class StrutsJsonDAO {
	//private static String filePath = new File("").getAbsolutePath();
	
	/**
	 * El siguiete método permite conectarnos a la base de datos 'twitter' 
	 * @return devuelve la Conexión a la BD para poder realizar consultas o manipulación de datos a esta
	 * @throws Exception
	 */
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
	 * Inserta un registro de tipo usuario en la BD, indicando el 'nombre' y 'password'
	 * @param nombre nombre del usuario
	 * @param password 
	 * @return devuelve el id_usuario registrado, en caso de no poder registrarse devuelve -1
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
	 * Crea un nuevo comentario/publicacion, puede ser nueva o una réplica a un comentario
	 * @param id_usuario identificador del usuario que hará la réplica
	 * @param contenido es el mensaje que será publicado
	 * @param id_respuesta_a determina a que publicación se realizo la réplica, si se deja NULL se interpreta como una nueva publicación
	 * @return true si el registro se inserto correctamente
	 */
	public static boolean insertarComentario(int id_usuario, String contenido, Integer id_respuesta_a) {
		String sql = "INSERT INTO comentario(id_usuario, fecha_publicacion, contenido, id_respuesta_a) VALUES(?,?,?,?)";
		try {
			PreparedStatement statement = conn().prepareStatement(sql);
			statement.setInt(1, id_usuario);
			//statement.setDate(2, new Date(fecha_publicacion.getTime()));
			statement.setDate(2, new Date(new java.util.Date().getTime()));
			statement.setString(3, contenido);
			if (id_respuesta_a != null)
			   statement.setInt(4, id_respuesta_a);
			else
			   statement.setNull(4, Types.INTEGER);
			statement.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
	/**
	 * Recupera todos los registros de la tabla USUARIO
	 * @return una Lista de COMENTARIOS
	 **/
	public static List<Usuario> fetchUsuario() {
		ResultSet rs = null;
		List<Usuario> lista_usuario = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuario";
		try {
			Statement stmt = conn().createStatement();
			rs = stmt.executeQuery(sql);			
			if(rs != null) {
				while(rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId_usuario(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setPass(rs.getString(3));
					lista_usuario.add(usuario);
				}
				return lista_usuario;
			}	
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	/**
	 * Recupera todos los registros de la tabla COMENTARIO
	 * @return una Lista de COMENTARIOS
	 **/
	public static List<Comentario> fetchComentario() {
		ResultSet rs = null;
		List<Comentario> lista_comentario = new ArrayList<Comentario>();
		String sql = "SELECT * FROM comentario";
		try {
			Statement stmt = conn().createStatement();
			rs = stmt.executeQuery(sql);			
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
				return lista_comentario;
			}	
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	/**
	 * Recupera todos los registros de la tabla COMENTARIO y
	 * USUARIO, no se recuperan todos los campos, solo los necesario
	 * para las publicaciones
	 * @return una Lista de COMENTARIOS con Usuarios
	 **/
	public static List<ComentarioUsuario> fetchComentarioMejorado() {
		ResultSet rs = null;
		List<ComentarioUsuario> lista_comentario_usuario = new ArrayList<ComentarioUsuario>();
		String sql = "select usuario.id_usuario, usuario.nombre, comentario.id_comentario, ";
		sql +=	"comentario.fecha_publicacion, comentario.contenido, comentario.id_respuesta_a ";
		sql +=	"from comentario JOIN usuario ";
		sql +=	"ON usuario.id_usuario = comentario.id_usuario";
		try {
			Statement stmt = conn().createStatement();
			rs = stmt.executeQuery(sql);			
			if(rs != null) {
				while(rs.next()) {
					ComentarioUsuario comentario_usuario = new ComentarioUsuario();
					
					comentario_usuario.setId_usuario(rs.getInt(1));
					comentario_usuario.setNombre(rs.getString(2));
					comentario_usuario.setId_comentario(rs.getInt(3));
					comentario_usuario.setFecha_publicacion(rs.getDate(4).toLocalDate());
					comentario_usuario.setContenido(rs.getString(5));
					comentario_usuario.setId_respuesta_a(rs.getInt(6));
					lista_comentario_usuario.add(comentario_usuario);
				}
				return lista_comentario_usuario;
			}	
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	/**
	 * Recupera un registro de la tabla Usuario indicando el id_usuario
	 * @id_usuario identificador del usuario
	 * @return devuelve el registro encontrado en formato de objeto USUARIO
	 **/
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
	
	/**
	 * Recupera un registro de la tabla USUARIO indicando nombre y password del usuario
	 * Este método nos asistirá en el escenario del Login
	 * @param nombre nombre del usuario
	 * @param pass password del usuario
	 * @return devuelve el registro encontrado en formato de objeto USUARIO
	 */
	public static Usuario getUsuarioByNameAndPass(String nombre, String pass) {
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		String sql = "SELECT * FROM usuario WHERE nombre = ? AND pass = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs != null) {
				while (rs.next()) {
					usuario.setId_usuario(rs.getInt(1));
					usuario.setNombre(rs.getString(2));
					usuario.setPass(rs.getString(3));
				}
				return usuario;
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	/** Recupera un registro de la tabla COMENTARIO indicando el identificador del comentario
	 * @id_comentario es el identificador único que tiene asociado un comentario
	 * @return devuelve el registro encontrado en formato de objeto COMENTARIO
	 **/
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
	 * Esta función permite a un usuario que haya accedido a su cuenta, modificar un comentario
	 * de su interés, solo podrá modificar comentarios que hayan sido creados por el
	 * Ojo: La fecha se actualiza automáticamente
	 * @param id_comentario
	 * @param id_usuario
	 * @param contenido es el texto/contenido que tendrá la publicación
	 * @return el número filas que fueron modificada | 0 si no se modifico algún registro
	 */
	public static int modificarComentario(int id_comentario, int id_usuario, String contenido) throws Exception{
		String modify_query = "UPDATE comentario SET fecha_publicacion = ?, contenido = ? ";
		modify_query += "WHERE id_comentario = ? AND id_usuario = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(modify_query);
			ps.setDate(1, new Date(new java.util.Date().getTime()));
			ps.setString(2 , contenido);
			ps.setInt(3, id_comentario);
			ps.setInt(4, id_usuario);
			int rowsAffected = ps.executeUpdate();
			System.out.println("rows: " + rowsAffected);
			return rowsAffected;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}				
	}
	
	/**
	 * La siguiente función elimina el comentario elegido con sus hijos o réplicas que tenga relacionadas
	 * @id_comentario número de comentario que será eliminado
	 * @return true si el comentario ha sido eliminado
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
	/*Funciones para testing*/
	
	/**
	 * Esta función desecha una tabla
	 * @param table nombre de la tabla, opciones (usuario|comentario)
	 * @return true si se ha eliminado la tabla de la Base de datos
	 */
	public static boolean dropTable(String table) {
		String sql = "DROP TABLE IF EXISTS " + table;
		
		try {
			Statement statement = conn().createStatement();
			statement.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * Esta función crea una tabla, solo hay 2 opciones de tabla
	 * @param table nombre de la tabla, opciones (usuario|comentario)
	 * @return true si la tabla se creo correctamente
	 */
	public static boolean create_table(String table) {
		String filePath = new File("").getAbsolutePath();
		filePath += "\\src\\test\\java\\scripts_db_test\\";
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
	 * Llena una tabla de registros de un archivo de prueba sql
	 * @table nombre de la tabla que será llenada, opciones (usuario|comentario)
	 * @return true si la tabla se lleno sin errores
	 **/
	public static boolean populate_table(String table) {
		String filePath = new File("").getAbsolutePath();
		filePath += "\\src\\test\\java\\scripts_db_test\\";
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