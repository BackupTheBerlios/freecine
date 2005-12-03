package SolsNeu;

import java.sql.*;


/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */

public class Programa {

	// Esto pa que coño es?
	public Programa() {
		super();
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String driver = "org.postgresql.Driver";
		
		// Ip del servidor y el nombre de base de datos
		
		//String url = "jdbc:postgresql://161.116.52.21/voc0210";
		
		String url = "jdbc:postgresql://127.0.0.1/abd";
		
			
		System.setProperty("java.net.preferIPv4Stack","true");
		System.setProperty("java.net.preferIPv6Addresses","false");
		
		
		// Nombre de usuario
		String user = "victor";
		String pwd = "";
		
		//System.out.println(System.getProperty("user.name"));
		
		
		System.out.println("Cargando driver...");
		
		try {
			Class.forName(driver);			
		} catch (ClassNotFoundException e) {
			System.err.println("Can't load driver "+ e.getMessage());
		}
		
		System.out.println("Conectando con base de datos...");
		
		try {
			Connection con = DriverManager.getConnection(url,user,pwd);
			System.err.println("Conexión realizada.");
			
			String query = "Select nom,cognom1 from client";
			
			ResultSet result = execQuery(con,query);
			printResults(result);
			con.close();
		}catch(Exception e) {
			System.err.println("Intento de conexión fallido");
			System.err.println(e.getMessage());
		}		
	}
	// Fin de Main
	
	
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
			System.out.println(query);
			return(stmt.executeQuery(query));
		}catch(SQLException e) {
			System.err.println("Query failed - "+e.getMessage());
			return(null);
			
		}
	}
	
	// Fin de función execQuery
	
	
	
	
	//@param res ResultSet provinent de l'execució d'una comanda SQL
	
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