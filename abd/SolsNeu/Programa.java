package SolsNeu;

import java.sql.*;


public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		String driver = "org.postgresql.Driver";
		
		// Ip del servidor y el nombre de base de datos
		
		String url = "jdbc:postgresql://161.116.52.21/voc0210";
		
		
		// Nombre de usuario
		String user = "voc0210";
		
		String pwd = "p4Ki(o.S";
		
		try {
					Class.forName(driver);
			
			
		} catch (ClassNotFoundException e) {
		
			System.err.println("Can't load driver "+ e.getMessage());
		}
		

	}

}