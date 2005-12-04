/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */

package SolsNeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccessoDatos {
	
	private static String user;
	private static String pwd;
	private Controlador contr;
	private static Connection con;
	private static String url = "jdbc:postgresql://127.0.0.1/abd";
	private static String driver = "org.postgresql.Driver";
	
	
	/**
	 * Constructor de Clase AccessoDatos
	 * 
	 * @param u Nombre de usuario
	 * @param p Contraseña
	 * @param c Objeto Controlador
	 */
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
	
	/**
	 * Función cerrar conexión.
	 *
	 */
	public void cerrarConexion() {
		
		System.out.println("BD: Cerrando conexión...");		
		try{
			con.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
	
	public String datosCliente(){
		String r,query;
		ResultSet res;

		// VISTA: vista_dades_client
		// ABEL: Aquesta vista encara no funciona bé!!!
		// query = "select * from vista_dades_client";
		query = "select * from client where login=CURRENT_USER";
		res = execQuery(con,query);
		r ="";
		
		try {
			while(res.next()){
				
				/*
				System.out.print(res.getString(1));
				System.out.print(" | ");
				System.out.print(res.getString( 2 ));
				System.out.println(""); */
				
				r = r.concat(res.getString(1));
				r = r.concat(" | ");
				r = r.concat(res.getString( 2 ));
				r = r.concat(" | ");
				r = r.concat(res.getString( 3 ));
				r = r.concat(" | ");
				r = r.concat(res.getString(4));
				r = r.concat(" | ");
				r = r.concat(res.getString(5));
				r = r.concat(" | ");
				r = r.concat(res.getString(6));
				r = r.concat(" | ");
				r = r.concat(res.getString(7));
				r = r.concat(" | ");
				r = r.concat(res.getString(8));
				r = r.concat(" | ");
				r = r.concat(res.getString(9));
				r = r.concat(" | ");
				r = r.concat(res.getString(10));
				r = r.concat(" | ");
				r = r.concat(res.getString(11));
			}
			
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
	}
	
	public String productosDisponibles(){
		String r,query;
		ResultSet res;
		query = "select * from vista_productes_disponibles_client";
		res = execQuery(con,query);
		r ="";		
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+" | ");
				r = r.concat(res.getString(2)+" | ");
				r = r.concat(res.getString(3)+" | ");
				r = r.concat(res.getString(4)+" | ");
				r = r.concat(res.getString(5)+" | ");
				r = r.concat(res.getString(6)+" | ");
				r = r.concat(res.getString(7)+"\n");
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
	}
	
	public String productosAlquiladosCliente(){
		String r,query;
		ResultSet res;
		query = "select * from vista_productes_llogats_client";
		res = execQuery(con,query);
		r ="";		
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+" | ");
				r = r.concat(res.getString(2)+" | ");
				r = r.concat(res.getString(3)+" | ");
				r = r.concat(res.getString(4)+" | ");
				r = r.concat(res.getString(5)+" | ");
				r = r.concat(res.getString(6)+" | ");
				r = r.concat(res.getString(7)+" | ");
				r = r.concat(res.getString(8)+"\n");
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
	}	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param con Conexión con la base de datos. Esta tiene que estar ya establecida y no ser NULL
	 * @param query Comando SQL que queremos ejecutar
	 * @return ResultSet que contiene todas las filas con cadauna de sus columnas resultantes de aplicar la instrucción SQL en la Base de datos.
	 * 
	 * 
	 */
	
	static ResultSet execQuery(Connection con, String query) {
		
		try {
			Statement stmt = con.createStatement();
			System.out.println("[DEBUG] DB: "+query);
			return(stmt.executeQuery(query));
		}catch(SQLException e) {
			System.err.println("Query failed - "+e.getMessage());
			return(null);
			
		}
	}
	
	// Fin de función execQuery
	
	/**
	 * @param res ResultSet provinent de l'execució d'una comanda SQL 
	 */	
	static void printResults( ResultSet res) {
		
		System.out.println(" Columna1 | Columna2");
		System.out.println("---------+-------------------------------------------------------");
		
		try {
			
			while (res.next()) {
				
				System.out.print(res.getString(1));
				System.out.print(" | ");
				System.out.print(res.getString( 2 ));
				System.out.println("");
			}
			
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
	}

}
