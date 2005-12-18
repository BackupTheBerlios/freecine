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
	//private static String url = "jdbc:postgresql://161.116.52.21/voc0210";
	
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
		
		if(user.equals("victor") || user.equals("voc0210") || user.equals("apr0110") || user.equals("abel2")) {
			return 0;
		}
		
		else if(user.contains("cli")) {
			return 2;
		}
		
		else if(user.contains("dep")) {
			return 1;			
		}		
		
		return 0;
	}
	
	
	public String datosCliente(){
		String r,query;
		ResultSet res;

		// VISTA: vista_dades_client
		// ABEL: Aquesta vista encara no funciona bï¿½!!!
		// query = "select * from vista_dades_client";
		//query = "select * from client where login=CURRENT_USER";
		query = "select * from consulta_dades_client()";
		res = execQuery(con,query);
		r ="";
		
		try {
			while(res.next()){
				
				/*
				System.out.print(res.getString(1));
				System.out.print(" | ");
				System.out.print(res.getString( 2 ));
				System.out.println(""); */
				
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\t");
				r = r.concat(res.getString(5)+"\t");
				r = r.concat(res.getString(6)+"\t");
				r = r.concat(res.getString(7)+"\t");
				r = r.concat(res.getString(8)+"\t");
				r = r.concat(res.getString(9)+"\t");
				r = r.concat(res.getString(10)+"\t");
				r = r.concat(res.getString(11)+"\n");
			}
			
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
	}
	
	
	public String listaClientes(){
		String r,query;
		ResultSet res;

		query = "select nif, nom, cognom1, cognom2 from vista_dades_client";
		res = execQuery(con,query);
		r ="";
		try {
			while(res.next()){
				
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\n");
			}
			
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
	}
	
	
	
	public String facturasCliente(){
		String r,query;
		ResultSet res;
		query = "select * from consulta_factures_client()";
		res = execQuery(con,query);
		
		r ="";
		r = r.concat("nif"+"\t\t");
		r = r.concat("login"+"\t\t");
		r = r.concat("nom"+"\t\t");
		r = r.concat("cognom1"+"\t");
		r = r.concat("cognom2"+"\t");
		r = r.concat("# Factura"+"\t");
		r = r.concat("Data"+"\t");
		r = r.concat("Import"+"\t");
		r = r.concat("Import + IVA"+"\n");
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\t");
				r = r.concat(res.getString(5)+"\t");
				r = r.concat(res.getString(6)+"\t");
				r = r.concat(res.getString(7)+"\t");
				r = r.concat(res.getString(8)+"\t");
				r = r.concat(res.getString(9)+"\n");
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
	}
	
	/**
	 * 
	 * @param id_factura
	 * @return
	 */
	public String getFactura(int id_factura) {
		
		String r,query;
		ResultSet res;
		query = "select * from factura where id_factura="+id_factura;
		r = "";
		res = execQuery(con,query);
		try {
			while(res.next()){
				
				r = r.concat("\t");
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\t");
				r = r.concat(res.getString(5)+"\t");
				r = r.concat(res.getString(6)+"\t\t");
				r = r.concat(res.getString(7)+"\n");
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
		
	}
	
	
	public String getLinia(int id_factura) {
		
		String r,query;
		ResultSet res;
		query = "select id,id_unit,import,dies_lloguer from linia_factura where num_factura="+id_factura;
		r = "";
		r.concat(" id | id_unit | import | dies_lloguer\n");
		r.concat("----+---------+--------+--------------\n");
		res = execQuery(con,query);
		try {
			while(res.next()){
				
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
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
		query = "select * from consulta_prod_llogats()";
		res = execQuery(con,query);
		r ="";		
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\t");
				r = r.concat(res.getString(5)+"\t");
				r = r.concat(res.getString(6)+"\t");
				r = r.concat(res.getString(7)+"\t");
				r = r.concat(res.getString(8)+"\t");
				r = r.concat(res.getString(9)+"\t");
				r = r.concat(res.getString(10)+"\t");
				r = r.concat(res.getString(11)+"\t");
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
		r = r.concat("id"+"\t");
		r = r.concat("Descripcion"+"\t");
		r = r.concat("Modelo"+"\t\t");
		r = r.concat("Marca"+"\t\t");
		r = r.concat("Actividad"+"\t\t");
		r = r.concat("Precio"+"\t\t");
		r = r.concat("Precio+IVA"+"\n");
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t\t");
				r = r.concat(res.getString(3)+"\t\t");
				r = r.concat(res.getString(4)+"\t\t");
				r = r.concat(res.getString(5)+"\t\t");
				r = r.concat(res.getString(6)+"\t\t");
				r = r.concat(res.getString(7)+"\n");
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;
	}
	
	
	public ResultSet unidadesDisponibles(){
		String r,query;
		ResultSet res;
		query = "select * from vista_unitats_disponibles";
		return execQuery(con,query);
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
	
		
	public ResultSet morosos(){
		String query;
		query =  "select * from vista_clients_mesdies_lloguer";
		return execQuery(con,query);
	}
	
	
	/*
	 * NOTA: Nomï¿½s retorna productes esgotats que han tingut unitats disponibles
	 * TODO: S'hauria de modificar la vista per retornar tambï¿½ productes dels que 
	 * 		mai hem tingut cap unitat.
	 * 
	 */
	
	public ResultSet productosAcabados(){
		String query;
		query = "select * from vista_unitats_esgotades";
		return execQuery(con,query);
		
	}
	
	
	/**
	 * @return
	 */
	public ResultSet ventasAcumuladas() {
		
		String query = "select * from vista_import_acumulat";
		
		return execQuery(con,query);
	}
	
	public ResultSet ventasAcumuladas(String data) {
		
		String query = "select * from funcio_import_data('"+data+"')";
		
		return execQuery(con,query);
	}
	
	public ResultSet ventasAcumuladas(String data_de, String data_fins) {
		
		String query = "select * from funcio_import_data('"+data_de+","+data_fins+"')";
		
		return execQuery(con,query);
	}
	
	public ResultSet actividadFavorita(){
		String query;
		query = "select nif,nom,cognom1,cognom2,activitat_favorita from client ORDER by cognom1 ASC";
		return execQuery(con,query);
	}
	
	
	public String venta2alquiler(){
		String r,query;
		query ="select func_actualitza_unitats()";
		execQuery(con,query);
		
		//TODO Se podria mirar que devolviera el numero de unidades cambiadas.
		r = "Se han pasado las unidades del año pasado a alquiler.";
		return r;
	}
	
	/**
	 * @return
	 */
	public ResultSet productosEnAlquiler() {
		
		String query = "select * from vista_productes_llogats_client";
		return execQuery(con,query);
		
	}

	
	/**
	 * 
	 * @param dni Dni del cliente al que asignamos la nueva factura
	 * @return identificador de la nueva factura.
	 */
	public int nuevaFactura(String dni) {
		
		int id_factura = 0;
		ResultSet rs;
		
		//TODO: Llamar a una función "crear_factura" que me retorne el id de la factura creada.
		
		String query = "select func_nova_factura('"+dni+"')";
		rs = execQuery(con,query);
		try {
			while(rs.next()){						
				
				id_factura = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}	
		
		return id_factura;
		
	}
	
	
	public ResultSet devolverAlquiler(int id_unitat) {		
		
		String query = "select func_retornar_unitat("+id_unitat+")";
		return execQuery(con,query);		
		
	}
	
	/**
	 * @param id_factura Factura que queremos confirmar.
	 */
	public void confirmarFactura(int id_factura) {
		
		String query = "update factura set confirm = 'true' where id_factura ="+id_factura;
		execUpdate(con,query);
		
	}
	
	public void nuevaLinia(int id_factura, int id_unitat, int dies_lloguer) {
		
		String query = "INSERT INTO linia_factura (num_factura,id_unit,dies_lloguer)VALUES ("+id_factura+","+id_unitat+","+dies_lloguer+")";
		
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
		
		//TODO: Aï¿½adir el usuario al grupo t_usuario.
		
		n = execUpdate(con,query);
		
		return n;
	}
	
	
	
	/**
	 * @param descr Descripciï¿½n del producto
	 * @param model Modelo del producto
	 * @param marca Marca del producto
	 * @param mat_prim Materias primas del producto
	 * @param activitat Actividad del producto
	 * @param distribuidors Distribuidor al que se compra el producto
	 * @param p_venda Precio de venta
	 * @param p_llog_dia Precio de alquiler por dï¿½a.
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
	 * @param con Conexiï¿½n con la base de datos. Esta tiene que estar ya establecida y no ser NULL
	 * @param query Comando SQL que queremos ejecutar
	 * @return ResultSet que contiene todas las filas con cadauna de sus columnas resultantes de aplicar la instrucciï¿½n SQL en la Base de datos.
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
	
	// Fin de funciï¿½n execQuery
	
	/**
	 * 
	 * @param con Conexiï¿½n con la base de datos. Esta tiene que estar ya establecida y no ser NULL
	 * @param query Comando SQL que queremos ejecutar
	 * @return int nï¿½mero de filas afectadas por el insert, el update o el delete.
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
	 * @param res ResultSet provinent de l'execuciï¿½ d'una comanda SQL 
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
