package servlets;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;

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
 public class GestioCinemaServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String urlExit="/intranet/consola.jsp";
	private String urlError="/intranet/consola.jsp";
	private RequestDispatcher rd;
	private Controlador ctrl;
	
	public GestioCinemaServlet() {
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
			
			ctrl = new Controlador();
			if(accio.equals("consulta")) consultaAction();			
			else{
				rd = getServletContext().getRequestDispatcher(urlError+"?error=Accés Fallit");
			    rd.forward(request, response);
			}
			
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?Error=No es pot connectar a la base de dades");
		}
	}   	 

	private void consultaAction() throws ServletException, IOException {
		urlExit="/intranet/consola.jsp";				
		String query = request.getParameter("query");
		System.err.println(query);
		Vector resultat;
		if (query!= null)
		{
			try {
				resultat = ctrl.execute(query);
				request.getSession().setAttribute("resultat",resultat);
				rd = getServletContext().getRequestDispatcher(urlExit);
				rd.forward(request, response);
			} catch (ControladorException e) {
				resultat = new Vector();
				resultat.add(e.getMessage());
				rd = getServletContext().getRequestDispatcher(urlExit);
				rd.forward(request, response);
			}
		}
		else
		{	
			resultat = new Vector();
			resultat.add("Query buida");
			rd = getServletContext().getRequestDispatcher(urlExit);
			rd.forward(request, response);
		}
	}
}