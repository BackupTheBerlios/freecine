/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */

package SolsNeu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controlador {
		
	private static Interficie ui;
	private static AccessoDatos bd;
	private static boolean salida = false;
	private static int t_usuario;
	
	
	public Controlador(){
		super();
	}
	
	
	public static void main(String[] args) {
			
		// new Controlador() o this?
		ui = new Interficie(new Controlador());
		ui.login();
		t_usuario = bd.tipoUsuario();
		
		
		// Bucle del programa
		while (!salida){
			ui.menu(t_usuario);
				
		}
		
	}
	
	/**
	 * Funci�n salir del Programa
	 */
	public void salir(){
		bd.cerrarConexion();
		salida = true;
	}
		
	/**
	 * Funci�n que crea un nuevo objeto del tipo AccessoDatos.
	 * 
	 * @param user Usuario a Conectarse a la Base de Datos.
	 * @param pwd  Password de user.
	 */
	public void login(String user, String pwd) {
		bd = new AccessoDatos(user,pwd, this);
	}
	
	
	
	/*
	 * M�todos encargados de mostrar resultados.
	 * 
	 * 
	 */
	
	public void consultaDatosPersonales(){
		
		String res;
		res = bd.datosCliente();
		ui.imprimirResultado(res);	
		
	}
	
	public void consultaFacturas(){
		String res;
		res = bd.facturasCliente();
		ui.imprimirResultado(res);
		//ui.imprimirResultado(res2);
	}
	
	
	/**
	 *  Muestra una factura y todas sus l�neas asociadas.
	 * 
	 * @param id_factura
	 */
	public String mostrarFactura(int id_factura) {
		
		String str;
		str =" id_factura |    data    | nif_client | import | iva  | import_final | confirm\n";
		str = str.concat("------------+------------+------------+--------+------+--------------+---------\n");
		str = str.concat(bd.getFactura(id_factura));
		if (bd.getLinia(id_factura)!= "") {
			str = str.concat("\n id | id_unit | import | dies_lloguer\n");
			str = str.concat("----+---------+--------+--------------\n");
			str = str.concat(bd.getLinia(id_factura));
		}
		return str;
	}
	
	public void queTengoAlquilado(){
		
		String res;
		res = bd.productosAlquiladosCliente();
		ui.imprimirResultado(res);
		
	}
	
	public void productosDisponibles(){
		
		String res;
		res = bd.productosDisponibles();
		ui.imprimirResultado(res);
		
	}
	
	
	public String unidadesDisponibles(){
		
		ResultSet res = bd.unidadesDisponibles();
		String r ="";
		r = r.concat("id"+"\t");
		r = r.concat("Descripcion"+"\t");
		r = r.concat("Marca"+"\t\t");
		r = r.concat("Modelo"+"\t\t");
		r = r.concat("Precio+IVA"+"\t\t");
		r = r.concat("Precio Alquiler"+"\n");
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t\t");
				r = r.concat(res.getString(3)+"\t\t");
				r = r.concat(res.getString(4)+"\t\t");
				r = r.concat(res.getString(5)+"\t\t");
				r = r.concat(res.getString(6)+"\n");
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
		}
		return r;		
	}
	
	public void listaProductos(){
		String res;
		res = bd.listaProductos();
		ui.imprimirResultado(res);
	}
	
	
	public void listaClientes(){
		
		ui.imprimirResultado(bd.listaClientes());
		
	}
	
	public String morosos(){
		
		ResultSet res = bd.morosos();
		
		String r =" nif | nom | cognom1 | telf_contacte | Data Actual | Data Lloguer | Dies llogat | LL/V\n";
		r = r.concat("-----+-----+---------+---------------+-------------+--------------+-------------+------\n");
		
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\t");
				r = r.concat(res.getString(5)+"\t");
				r = r.concat(res.getString(6)+"\t");
				r = r.concat(res.getString(7)+"\t");
				r = r.concat(res.getString(8)+"\n");
			}
			
			return r;
			
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			return r ="[Error]: Error en ResultSet.\n";
		}		
	}
	
	public String productosAcabados(){
		
		ResultSet res;
		res = bd.productosAcabados();
		String r = "id_producte | descr   |   model     |   marca   | mida_talla\n";
		r = r.concat("------------+---------+-------------+-----------+------------\n");
		
		try {
			
			while(res.next()){
				
				r = r.concat("\t");
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\t");
				r = r.concat(res.getString(5)+"\n");
				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			r = "[Error]: Error en ResultSet.\n";
		}
		return r;
	}
	
	public String ventasAcumuladas(){
		ResultSet res;
		res = bd.ventasAcumuladas();
		
		String r = "  import   | unitats |   Data1    |   Data2\n";
		r = r.concat("-----------+---------+------------+------------\n");
		
		try {
			
			while(res.next()){
				
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\n");				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			r = "[Error]: Error en ResultSet.\n";
		}
		return r;
		
		
	}
	
	public String ventasAcumuladas(String data){
		ResultSet res;
		res = bd.ventasAcumuladas(data);
		
		String r = "  import   | unitats |   Data1    |   Data2\n";
		r = r.concat("-----------+---------+------------+------------\n");
		
		try {
			
			while(res.next()){
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\n");				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			r = "[Error]: Error en ResultSet.\n";
		}
		return r;
		
	}
	
	public String ventasAcumuladas(String data_de, String data_fins){
		ResultSet res;
		res = bd.ventasAcumuladas(data_de,data_fins);
		
		String r = "  import   | unitats |   Data1    |   Data2\n";
		r = r.concat("-----------+---------+------------+------------\n");
		
		try {
			
			while(res.next()){
				
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\n");				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			r = "[Error]: Error en ResultSet.\n";
		}
		return r;
		
	}
	
	public String actividadFavorita(){
		ResultSet res = bd.actividadFavorita();
		String r="    nif    |   nom        | cognom1      |  cognom2      | activitat_favorita\n";
		r = r.concat("-----------+--------------+--------------+---------------+------------------------\n");
		
		try {
			while(res.next()){
								
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t\t");
				r = r.concat(res.getString(3)+"\t\t");
				r = r.concat(res.getString(4)+"\t\t");
				r = r.concat(res.getString(5)+"\n");
				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			r = "[Error]: Error en ResultSet.\n";
		}
		
		return r;
		
	}
	
	public void venta2alquiler(){
		String res;
		res = bd.venta2alquiler();
		ui.imprimirResultado(res);
	}
	
	public int nuevaFactura(String dni){
		
		int id_factura;		
		id_factura = bd.nuevaFactura(dni);
		
		return id_factura;
	}
	
	
	public int devolverAlquiler(int id_unitat) {
		
		int id_factura = -1;
		ResultSet rs = bd.devolverAlquiler(id_unitat);
		
		try {
			while(rs.next()){						
				
				id_factura = rs.getInt(1);
				System.err.println(id_factura);
				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			return -1;
		}
		
		return id_factura;	
	}
	
	
	public String productosEnAlquiler(){
		
		ResultSet res = bd.productosEnAlquiler();
		String r=" id_unitat | DescripciÃ³ |   Marca   |   Model    |  Client   | Data llogat | Dies llogat | Data Retorn\n";
		r = r.concat("-----------+-------------+-----------+------------+-----------+-------------+-------------+-------------\n");
		try {
			while(res.next()){
				r = r.concat("\t");
				r = r.concat(res.getString(1)+"\t");
				r = r.concat(res.getString(2)+"\t");
				r = r.concat(res.getString(3)+"\t");
				r = r.concat(res.getString(4)+"\t");
				r = r.concat(res.getString(5)+"\t");
				r = r.concat(res.getString(7)+"\t");
				r = r.concat(res.getString(8)+"\t");
				r = r.concat(res.getString(9)+"\n");
				
			}
		} catch (SQLException e) {
			System.err.println("Fetch failed: "+ e.getMessage());
			r = "[Error]: Error en ResultSet.\n";
		}
		
		return r;
		
		
		
		
	}
	
	
	
	/**
	 * 
	 * @param id_factura Número de factura a la que añadir la línea de factura
	 * @param id_unitat Unidad a incluir en la línea de factura
	 * @param dies_lloguer Días de alquiler. 0 si es una compra.
	 */
	public void nuevaLinia(int id_factura, int id_unitat, int dies_lloguer) {
		
		bd.nuevaLinia(id_factura,id_unitat,dies_lloguer);
		
	}	
	public void confirmarFactura(int id_factura) {
		
		bd.confirmarFactura(id_factura);
	}
	
	public void nuevaUnidad(int id_producte, String mida_talla, String llog_vend){
		int n;
		n = bd.nuevaUnidad(id_producte, mida_talla, llog_vend);
		if (n==0) System.out.println("[DEBUG] CONT: Error o no se han creado/modificado/eliminado filas.");
		else System.out.println("[DEBUG] CONT: Se han creado/modificado/eliminado "+n+" filas.");
	}
	
	public void nuevoUsuario(	String nif, int t_usuario, String login, String nom, String cognom1,
								String cognom2, String ciutat,String carrer,String num,String pis,String telf_contacte){
		int n;
		n = bd.nuevoUsuario(nif,t_usuario,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte);
		if (n==0) System.out.println("[DEBUG] CONT: Error o no se han creado/modificado/eliminado filas.");
		else System.out.println("[DEBUG] CONT: Se han creado/modificado/eliminado "+n+" filas.");
	}
	
	public void nuevoProducto(	String descr, String model, String marca, String mat_prim, String activitat,
								String distribuidors, int p_venda, int p_llog_dia) {
		int n;
		
		n = bd.nuevoProducto(descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia);
		
		if (n==0) System.out.println("[DEBUG] CONT: Error o no se han creado/modificado/eliminado filas.");
		else System.out.println("[DEBUG] CONT: Se han creado/modificado/eliminado "+n+" filas.");
		
	}

}
