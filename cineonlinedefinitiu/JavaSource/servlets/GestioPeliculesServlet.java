package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;
import gestioCinema.gestioPelicules.Pelicula;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GestioPeliculesServlet
 *
 */
 public class GestioPeliculesServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String urlExit="/intranet/pelicules.jsp";
	private String urlError="/intranet/error.jsp";
	private RequestDispatcher rd;
	private ControladorPelicules ctrlPelicules;
	
	public GestioPeliculesServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		rd = getServletContext().getRequestDispatcher(urlError+"?error=Accés denegat");
	    rd.forward(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		String accio = request.getParameter("accio");
		System.err.println("yyyyyy"+accio);
		try {
			
			ctrlPelicules = new ControladorPelicules();
			if(accio.equals("llistarPelicules")) llistarPeliculesAction();
			else if(accio.equals("detallPelicula")) detallPeliculaAction();
			else if(accio.equals("afegir")) afegirPeliculaAction();
			else if(accio.equals("novaPelicula")) novaPeliculaAction();
			else if(accio.equals("modificar")) modificarPeliculaAction();
			else if(accio.equals("eliminar")) eliminarPeliculaAction();
			else if(accio.equals("llistarGeneres")) llistarGeneresAction();
			else if(accio.equals("llistarNacionalitats")) llistarNacionalitatsAction();
			else if(accio.equals("detallNacionalitat")) detallNacionalitatAction();
			else if(accio.equals("novaNacionalitat")) novaNacionalitatAction();
			
			
			else{
				rd = getServletContext().getRequestDispatcher(urlError+"?error=Accés Fallit");
			    rd.forward(request, response);
			}
			
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?Error=No es pot connectar a la base de dades");
		}
	}   	 

	private void novaNacionalitatAction() throws ServletException, IOException {
		urlExit="/intranet/fitxa_nacionalitat.jsp";
		request.getSession().removeAttribute("nacionalitat");				
	    rd = getServletContext().getRequestDispatcher(urlExit);
	    rd.forward(request, response);
		
	}

	private void detallNacionalitatAction() throws ServletException, IOException {
		urlExit="/intranet/fitxa_nacionalitat.jsp";
		System.err.println("[detallNacionalitat]");
		int idNacionalitat = Integer.parseInt(request.getParameter("idNacionalitat"));
		try {
			
			Vector nacionalitat = ctrlPelicules.getNacionalitat(idNacionalitat);	
			request.getSession().setAttribute("nacionalitat", nacionalitat);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}

	private void llistarNacionalitatsAction() throws ServletException, IOException {
		urlExit="/intranet/nacionalitats.jsp";
		try {
			
			Vector llistaNacionalitats= ctrlPelicules.getNacionalitats();		
			request.getSession().setAttribute("llistaNacionalitats", llistaNacionalitats);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
		
	}

	private void llistarGeneresAction() throws ServletException, IOException {
	urlExit="/intranet/generes.jsp";
		
		try {
			
			Vector llistaGeneres= ctrlPelicules.getGeneres();		
			request.getSession().setAttribute("llistaGeneres", llistaGeneres);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
		
	}

	private void novaPeliculaAction() throws ServletException, IOException {
		urlExit="/intranet/fitxa_pelicula.jsp";

		request.getSession().removeAttribute("pelicula");		
		
	    rd = getServletContext().getRequestDispatcher(urlExit);
	    rd.forward(request, response);
		    
	}

	private void llistarPeliculesAction() throws ServletException, IOException{
		/*
		 * Crida al controlador de pelicules la funcio getPelicules
		 * on obté un vector de pelicules.
		 * Posa el vector de pelicules en un atribut de sessio
		 * Per que la jsp l'agafi session.getAttribute("llistaPelicules")
		 * i agafi les dades que vulgui de la pelicula
		 */
		urlExit="/intranet/pelicules.jsp";
		
		try {
			
			Vector llistaPelicules = ctrlPelicules.getPelicules();		
			request.getSession().setAttribute("llistaPelicules", llistaPelicules);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}
	
	private void detallPeliculaAction() throws ServletException, IOException{
		/*
		 * Mitjançant la id de la pelicula cridem la funcio 
		 * getPelicula i aquest ens retorna el objecte de la pelicula
		 * El posem a sessio amb l'atribut de 'pelicula' 
		 */
		urlExit="/intranet/fitxa_pelicula.jsp";
		int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
		try {
			
			Pelicula pelicula = ctrlPelicules.getPelicula(idPelicula);	
			System.err.println(pelicula);
			request.getSession().setAttribute("pelicula", pelicula);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}
	
	private void afegirPeliculaAction() throws ServletException, IOException{
		/*
		 * Afagar les dades de la pelicula, les posa en Pelicula
		 * Comprova que el format de dades sigui correcte
		 * Executa al controlador de pelicula afegirPelciual i li pasa
		 * com a parametre la pelicula
		 */
		urlExit="/intranet/fitxa_pelicula.jsp";
		
		Pelicula pelicula = new Pelicula();
		
		int id = -1;
		String titol;
		if(!request.getParameter("titol").equals("")){
			titol = request.getParameter("titol");
		}else{
			titol = "";
		}
		
		String titolOriginal = request.getParameter("titolOriginal");
		int anny;
		System.err.println(request.getParameter("anny"));
		if(!request.getParameter("anny").equals("")){
			anny = Integer.parseInt(request.getParameter("anny"));	
		}else{
			anny = 1850;
		}
		
		int durada;
		if(!request.getParameter("durada").equals("")){
			durada = Integer.parseInt(request.getParameter("durada"));	
		}else{
			durada = 0;
		}
		
		String nacionalitat = request.getParameter("nacionalitat");
		int edatRecomenada;
		System.err.println(request.getParameter("edatRecomenada"));
		if(!request.getParameter("edatRecomenada").equals("")){
			edatRecomenada = Integer.parseInt(request.getParameter("edatRecomenada"));
		}else{
			edatRecomenada = 0;
		}
		
		String tipusColor = request.getParameter("tipusColor");
		String tipusSo = request.getParameter("tipusSo");
		String genere = request.getParameter("genere");
		String director = request.getParameter("director");
		String guionista = request.getParameter("guionista");
		String productor = request.getParameter("productor");
		String actors = request.getParameter("actors");
		String sinopsis = request.getParameter("sinopsis");
		String urlWeb = request.getParameter("urlWeb");
		String urlImatge = request.getParameter("urlImatge");
		pelicula.setAll(
				id,
				titol,
				titolOriginal,
				anny,
				durada,
				nacionalitat,
				edatRecomenada,
				tipusColor,
				tipusSo,
				genere,
				director,
				guionista,
				productor,
				actors,
				sinopsis,
				urlWeb,
				urlImatge);			
		
		try {
			
			ctrlPelicules.afegirPelicula(pelicula);				
			
			llistarPeliculesAction();
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}

	}
	
	private void eliminarPeliculaAction() throws ServletException, IOException{
		/*
		 * Mitjançant la id de la pelicula cridem la funcio 
		 * eliminarPelicula i aquest ens retorna el objecte de la pelicula
		 * I anem a l'accio per anar a pelicules.
		 */
		System.err.println("elimina");
		String idPelicula = request.getParameter("idPelicula");
		try {
			
			ctrlPelicules.eliminarPelicula(idPelicula);		
			
			/* Enviem a llistar totes les pelicules*/
			llistarPeliculesAction();
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}

	private void modificarPeliculaAction() throws ServletException, IOException{
		/*
		 * Afagar les dades de la pelicula, les posa en Pelicula
		 * Comprova que el format de dades sigui correcte
		 * Executa al controlador de pelicula modificarPelciual i li pasa
		 * com a parametre la pelicula i li retorna el mateix objecte actualitzat
		 */
		urlExit="/intranet/fitxa_pelicula.jsp";
		
		Pelicula pelicula = new Pelicula();
		
		int id = Integer.parseInt(request.getParameter("idPelicula"));
		String titol = request.getParameter("titol");
		String titolOriginal = request.getParameter("titolOriginal");
		int anny = Integer.parseInt(request.getParameter("anny"));
		int durada = Integer.parseInt(request.getParameter("durada"));
		String nacionalitat = request.getParameter("nacionalitat");
		int edatRecomenada = Integer.parseInt(request.getParameter("edatRecomenada"));
		String tipusColor = request.getParameter("tipusColor");
		String tipusSo = request.getParameter("TipusSo");
		String genere = request.getParameter("genere");
		String director = request.getParameter("director");
		String guionista = request.getParameter("guionista");
		String productor = request.getParameter("productor");
		String actors = request.getParameter("actors");
		String sinopsis = request.getParameter("sinopsis");
		String urlWeb = request.getParameter("urlWeb");
		String urlImatge = request.getParameter("urlImatge");
		pelicula.setAll(
				id,
				titol,
				titolOriginal,
				anny,
				durada,
				nacionalitat,
				edatRecomenada,
				tipusColor,
				tipusSo,
				genere,
				director,
				guionista,
				productor,
				actors,
				sinopsis,
				urlWeb,
				urlImatge);			
		
		try {
			
			ctrlPelicules.modificarPelicula(pelicula);		
			pelicula = ctrlPelicules.getPelicula(pelicula.getId());
			request.getSession().setAttribute("pelicula", pelicula);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}
}