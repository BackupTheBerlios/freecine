/*
 * Created on 03-dic-2005
 *
 */
package SolsNeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */
public class AccessoDatos {
	
	private static String user;
	private static String pwd;
	private Controlador contr;
	private static Connection con;
	private static String url = "jdbc:postgresql://127.0.0.1/abd";
	private static String driver = "org.postgresql.Driver";
	
	
	
	public AccessoDatos(String u, String p, Controlador c) {
		
		user = u;
		pwd  = p;
		contr = c;
		
		System.out.println("BD: Cargando driver...");
		
		try {
			Class.forName(driver);			
		} catch (ClassNotFoundException e) {
			System.err.println("Can't load driver "+ e.getMessage());
		}
		
		System.out.println("BD: Conectando con base de datos...");
		
		try {
			con = DriverManager.getConnection(url,user,pwd);
			System.err.println("BD: Conexión realizada.");
			
		}catch(Exception e) {
			System.err.println("BD: Intento de conexión fallido");
			System.err.println(e.getMessage());
		}		
		
		
		
	}
	
	public void cerrarConexion() {
		
		System.out.println("BD: Cerrando conexión...");		
		try{
			con.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}

}
