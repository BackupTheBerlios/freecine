package gestioCinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Controlador {
	private String driverBBDD;
	private String url;
	private String usuari;
	private String password;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	/* FI CONEXIO BASE DE DADES */

	
	public Controlador(){
		/*
		 * Constructor amb driver, ubicacio.
		 * S'utilitza quan la base de dades necessita
		 * usuari i password
		 */
		
		
		
		usuari="postgres";
		password="s0lar1s";
		driverBBDD="org.postgresql.Driver";
		url="jdbc:postgresql://127.0.0.1/cineonline";
		
		
		
		try{
			Class.forName(driverBBDD);
			conn = DriverManager.getConnection(url, usuari, password);
			stmt = conn.createStatement();
		}catch(ClassNotFoundException e){
			System.err.println("[Controlador]: configurar -> Error: "+url+", "+usuari);
			e.printStackTrace();
		}catch(SQLException e1){
			System.err.println("[Controlador]: configurar -> Error: "+url+", "+usuari);
			e1.printStackTrace();
		}
		/*Constructor buit*/
	}
	
	public Controlador(String driverBBD){
		/* 
		 * Constructor amb driver, despr�s cal configurar
		 * per utilitzar l'acc�s a les bases de dades
		 */
		this.driverBBDD = driverBBD;
	}
	
	public Controlador(String driverBBD,String url) {
		/*
		 * Constructor amb driver i la seba ubicacio.
		 * S'utilitza quan la base de dades no necessita
		 * usuari i password 
		 * */
		try{
			Class.forName(driverBBD);
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		}catch(ClassNotFoundException e){
			System.err.println("[Controlador]:[configurar] -> Error:"+url);
			e.printStackTrace();
		}catch(SQLException e1){
			System.err.println("[Controlador]:[configurar] -> Error:"+url);
			e1.printStackTrace();
		}
	}
	
	public Controlador(String driverBBD,String url,String usuario, String password){
		/*
		 * Constructor amb driver, ubicacio.
		 * S'utilitza quan la base de dades necessita
		 * usuari i password
		 */
		try{
			Class.forName(driverBBD);
			conn = DriverManager.getConnection(url, usuario, password);
			stmt = conn.createStatement();
		}catch(ClassNotFoundException e){
			System.err.println("[Controlador]:[configurar] -> Error: "+url+", "+usuario);
			e.printStackTrace();
		}catch(SQLException e1){
			System.err.println("[Controlador]:[configurar] -> Error: "+url+", "+usuario);
			e1.printStackTrace();
		}
	}
	
	public void configurar(String url){
		/*
		 * En el cas de que nom�s li haguem passat el
		 * driver i despr�s el volem configurar sense
		 * que la base de dades necessiti
		 * nom usuari i contrasenya
		 */
		try{
			Class.forName(driverBBDD);
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		}catch(ClassNotFoundException e){
			System.err.println("[Controlador]:[configurar] -> Error: "+url);
			e.printStackTrace();
		}catch(SQLException e1){
			System.err.println("[Controlador]:[configurar] -> Error: "+url);
			e1.printStackTrace();
		}
	}
	
	public void configurar(String url, String usuario, String password){
		/*
		 * En el cas de que nom�s li haguem passat el
		 * driver i despr�s el volem configurar quan
		 * la base de dades necessiti nom usuari i 
		 * contrasenya
		 */
		try{
			Class.forName(driverBBDD);
			conn = DriverManager.getConnection(url, usuario, password);
			stmt = conn.createStatement();
		}catch(ClassNotFoundException e){
			System.err.println("[Controlador]:[configurar] -> "+url+", "+usuario);
			e.printStackTrace();
		}catch(SQLException e1){
			System.err.println("[Controlador]:[configurar] -> "+url+", "+usuario);
			e1.printStackTrace();
		}
		
	}
	
	
	public Vector select(String query) throws SQLException{
		/*
		 * Realitza un query concreta
		 */
		
		try{			
		
			rs = stmt.executeQuery(query);
			return toVector(rs);
			
		}catch(SQLException e){
			System.err.println("[Controlador]:[select]"+ query);
			e.printStackTrace();
			return null;
		}
	}
	
	public void update(String query) throws SQLException{
		/*
		 * Realitzar una modificaci� a la base de dades concreta 
		 */
		stmt.executeUpdate(query);
	}
	
	public void cerrarBDD(){
		/*
		 * Tenca la conexio de la base de dades
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
	
	public Vector toVector(ResultSet rs){
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
			return null;
		}
	}
}
