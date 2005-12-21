package testMain;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;
import gestioCinema.gestioPelicules.Pelicula;
import junit.framework.TestCase;

public class SimpleTest extends TestCase{

	private Pelicula pelicula= new Pelicula();
	
	private ControladorPelicules controlador;
	
	public SimpleTest(String nom){
		super(nom);
		try {
			controlador = new ControladorPelicules();
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SimpleTest.class);
	}
	
	public void setUp(){
		pelicula.setAll(50,
				"PeliTest",
				"PeliTest",
				2005,
				120,
				"Catalana",
				18,
				"Color",
				"So",
				"Terror",
				"Parretis",
				"Parretis",
				"Parretis",
				"Parretis",
				"Prova Test",
				"www.com",
				"www.com");		
	}
	
	public void testSetAllGetsGetValors(){
		assertEquals(""+pelicula.getId(),			""+50, 			""+pelicula.getValors().elementAt(1));
		//assertEquals(pelicula.getId(),				50				);
		assertEquals(pelicula.getTitol(),			"PeliTest",		pelicula.getValors().elementAt(2));
		assertEquals(pelicula.getTitolOriginal(),	"PeliTest",		pelicula.getValors().elementAt(3));
		assertEquals(""+pelicula.getAnny(),			""+2005,	    ""+pelicula.getValors().elementAt(4));
		//assertEquals(pelicula.getAnny(),			2005);
		assertEquals(""+pelicula.getDurada(),		""+120,			""+pelicula.getValors().elementAt(5));
		//assertEquals(pelicula.getDurada(),			120);
		assertEquals(pelicula.getNacionalitat(),	"Catalana",		pelicula.getValors().elementAt(6));
		assertEquals(""+pelicula.getEdatRecomenada(),""+18,		""+pelicula.getValors().elementAt(7));
		//assertEquals(pelicula.getEdatRecomenada(),	18);
		assertEquals(pelicula.getTipusColor(),		"Color",		pelicula.getValors().elementAt(8));
		assertEquals(pelicula.getTipusSo(),			"So",			pelicula.getValors().elementAt(9));
		assertEquals(pelicula.getGenere(),			"Terror",		pelicula.getValors().elementAt(10));
		assertEquals(pelicula.getDirector(),		"Parretis",		pelicula.getValors().elementAt(11));
		assertEquals(pelicula.getGuionista(),		"Parretis",		pelicula.getValors().elementAt(12));
		assertEquals(pelicula.getProductor(),		"Parretis",		pelicula.getValors().elementAt(13));
		assertEquals(pelicula.getActors(),			"Parretis",		pelicula.getValors().elementAt(14));
		assertEquals(pelicula.getSinopsis(),		"Prova Test",	pelicula.getValors().elementAt(15));
		assertEquals(pelicula.getUrlImatge(),		"www.com",		pelicula.getValors().elementAt(16));
		assertEquals(pelicula.getUrlWeb(),			"www.com",		pelicula.getValors().elementAt(17));
		
	}
	
	public void testCampsValidsPeliculaNull(){
		//assertTrue(pelicula.campsValids());
	}
	
	public void testAfegirPelicula() throws ControladorException{
		controlador.afegirPelicula(pelicula);
		//assertEquals(pelicula.toString(),controlador.getPelicula(50).toString(),(Pelicula)controlador.getPelicules().firstElement().toString());
		assertEquals(pelicula.toString(),controlador.getPelicula(50).toString());
		//si nomes hi ha un element a dintre de la base de dades ha de ser .firstElement()
	}

	public void testModificarPelicula() throws ControladorException{
		pelicula.setTitol("PeliTest2");
		controlador.modificarPelicula(pelicula);
		//assertEquals(controlador.getPelicula(50).toString(),pelicula,controlador.getPelicules().firstElement());
		assertEquals(controlador.getPelicula(50).toString(),pelicula.toString());
//		si nomes hi ha un element a dintre de la base de dades ha de ser .firstElement()
	}
	
	public void testEliminarPelicula() throws ControladorException{
		controlador.eliminarPelicula(""+50);
		//boolean esnull=controlador.getPelicules().equals(null);
		//assertTrue(esnull);
		assertNull(controlador.getPelicules());
		//mirar que retornara quan no hi haguin pelicules
	}


	
}