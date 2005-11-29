package servlets.gestioCinema.gestioPelicules;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GetDetallPeliculaServlet
 *
 */
 public class GetCatalegPeliculesServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urlExit="/jspIvan/catalegPelicules.jsp";
	private String urlError="/jspIvan/catalegPelicules.jsp";


	public GetCatalegPeliculesServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPelicules ctrlPelicules;
		 
		try {
			ctrlPelicules = new ControladorPelicules();
			Vector catalegPelicules = ctrlPelicules.getPelicules();		
			request.getSession().setAttribute("catalegPelicules", catalegPelicules);		
			
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}  	
	
	  	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}   	  	    
}

