/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */

package SolsNeu;

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
	
	public void consultaDatosPersonales(){
		
		String res;
		res = bd.datosCliente();
		ui.imprimirResultado(res);		
		
	}
	
	public void consultaFacturas(){
		String res;
		res = bd.facturasCliente();
		ui.imprimirResultado(res);		
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
	
	public void listaProductos(){
		String res;
		res = bd.listaProductos();
		ui.imprimirResultado(res);
	}
	
	public void morosos(){
		
		String res;
		res = bd.morosos();
		ui.imprimirResultado(res);
		
	}
	
	public void productosAcabados(){
		
		String res;
		res = bd.productosAcabados();
		ui.imprimirResultado(res);
		
	}
	
	public void ventasAcumuladas(){
		
	}
	
	public void actividadFavorita(){
		
		String res;
		res = bd.actividadFavorita();
		ui.imprimirResultado(res);
		
	}
	
	public void venta2alquiler(){
		String res;
		res = bd.venta2alquiler();
		ui.imprimirResultado(res);
	}
	
	public void nuevaFactura(){
		
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
