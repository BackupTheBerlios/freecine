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
	
	/**
	 * @return 0 para administrador, 1 para vendedor y 2 para cliente.
	 */
	public int tipoUsuario(){
		
		
		return 1;
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
	
	
	public String listaProductos(){
		String r,query;
		ResultSet res;
		query = "select id_prod, descr, marca, model from producte ORDER BY descr,marca ASC";
		res = execQuery(con,query);
		r ="";		
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+" | ");
				r = r.concat(res.getString(2)+" | ");
				r = r.concat(res.getString(3)+" | ");
				r = r.concat(res.getString(4)+"\n");
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
	
	// TODO: Abel, s'ha de fer anar aquesta vista, que no funciona.
	public String morosos(){
		String r,query;
		ResultSet res;
		query =  "select * from vista_clients_mesdies_lloguer";
		//res = execQuery(con,query);
		r="";		
		
		return r;
	}
	
	
	/*
	 * NOTA: Només retorna productes esgotats que han tingut unitats disponibles
	 * TODO: S'hauria de modificar la vista per retornar també productes dels que 
	 * 		mai hem tingut cap unitat.
	 * 
	 */
	
	public String productosAcabados(){
		String r,query;
		ResultSet res;
		r = "";
		query = "select * from vista_unitats_esgotades";
		res = execQuery(con,query);
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+" | ");
				r = r.concat(res.getString(2)+" | ");
				r = r.concat(res.getString(3)+" | ");
				r = r.concat(res.getString(4)+" | ");
				r = r.concat(res.getString(5)+"\n");
				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		
		
		return r;
	}
	
	
	public String actividadFavorita(){
		String r,query;
		ResultSet res;
		r = "";
		query = " select nif,nom,cognom1,cognom2,activitat_favorita from client ORDER by cognom1 ASC";
		res = execQuery(con,query);
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+" | ");
				r = r.concat(res.getString(2)+" | ");
				r = r.concat(res.getString(3)+" | ");
				r = r.concat(res.getString(4)+" | ");
				r = r.concat(res.getString(5)+"\n");
				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		
		
		return r;
	}
	
	
	public String venta2alquiler(){
		String r,query;
		query ="select func_actualitza_unitats()";
		execQuery(con,query);
		
		//TODO Se podría mirar que devolviera el número de unidades cambiadas.
		r = "Se han pasado las unidades del año pasado a alquiler.";
		return r;
	}
	
	public int nuevaUnidad(int id_producte, String mida_talla, String llog_vend ){
		int n;
		String query ="INSERT INTO unitat (id_producte,mida_talla,llog_vend) VALUES ("+id_producte+",'"+mida_talla+"','"+llog_vend+"');";
		n = execUpdate(con,query);
		
		return n;
	}
	
	
	
	public int nuevoUsuario(String nif, int t_usuario, String login, String nom, String cognom1,
			String cognom2, String ciutat,String carrer,String num,String pis,String telf_contacte) {
		int n;
		
		String query ="INSERT INTO client (nif,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte) VALUES('"+nif+"', '"+login+"','"+nom+"','"+cognom1+"','"+cognom2+"','"+ciutat+"','"+carrer+"','"+num+"','"+pis+"',"+telf_contacte+")";
		
		//TODO: Añadir el usuario al grupo t_usuario.
		
		n = execUpdate(con,query);
		
		return n;
	}
	
	
	
	/**
	 * @param descr Descripción del producto
	 * @param model Modelo del producto
	 * @param marca Marca del producto
	 * @param mat_prim Materias primas del producto
	 * @param activitat Actividad del producto
	 * @param distribuidors Distribuidor al que se compra el producto
	 * @param p_venda Precio de venta
	 * @param p_llog_dia Precio de alquiler por día.
	 */
	public int nuevoProducto(	String descr, String model, String marca, String mat_prim, String activitat,
			String distribuidors, int p_venda, int p_llog_dia) {
		int n;
		
		String query = "INSERT INTO producte (descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia) VALUES ('"+descr+"','"+model+"','"+marca+"','"+mat_prim+"','"+activitat+"','"+distribuidors+"',"+p_venda+","+p_llog_dia+")";
		n = execUpdate(con,query);
		return n;
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
	 * 
	 * @param con Conexión con la base de datos. Esta tiene que estar ya establecida y no ser NULL
	 * @param query Comando SQL que queremos ejecutar
	 * @return int número de filas afectadas por el insert, el update o el delete.
	 * 
	 * 
	 */
	
	static int execUpdate(Connection con, String query) {
		
		try {
			Statement stmt = con.createStatement();
			System.out.println("[DEBUG] DB: "+query);
			return(stmt.executeUpdate(query));
		}catch(SQLException e) {
			System.err.println("Query failed - "+e.getMessage());
			return 0;
			
		}
	}
	
	
	
	/**
	 * @param res ResultSet provinent de l'execució d'una comanda SQL 
	 */	
	static void printResults( ResultSet res) {
		
		System.out.println(" Columna1 | Columna2");
		System.out.println("----------+-------------------------------------------------------");
		
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
