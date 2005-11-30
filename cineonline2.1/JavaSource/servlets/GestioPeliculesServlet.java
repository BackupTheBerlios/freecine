package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;

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
	private String urlExit="/jspIvan/pelicules.jsp";
	private String urlError="/jspIvan/error.jsp";
	private RequestDispatcher rd;
	

	public GestioPeliculesServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
	
	}   	 
	
	private void llistarPeliculesAction() throws ServletException, IOException{
		
		ControladorPelicules ctrlPelicules;
		try {
			ctrlPelicules = new ControladorPelicules();
			Vector catalegPelicules = ctrlPelicules.getPelicules();		
			request.getSession().setAttribute("catalegPelicules", catalegPelicules);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}
}