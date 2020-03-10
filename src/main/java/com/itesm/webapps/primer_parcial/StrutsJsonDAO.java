package com.itesm.webapps.primer_parcial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StrutsJsonDAO {
	
	// database connection method
	public static Connection conn() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/twitter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// fetch the country list
	public static ResultSet fetchCountry() {
		ResultSet rs = null;
		try {
			Statement stmt = conn().createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT(country) AS country FROM countrymaster");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// fetch the state list
	public static ResultSet fetchState(String country) {
		ResultSet rs = null;
		try {
			Statement stmt = conn().createStatement();
			String q = "SELECT state FROM countrymaster WHERE country='" + country + "'";
			rs = stmt.executeQuery(q);
			System.out.println(q);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}