package servlets.gestioCinema.gestioPelicules;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.ControladorPelicules;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: EliminarPeliculaServlet
 *
 */
 public class EliminarPeliculaServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String urlExit="/jspIvan/catalegPelicules.jsp";
	private String urlError="/jspIvan/catalegPelicules.jsp";
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public EliminarPeliculaServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPelicules ctrlPelicules;
		String id = request.getParameter("id");
		try {
			ctrlPelicules = new ControladorPelicules();
			ctrlPelicules.eliminarPelicula(Integer.parseInt(id));		
			request.getSession().removeAttribute("pelicula");		
			
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}   	  	    
}