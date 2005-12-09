package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;
import gestioCinema.gestioPelicules.Pelicula;
import gestioCinema.gestioSessions.Sessio;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		try {
			
			ctrlPelicules = new ControladorPelicules();
			if(accio.equals("llistarPelicules")) llistarPeliculesAction();
			else if(accio.equals("detallPelicula")) detallPeliculaAction();
			else if(accio.equals("afegirPelicula")) afegirPeliculaAction();
			else if(accio.equals("modificarPelicula")) modificarPeliculaAction();
			else if(accio.equals("eliminarPelicula")) eliminarPeliculaAction();
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?Error=No es pot connectar a la base de dades");
		}
	}   	 

	private void llistarPeliculesAction() throws ServletException, IOException{
		/*
		 * Crida al controlador de pelicules la funcio getPelicules
		 * on obté un vector de pelicules.
		 * Posa el vector de pelicules en un atribut de sessio
		 * Per que la jsp l'agafi session.getAttribute("llistaPelicules")
		 * i agafi les dades que vulgui de la pelicula
		 */
		urlExit="intranet/pelicules.jsp";
		
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
		urlExit="intranet/fitxa-pelicula.jsp";
		int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
		try {
			
			Pelicula pelicula = ctrlPelicules.getPelicula(idPelicula);		
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
		urlExit="intranet/fitxa-pelicula.jsp";
		
		HttpSession session = request.getSession();
		Pelicula pelicula = new Pelicula();
		
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
				-1,
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
			request.getSession().setAttribute("pelicula", pelicula);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
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
		
		llistarPeliculesAction();
	}

	private void modificarPeliculaAction() throws ServletException, IOException{
		/*
		 * Afagar les dades de la pelicula, les posa en Pelicula
		 * Comprova que el format de dades sigui correcte
		 * Executa al controlador de pelicula modificarPelciual i li pasa
		 * com a parametre la pelicula i li retorna el mateix objecte actualitzat
		 */
		urlExit="intranet/fitxa-pelicula.jsp";
	}
}