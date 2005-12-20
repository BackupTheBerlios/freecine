package servlets;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;
import gestioCinema.gestioUsuaris.ControladorUsuaris;
import gestioCinema.gestioUsuaris.Usuari;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GestioUsuarisServlet
 *
 */
 public class GestioUsuarisServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	   
		private static final long serialVersionUID = 1L;
		private HttpServletRequest request;
		private HttpServletResponse response;
		private String urlExit="/intranet/default.jsp";
		private String urlError="/intranet/error.jsp";
		private RequestDispatcher rd;
		private ControladorUsuaris ctrl;
		
		public GestioUsuarisServlet() {
			super();
		}   	
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.request = request;
			this.response = response;
			rd = getServletContext().getRequestDispatcher(urlError+"?error=Acciós denegat");
		    rd.forward(request, response);
		}  	
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.request = request;
			this.response = response;
			String accio = request.getParameter("accio");
			
			try {
				
				ctrl = new ControladorUsuaris();
				if(accio.equals("accedir")) accedirAction();
				else if(accio.equals("sortir")) sortirAction();
				else if(accio.equals("llistar usuaris")) llistarUsuarisAction();
				else if(accio.equals("agegir usuari")) afegirUsuariAction();
				else if(accio.equals("modificar usuari")) modificarUsuariAction();
				else if(accio.equals("eliminar usuari")) eliminarUsuariAction();
				
				else{
					rd = getServletContext().getRequestDispatcher(urlError+"?error=Accés Fallit");
				    rd.forward(request, response);
				    
				}
				
				
				
			} catch (ControladorException e) {
				rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
			}
		}   	 

		private void accedirAction() throws ServletException, IOException {

			String missatge = "";
			boolean autenticat = false;
			String nomUsuari = request.getParameter("nomUsuari");
			String psw = request.getParameter("psw");

			if(nomUsuari!=null)
			{
				if(psw!=null)
				{
					if (entradaPlena(nomUsuari))
					{
						if (entradaPlena(psw))
						{
							if (identificat(nomUsuari, psw))
							{
								missatge = "Benvingut a l'administració " + nomUsuari;
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
		
		public boolean entradaPlena(String entrada)
		{
			return (entrada!="");
		}

		public boolean identificat(String nomUsuari, String pwd)
		{
			Usuari usuari = new Usuari();
			usuari.setNomUsuari(nomUsuari);
			usuari.setPassword(pwd);
			
			ctrl.getUsuari(usuari);
			
			
			
			String login = "popo";
			String password = "popo";
			return ((nomUsuari.compareTo(login)==0)&&(pwd.compareTo(password)==0));		
		}	
		

		
		private void sortirAction() throws ServletException, IOException {
			urlExit="/intranet/login.jsp";
			request.getSession().removeAttribute("NomUsuari");
			request.getSession().invalidate();
			rd = getServletContext().getRequestDispatcher(urlExit);
			rd.forward(request, response);
		}
	}