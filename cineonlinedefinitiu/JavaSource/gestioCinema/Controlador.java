package gestioCinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Controlador {
	private final String DRIVERBBDD = "org.postgresql.Driver";;
	private final String URL = "jdbc:postgresql://localhost/bbddCineOnline";
	private final String USUARI = "postgres";
	private final String PASSWORD ="s0lar1s";

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	/* FI CONEXIO BASE DE DADES */

	
	public Controlador() throws ControladorException{
		/*
		 * Constructor amb driver, ubicacio.
		 * S'utilitza quan la base de dades necessita
		 * usuari i password
		 */
		
		try{
			Class.forName(DRIVERBBDD);			
			conn = DriverManager.getConnection(URL, USUARI, PASSWORD);
			stmt = conn.createStatement();
		}catch(ClassNotFoundException e){
			System.err.println("[Controlador]: configurar -> Error: "+URL+", "+USUARI+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[Controlador]: configurar -> Error Class: "+URL+", "+USUARI+"\n"+e.getMessage());
		}catch(SQLException e1){
			System.err.println("[Controlador]: configurar -> Error: "+URL+", "+USUARI+"\n"+e1.getMessage());
			e1.printStackTrace();
			throw new ControladorException("[Controlador]: configurar -> Error SQL: "+URL+", "+USUARI+"\n"+e1.getMessage());
		}
	}
	
	public void cerrarBBDD()throws ControladorException{
		/*
		 * Tenca la conexió de la base de dades
		 */
		try{
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){
			System.err.println("[Controlador]:[cerrarBDD] -> Error close");
			e.printStackTrace();
		}catch(Exception e1){
			System.err.println("[Controlador]:[cerrarBDD] -> Error close");
			e1.printStackTrace();
		}
		
	}
	
	public Vector toVector(ResultSet rs) throws SQLException{
		/*
		 * Converteix un ResultSet a Vector de Vectors
		 */
		Vector rows = new Vector();
			while (rs.next()) {
				Vector newRow = new Vector();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					newRow.addElement(rs.getObject(i));
				}
				rows.addElement(newRow);
			}
			return rows;	
	}
	
	
	
	public Vector selectVector(String query) throws SQLException{
		/*
		 * Realitza un query concreta retornante un Vector de tuplas. I cada tupla es un vector de camps
		 */
			rs = stmt.executeQuery(query);
			return toVector(rs);
		
	}
	
	public ResultSet selectRS(String query) throws SQLException{
		/*
		 * Realitza un query concreta retornante un Resultset
		 */
			
			rs = stmt.executeQuery(query);
			return rs;
	}
	
	public void update(String query) throws SQLException{
		/*
		 * Realitzar una modificació a la base de dades concreta 
		 */
			stmt.executeUpdate(query);
	}
	
	public Vector execute(String query) throws SQLException{
		if(query.toLowerCase().startsWith("select")){
			return selectVector(query);
			
		}else{
			update(query);
			Vector v1 = new Vector();
			Vector v2 = new Vector();
			v2.add("Consulta executada correctament");
			v1.add(v2);
			return new Vector();
		}
	}
	
}

