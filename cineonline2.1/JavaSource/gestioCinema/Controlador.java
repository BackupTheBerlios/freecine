package gestioCinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Controlador {
	private final String DRIVERBBDD = "org.postgresql.Driver";;
	private final String URL = "jdbc:postgressql//localhost:8080/bbddCineOnline";
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
	
	public Vector toVector(ResultSet rs) throws ControladorException{
		/*
		 * Converteix un ResultSet a Vector de Vectors
		 */
		Vector rows = new Vector();
		try{
			while (rs.next()) {
				Vector newRow = new Vector();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					newRow.addElement(rs.getObject(i));
				}
				rows.addElement(newRow);
			}
			return rows;
			
		}catch(SQLException e){
			System.err.println("[Controlador]:[toVector]");
			e.printStackTrace();
			throw new ControladorException("[Controlador]:[toVector]");
		}
	}
	public Vector selectVector(String query) throws ControladorException{
		/*
		 * Realitza un query concreta
		 */
		
		try{			
		
			rs = stmt.executeQuery(query);
			return toVector(rs);
			
		}catch(SQLException e){
			System.err.println("[Controlador]:[selectVector]"+ query);
			e.printStackTrace();
			throw new ControladorException("[Controlador]:[selectVector]"+ query+"\n"+e.getMessage());
		}
	}
	
	public ResultSet selectRS(String query) throws ControladorException{
		/*
		 * Realitza un query concreta
		 */
		
		try{			
		
			rs = stmt.executeQuery(query);
			return rs;
			
		}catch(SQLException e){
			System.err.println("[Controlador]:[selectRS]"+ query);
			e.printStackTrace();
			throw new ControladorException("[Controlador]:[selectRS] -> error:"+ query+"\n"+e.getMessage());
		}
	}
	
	public void update(String query) throws ControladorException{
		/*
		 * Realitzar una modificació a la base de dades concreta 
		 */
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println("[Controlador]:[update] ->error:"+ query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[Controlador]:[update] ->error:"+ query+"\n"+e.getMessage());
		}
	}
	
	public Vector execute(String query) throws ControladorException{
		if(query.toLowerCase().startsWith("select")){
			return selectVector(query);
			
		}else{
			update(query);
			return new Vector();
		}
	}
}
