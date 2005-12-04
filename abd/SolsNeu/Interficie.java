/**
 * @author victor
 * @fecha 03-dic-2005
 * @email victorbcn@gmail.com
 *
 */

package SolsNeu;

public class Interficie {
	
	private Controlador contr;
	private static String user;
	private static String pwd;
	//private static String opcion;
	private static final String version = "2005.12.04";
	
	
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
	
	public void imprimirResultado(String res) {
		
		System.out.println(res);
		SimpleInput.getString("Aprieta una tecla para volver");
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
			contr.consultaDatosPersonales();
		}
		else if (opcion.equals("b")) {
			contr.consultaFacturas();
		}
		else if (opcion.equals("c")) {
			contr.queTengoAlquilado();
		}
		else if (opcion.equals("d")) {
			contr.productosDisponibles();
		}
		else if (opcion.equals("q")) {
			
			// llamar a controlador.salir
			
			contr.salir();
			
		}
				
	}
	
	

}
