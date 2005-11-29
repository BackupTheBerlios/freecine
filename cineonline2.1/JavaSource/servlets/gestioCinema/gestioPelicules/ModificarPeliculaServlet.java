package servlets.gestioCinema.gestioPelicules;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;
import gestioCinema.gestioPelicules.Pelicula;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: ModificarPeliculaServlet
 *
 */
 public class ModificarPeliculaServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urlExit="/jspIvan/catalegPelicules.jsp";
	private String urlError="/jspIvan/modificarPeliculaError.jsp";

	public ModificarPeliculaServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPelicules ctrlPelicules;
		String id = request.getParameter("id");
		Pelicula pelicula = (Pelicula) request.getSession().getAttribute("pelicula");
		/* s'han de agafar els atributs de la pagina
		 * request.getParameters("nomParametre");
		 * */
		/*pelicula.setAll();*/
		
		try {
			ctrlPelicules = new ControladorPelicules();
			
			ctrlPelicules.modificarPelicula(pelicula);				
			
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}   	  	    
}