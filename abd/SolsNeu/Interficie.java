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
		
		System.out.println();
		System.out.println("===============");
		System.out.println("Menú de Usuario");
		System.out.println("===============");
		System.out.println();
		System.out.println("a) Consultar mis datos personales");
		System.out.println("b) Consultar mis facturas");
		System.out.println("c) Consultar mis alquileres actuales");
		System.out.println("d) Consultar productos disponibles");
		System.out.println("-------------------------------------");
		System.out.println("q) Salir del programa");
		System.out.println();
		System.out.println();
		opcion = SimpleInput.getString("¿Qué opción eliges?");
		
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
		System.out.println();
		System.out.println("================");
		System.out.println("Menú de Vendedor");
		System.out.println("================");
		System.out.println();
		System.out.println("a) Consulta de clientes con alquileres retrasados.");
		System.out.println("b) Consulta de productos sin stock.");
		System.out.println("c) Consulta de ventas acumuladas.");
		System.out.println("d) Consulta de actividad preferida de los clientes.");
		System.out.println("e) Poner unidades de la temporada pasada en alquiler");
		System.out.println("f) NUEVA FACTURA.");
		System.out.println("g) Devolver Producto alquilado");
		System.out.println("h) Dar de alta nuevas unidades de un producto.");
		System.out.println("i) Extra: Ver factura");
		System.out.println("-------------------------------------");
		System.out.println("q) Salir del programa");
		System.out.println();
		System.out.println();
		opcion = SimpleInput.getString("¿Qué opción eliges?");
		
		if (opcion.equals("a")) {
			this.morosos();
		}
		else if (opcion.equals("b")) {
			this.productosAcabados();
		}
		else if (opcion.equals("c")) {
			this.ventasAcumuladas();
		}
		else if (opcion.equals("d")) {
			this.actividadFavorita();
		}
		else if (opcion.equals("e")) {
			contr.venta2alquiler();
		}
		else if (opcion.equals("f")) {
			this.nuevaFactura();
		}
		else if (opcion.equals("g")) {
			this.devolverAlquiler();
		}
		else if (opcion.equals("h")) {
			this.nuevaUnidad();
		}
		else if (opcion.equals("i")) {
			this.verFactura();
		}
		else if (opcion.equals("q")) {
			contr.salir();		
		}			
	}
	
	
	public void morosos() {
		
		this.imprimirResultado(contr.morosos());
				
	}
	
	public void productosAcabados() {
		
		this.imprimirResultado(contr.productosAcabados());
	
	}
	
	
	public void ventasAcumuladas(){
		System.out.println();
		System.out.println("a) Consulta de ventas de un día en concreto.");
		System.out.println("b) Consulta de ventas de un período.");
		System.out.println("c) Consulta de ventas totales.");
		System.out.println();
		String opcion = SimpleInput.getString("¿Qué opción eliges?");
		
		if (opcion.equals("a")) {
			
			String dia = SimpleInput.getString("¿Día?");
			String mes = SimpleInput.getString("¿Mes?");
			String any = SimpleInput.getString("¿Año?");
			
			this.imprimirResultado(contr.ventasAcumuladas(any+mes+dia));
			
			
			
		}
		else if (opcion.equals("b")){
			
			
			System.out.println("Fecha Inicial");
			String dia = SimpleInput.getString("¿Día?");
			String mes = SimpleInput.getString("¿Mes?");
			String any = SimpleInput.getString("¿Año?");
			
			System.out.println("Fecha Final");
			String dia2 = SimpleInput.getString("¿Día?");
			String mes2 = SimpleInput.getString("¿Mes?");
			String any2 = SimpleInput.getString("¿Año?");

			this.imprimirResultado(contr.ventasAcumuladas(any+mes+dia,any2+mes2+dia2));
			
			
		}
		else if (opcion.equals("c")){
			
			
			this.imprimirResultado(contr.ventasAcumuladas());
			
		}
		
		
		
		
		
		
	}
	
	public void nuevaFactura() {
		int id_factura;
		boolean salida = false;
		
		contr.listaClientes();
		String client = SimpleInput.getString("¿DNI del cliente?");
		id_factura = contr.nuevaFactura(client);		
		
		
		while (!salida) {
			
			String salir = SimpleInput.getString("Nueva línea? (si/no)");
			if (salir.contains("n")) break;
			
			// Mostramos las unidades para que el dependiente pueda elegir.
			// TODO: Comprobar que metes una unidad de alquiler o al menos mostrar de qué tipo son
			this.imprimirResultado(contr.unidadesDisponibles());
			
			int id_unidad = SimpleInput.getInteger("¿Unidad a añadir?");
			int dias = SimpleInput.getInteger("Dias de alquiler? [0 si es una compra]");
			
			int n = contr.nuevaLinia(id_factura,id_unidad,dias);
			
			if (n==0){
				
				System.out.println("ERROR: No se ha podido añadir la unidad a la factura.");
			}
			
		}
		
		// Mostramos la factura completa con todas sus línias.
		this.imprimirResultado(contr.mostrarFactura(id_factura));
		
		String confirmar = SimpleInput.getString("Confirmamos la factura? (si/no)");
		if (confirmar.contains("s")) contr.confirmarFactura(id_factura);
		
	}
	
	
	
	public void devolverAlquiler(){
		
		int id_factura;
				
		this.imprimirResultado(contr.productosEnAlquiler());
		
		int id_unitat = SimpleInput.getInteger("¿ID de la unidad a devolver? [0 para volver]");
		if (id_unitat == 0) return;
		
		id_factura = contr.devolverAlquiler(id_unitat);
		System.err.println(id_factura);
		
		if (id_factura == -1) {
			System.out.println("[Error] La unidad no estaba alquilada");
			return;
		} else if (id_factura == 0) {
			
			System.out.println();
			System.out.println("[Aviso] Unidad devuelta correctamente");
			System.out.println();
			return;
		}
		
		else {		
			this.imprimirResultado(contr.mostrarFactura(id_factura));
			String confirmar = SimpleInput.getString("Confirmamos la factura? (si/no)");
			if (confirmar.contains("s")) contr.confirmarFactura(id_factura);
		}
		
	}
	
	public void actividadFavorita(){
		this.imprimirResultado(contr.actividadFavorita());	
	}
	
	public void verFactura(){
		int id_factura = SimpleInput.getInteger("¿Número de Factura?");
		this.imprimirResultado(contr.mostrarFactura(id_factura));
		
	}
	
	public void menu_admin() {
		
		String opcion;
		System.out.println();
		System.out.println("=====================");
		System.out.println("Menú de Administrador");
		System.out.println("=====================");
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
		//TODO: Comprovar que nom�s agafem V o LL
		String llog_vend = SimpleInput.getString("Tipo: [V:Venta, LL:Lloguer]");
		
		contr.nuevaUnidad(id_producte,mida_talla,llog_vend);
		
	}
	
	public void nuevoUsuario(){
		
				
		//TODO: No dejar poner nada salvo 0,1 o 2.
		//int t_usuario = SimpleInput.getInteger("Tipo de usuario [0:Administrador, 1:Trabajador, 2:Cliente]");
		String nif = SimpleInput.getString("NIF? ");
		String login = SimpleInput.getString("login? ");
		String nom = SimpleInput.getString("Nombre? ");
		String cognom1 = SimpleInput.getString("Primer apellido? ");
		String cognom2 = SimpleInput.getString("Segundo apellido? ");
		String ciutat = SimpleInput.getString("Ciudad ?");
		String carrer = SimpleInput.getString("Calle? ");
		String num = SimpleInput.getString("Numero?");
		String pis = SimpleInput.getString("Piso?");
		String telf_contacte = SimpleInput.getString("Telefono? ");
		
		contr.nuevoUsuario(nif,0,login,nom,cognom1,cognom2,ciutat,carrer,num,pis,telf_contacte);
		
	}
	
	public void nuevoProducto(){
		
		String descr = SimpleInput.getString("Descripci�n del producto (tipo)? ");
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
