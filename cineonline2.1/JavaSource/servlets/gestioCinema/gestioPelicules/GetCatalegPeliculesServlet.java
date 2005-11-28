package servlets.gestioCinema.gestioPelicules;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public class GetCatalegPeliculesServlet extends javax.servlet.http.HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPelicules ctrlPelicules;
		 
		try {
			ctrlPelicules = new ControladorPelicules();
			Vector catalegPelicules = ctrlPelicules.getPelicules();		
			request.getSession().setAttribute("catalegPelicules", catalegPelicules);		
			String url = "/jspIvan/catalegPelicules.jsp";
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		    rd.forward(request, response);
		} catch (ControladorException e) {
			String url = "/jspIvan/catalegPelicules.jsp?Error="+e.getMessage();
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		    rd.forward(request, response);
		}
	}  	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}   	  	    
}