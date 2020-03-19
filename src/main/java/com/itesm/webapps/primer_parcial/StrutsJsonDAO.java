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

	// fetch the country list
	public static ResultSet fetchUsuario() {
		ResultSet rs = null;
		try {
			Statement stmt = conn().createStatement();
			rs = stmt.executeQuery("SELECT * FROM usuario");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// fetch the state list
	public static ResultSet fetchComentario() {
		ResultSet rs = null;
		try {
			Statement stmt = conn().createStatement();
			String q = "SELECT * FROM comentario";
			rs = stmt.executeQuery(q);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet getUsuarioByID(int id_usuario) throws Exception {
		ResultSet rs = null;
		String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(sql);
			ps.setString(1, Integer.toString(id_usuario));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet getComentarioByID(int id_comentario) throws Exception {
		ResultSet rs = null;
		String sql = "SELECT * FROM comentario WHERE id_comentario = ?";
		try {
			PreparedStatement ps = conn().prepareStatement(sql);
			ps.setString(1, Integer.toString(id_comentario));
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * La siguiente función elimina el comentario elegido junto con sus hijos
	 * o réplicas que tenga asociados
	 * @id_comentario número de comentario que se eliminará 
	 **/
	public static boolean eliminarComentario(int id_comentario) throws Exception {
		ResultSet rs = null;
		//String sql = "DELETE FROM comentario WHERE id_comentario = ?;";
		
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
			System.out.println(":) " +  ddl_statement);
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
		
		//Se obtiene las inserciones del txt
		try {
			br = new BufferedReader(new FileReader(filePath));
			
			String input_string;
			while((input_string = br.readLine()) != null) {
				System.out.println(input_string);
				PreparedStatement ps = conn().prepareStatement(input_string);
				ps.execute();
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}