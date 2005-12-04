package SolsNeu;

/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */
public class Controlador {
	
	
	private static Interficie ui;
	private static AccessoDatos bd;
	private static boolean salida = false;
	
	
	public Controlador(){
		super();
	}
	
	
	public static void main(String[] args) {
		
		
		// Hay que pasarle como argumento nuestro objeto controlador?
		ui = new Interficie(new Controlador());
		ui.login();
		
		// Bucle del programa
		while (!salida){
			ui.menu_client();
				
		}
		
	}
	
	/**
	 * Función salir del Programa
	 */
	public void salir(){
		
		bd.cerrarConexion();
		salida = true;
	}
	
	
	
	/**
	 * Función que crea un nuevo objeto del tipo AccessoDatos.
	 * 
	 * @param user Usuario a Conectarse a la Base de Datos.
	 * @param pwd  Password de user.
	 */
	public void login(String user, String pwd) {
		
		bd = new AccessoDatos(user,pwd, this);
		
		
	}
	
	
	
	
	

}
