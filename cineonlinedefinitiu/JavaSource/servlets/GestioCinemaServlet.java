package servlets;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;

import java.io.IOException;
import java.sql.SQLException;
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
		rd = getServletContext().getRequestDispatcher(urlError+"?error=Acc�s denegat");
	    rd.forward(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		String accio = request.getParameter("accio");
		
		try {
			
			ctrl = new Controlador();
			if(accio.equals("consulta")) consultaAction();
			else if(accio.equals("accedir")) accedirAction();
			else if(accio.equals("sortir")) sortirAction();
			else{
				rd = getServletContext().getRequestDispatcher(urlError+"?error=Acc�s Fallit");
			    rd.forward(request, response);
			    
			}
			
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?Error=No es pot connectar a la base de dades");
		}
	}   	 

	private void accedirAction() throws ServletException, IOException {

		String missatge = "";
		boolean autenticat = false;

		if(request.getParameter("nomUsuari")!=null)
		{
			if(request.getParameter("pwd")!=null)
			{
				if (entrada_plena(request.getParameter("nomUsuari")))
				{
					if (entrada_plena(request.getParameter("pwd")))
					{
						if (identificat(request.getParameter("nomUsuari"),request.getParameter("pwd")))
						{
							missatge = "Benvingut a l'administració " + request.getParameter("nomUsuari");
							autenticat = true;
						}
						else
						{
							missatge = "Nom d'usuari o password incorrecte.";
						}				
					}
					else
					{
						missatge = "Cal intoduir el password.";
					}
				}
				else
				{
					missatge = "Cal introduir el nom d'usuari." + missatge;
				}					
			}
		}
		else
		{
			missatge = "No s'ha passat els parametres correctament.";
		}
		String page;
		if (autenticat)
		{
			request.getSession().setAttribute("nomUsuari",request.getParameter("nomUsuari"));
			page="/intranet/default.jsp";
			
		}
		else
		{
			page="/intranet/login.jsp?missatge="+missatge;
			
		}
		rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);
		
	}
	
	public boolean entrada_plena(String entrada)
	{
		return (entrada!="");
	}

	public boolean identificat(String nomUsuari, String pwd)
	{
		String login = "popo";
		String password = "popo";
		return ((nomUsuari.compareTo(login)==0)&&(pwd.compareTo(password)==0));		
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
			} catch (SQLException e) {
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
	
	private void sortirAction() throws ServletException, IOException {
		urlExit="/intranet/login.jsp";
		request.getSession().removeAttribute("NomUsuari");
		rd = getServletContext().getRequestDispatcher(urlExit);
		rd.forward(request, response);
	}
}