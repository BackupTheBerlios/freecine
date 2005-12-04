/*
 * Created on 03-dic-2005
 *
 */
package SolsNeu;


/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */
public class Interficie {
	
	private Controlador contr;
	private static String user;
	private static String pwd;
	//private static String opcion;
	private static final String version = "2005.12.03";
	
	
	/* Constructor de Clase Interficie */
	
	public Interficie(Controlador c) {
		
		contr = c;
	}
	
	
	/* Metodos Clase Interficie */
	
	public void login() {
		
		System.out.println("Bienvenidos a SolsNeu v"+version);
		user = SimpleInput.getString("Usuario: ");
		pwd = SimpleInput.getString("Password");
		
		contr.login(user,pwd);
		
	}
	
	public void menu_client() {
		
		String opcion;
		
		System.out.println("Menú de Usuario");
		System.out.println();
		System.out.println("a) Consultar mis datos personales");
		System.out.println("b) Consultar mis facturas");
		System.out.println("c) Consultar mis alquileres actuales");
		System.out.println("d) Consultar productos disponibles");
		System.out.println("-------------------------------------");
		System.out.println("q) Salir del programa");
		System.out.println();
		System.out.println();
		opcion = SimpleInput.getString("Qué opción eliges?");
		
		if (opcion.equals("a")) {			
		}
		else if (opcion.equals("b")) {
			
		}
		else if (opcion.equals("c")) {
			
		}
		else if (opcion.equals("d")) {
			
		}
		else if (opcion.equals("q")) {
			
			// llamar a controlador.salir
			
			contr.salir();
			
		}
				
	}
	
	

}
