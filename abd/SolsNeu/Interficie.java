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
	private static final String version = "2005.12.05";
	
	
	/* Constructor de Clase Interficie */
	
	public Interficie(Controlador c) {
		
		contr = c;
	}
	
	
	/* Metodos Clase Interficie */
	
	public void login() {
		
		System.out.println("Bienvenidos a SolsNeu v"+version);
		user = SimpleInput.getString("Usuario:\t");
		pwd = SimpleInput.getString("Password:\t");
		contr.login(user,pwd);
		
	}
	
	public void imprimirResultado(String res) {
		
		System.out.println(res);
		SimpleInput.getString("Aprieta una tecla para continuar.");
	}
	
	public void menu(int u) {
		
		if(u==0) {
			this.menu_admin();
		} else if(u == 1) {
			this.menu_vendedor();
		} else {
			this.menu_client();
		}
		
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
			contr.salir();		
		}	
	}
	
	public void menu_vendedor() {
		
		String opcion;
		
		System.out.println("Menú de Vendedor");
		System.out.println();
		System.out.println("a) Consulta de clientes con alquileres retrasados.");
		System.out.println("b) Consulta de productos sin stock.");
		System.out.println("c) Consulta de ventas acumuladas.");
		System.out.println("d) Consulta de actividad preferida de los clientes.");
		System.out.println("e) Poner unidades de la temporada pasada en alquiler");
		System.out.println("f) NUEVA FACTURA.");
		System.out.println("g) Dar de alta nuevas unidades de un producto.");
		System.out.println("-------------------------------------");
		System.out.println("q) Salir del programa");
		System.out.println();
		System.out.println();
		opcion = SimpleInput.getString("Qué opción eliges?");
		
		if (opcion.equals("a")) {
			contr.morosos();
		}
		else if (opcion.equals("b")) {
			contr.productosAcabados();
		}
		else if (opcion.equals("c")) {
			contr.ventasAcumuladas();
		}
		else if (opcion.equals("d")) {
			contr.actividadFavorita();
		}
		else if (opcion.equals("e")) {
			contr.venta2alquiler();
		}
		else if (opcion.equals("f")) {
			contr.nuevaFactura();
		}
		else if (opcion.equals("g")) {
			this.nuevaUnidad();
		}
		else if (opcion.equals("q")) {
			contr.salir();		
		}			
	}
	
	public void menu_admin() {
		
		String opcion;
		
		System.out.println("Menú de Administrador");
		System.out.println();
		System.out.println("a) Dar de alta NUEVO usuario");
		System.out.println("b) Dar de alta nuevo producto");
		System.out.println("-------------------------------------");
		System.out.println("q) Salir del programa");
		System.out.println();
		System.out.println();
		opcion = SimpleInput.getString("Qué opción eliges?");
		
		if (opcion.equals("a")) {
			this.nuevoUsuario();
		}
		else if (opcion.equals("b")) {
			this.nuevoProducto();
		}
		else if (opcion.equals("q")) {
			contr.salir();		
		}			
	}
	
	public void nuevaUnidad() {
		
		contr.listaProductos();	
		
		int id_producte = SimpleInput.getInteger("Id del producto? ");
		String mida_talla = SimpleInput.getString("Mida o talla del producto?");
		//TODO: Comprovar que només agafem V o LL
		String llog_vend = SimpleInput.getString("Tipo: [V:Venta, LL:Lloguer]");
		
		contr.nuevaUnidad(id_producte,mida_talla,llog_vend);
		
	}
	
	public void nuevoUsuario(){
		
				
		//TODO: No dejar poner nada salvo 0,1 o 2.
		int t_usuario = SimpleInput.getInteger("Tipo de usuario [0:Administrador, 1:Trabajador, 2:Cliente]");
		String nif = SimpleInput.getString("NIF? ");
		String login = SimpleInput.getString("login? ");
		String nom = SimpleInput.getString("¿Nombre? ");
		String cognom1 = SimpleInput.getString("Primer apellido? ");
		String cognom2 = SimpleInput.getString("Segundo apellido? ");
		String ciutat = SimpleInput.getString("Ciudad ?");
		String carrer = SimpleInput.getString("Calle? ");
		String num = SimpleInput.getString("Numero?");
		String pis = SimpleInput.getString("Piso?");
		String telf_contacte = SimpleInput.getString("Teléfono? ");
		
		contr.nuevoUsuario(nif,t_usuario,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte);
		
	}
	
	public void nuevoProducto(){
		
		String descr = SimpleInput.getString("Descripción del producto (tipo)? ");
		String model = SimpleInput.getString("Model del producto? ");
		String marca = SimpleInput.getString("Marca del producto? ");
		String mat_prim = SimpleInput.getString("Materia/s prima/s? ");
		String activitat = SimpleInput.getString("Actividad? ");
		String distribuidors = SimpleInput.getString("Distribuidoress? ");
		int p_venda = SimpleInput.getInteger("Precio de venta? ");
		int p_llog_dia = SimpleInput.getInteger("Precio de alquiler? ");
		
		contr.nuevoProducto(descr,model,marca,mat_prim,activitat,distribuidors,p_venda,p_llog_dia);
		
		
	}
}
