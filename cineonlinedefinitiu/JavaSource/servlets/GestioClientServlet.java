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
 public class GestioClientServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String urlExit="/extranet/consola.jsp";
	private String urlError="/extranet/error.jsp";
	private RequestDispatcher rd;
	private ControladorPelicules ctrlPelicules;
	
	public GestioClientServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		rd = getServletContext().getRequestDispatcher(urlError+"?error=Acc�s denegat");
	    rd.forward(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		String accio = request.getParameter("accio");
		
		try {
			
			ctrlPelicules = new ControladorPelicules();
			if(accio.equals("goCineonline")) goWeb("/extranet/index.jsp");
			else if(accio.equals("llistarPelicules")) llistarAction();
			else if(accio.equals("detallPelicula")) detallPeliculaAction();
			else if(accio.equals("goCartellera")) goWeb("/extranet/cartellera.jsp");
			else if(accio.equals("goEntrades_online")) goWeb("/extranet/entrades_online.jsp");
			else if(accio.equals("goTarifes")) goWeb("/extranet/tarifes.jsp");
			else if(accio.equals("goOnsom")) goWeb("/extranet/onsom.jsp");
			else if(accio.equals("goServeis")) goWeb("/extranet/serveis.jsp");
			else{
				rd = getServletContext().getRequestDispatcher(urlError+"?error=Acc�s Fallit");
			    rd.forward(request, response);
			    
			}
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?Error=No es pot connectar a la base de dades");
		}
	}
	
	private void detallPeliculaAction() throws ServletException, IOException {
		// TODO Auto-generated method stub
		urlExit="/extranet/fitxa_sinopsis.jsp";
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

	private void llistarAction() throws ServletException, IOException {
		// TODO Auto-generated method stub
urlExit="/extranet/sinopsis.jsp";
		
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

	private void goWeb(String url) throws ServletException, IOException{
		
		rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		
	}
}