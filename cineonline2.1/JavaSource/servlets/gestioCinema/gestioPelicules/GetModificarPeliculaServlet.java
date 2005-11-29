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
 * Servlet implementation class for Servlet: GetDetallPeliculaServlet
 *
 */
 public class GetModificarPeliculaServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urlExit="/jspIvan/catalegPelicules.jsp";
	private String urlError="/jspIvan/catalegPelicules.jsp";

	public GetModificarPeliculaServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}  	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPelicules ctrlPelicules;
		String id = request.getParameter("id");
		try {
			ctrlPelicules = new ControladorPelicules();
			Pelicula pelicula = ctrlPelicules.getPelicula(Integer.parseInt(id));		
			request.getSession().setAttribute("pelicula", pelicula);		
			
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}   	  	    
}