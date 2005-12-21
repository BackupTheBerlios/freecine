package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioUsuaris.ControladorUsuaris;
import gestioCinema.gestioUsuaris.Usuari;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: GestioUsuarisServlet
 *
 */
 public class GestioUsuarisServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	   
		private static final long serialVersionUID = 1L;
		private HttpServletRequest request;
		private HttpServletResponse response;
		private String urlExit="/intranet/default.jsp";
		private String urlErrorLog="/intranet/login.jsp?missatge=";
		private String urlError="/intranet/error.jsp";
		private RequestDispatcher rd;
		private ControladorUsuaris ctrl;
		
		public GestioUsuarisServlet() {
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
				
				ctrl = new ControladorUsuaris();
				if(accio.equals("accedir")) accedirAction();
				else if(accio.equals("sortir")) sortirAction();
				else if(accio.equals("llistar usuaris")) llistarUsuarisAction();
				else if(accio.equals("detall usuari")) detallUsuarisAction();
				else if(accio.equals("afegir usuari")) afegirUsuariAction();
				else if(accio.equals("nou usuari")) nouUsuariAction();
				else if(accio.equals("modificar")) modificarUsuariAction();
				else if(accio.equals("eliminar")) eliminarUsuariAction();
				
				else{
					rd = getServletContext().getRequestDispatcher(urlError+"?error=Accés Fallit");
				    rd.forward(request, response);
				    
				}
				
				
				
			} catch (ControladorException e) {
				rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
			}
		}   	 

		private void nouUsuariAction() throws ServletException, IOException {
			urlExit="/intranet/fitxa_usuari.jsp";
			
			HttpSession session = request.getSession();
			
			Vector rols = ctrl.getRols();
			
			session.setAttribute("rols", rols);

			request.getSession().removeAttribute("usuari");		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
			
		}

		private void detallUsuarisAction() throws ServletException, IOException {
			urlExit="/intranet/fitxa_usuari.jsp";
			int idUsuari = Integer.parseInt(request.getParameter("idUsuari"));
			try {
				
				HttpSession session = request.getSession();
				
				Usuari usuari = ctrl.getUsuari(idUsuari);
				
				session.setAttribute("usuari", usuari);
				
				Vector rols = ctrl.getRols();
							
				session.setAttribute("rols", rols);
				
				
			    rd = getServletContext().getRequestDispatcher(urlExit);
			    rd.forward(request, response);
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
			
		}

		private void eliminarUsuariAction() throws ServletException, IOException {
			urlExit="/intranet/default.jsp";
			/* Atributs que agafo de la jsp per a fer l'accio
			idNouUsuari
			*/
			
			
			
			try {
				
				String idUsuari = request.getParameter("idUsuari");
				
				if(idUsuari!=null && !idUsuari.equals("") ){
					
					ctrl.eliminarUsuari(idUsuari);
					
					llistarUsuarisAction();
					
				}else{
					throw new ControladorException("El id no es correcte");
				}
				
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
			
		}

		private void modificarUsuariAction() throws ServletException, IOException {
			urlExit="/intranet/usuaris.jsp";
			/* Atributs que agafo de la jsp per a fer l'accio
			*/
			

			try {
				
				String idUsuari = request.getParameter("idUsuari");
				String newNomUsuari = request.getParameter("login");
				String newPswUsuari = request.getParameter("password");
				String newRolUsuari = request.getParameter("tipus");
				
				if(idUsuari!=null && newNomUsuari!=null && newPswUsuari!=null && newRolUsuari!=null &&
						!idUsuari.equals("") && !newNomUsuari.equals("") && !newPswUsuari.equals("") && !newRolUsuari.equals("")){
					
					Usuari usuari = new Usuari();
					usuari.setAll(Integer.parseInt(idUsuari), newNomUsuari, newPswUsuari, Integer.parseInt(newRolUsuari));
					ctrl.modificarUsuari(usuari);
					
					llistarUsuarisAction();
					
				}else{
					throw new ControladorException("Els camps no poden ser buits");
				}
				
						
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
			
		}

		private void afegirUsuariAction() throws ServletException, IOException {
			urlExit="/intranet/usuaris.jsp";
				
			try {

				String newNomUsuari = request.getParameter("login");
				String newPswUsuari = request.getParameter("password");
				String newRolUsuari = request.getParameter("tipus");
				
				if(newNomUsuari!=null && newPswUsuari!=null && newRolUsuari!=null &&
						!newNomUsuari.equals("") && !newPswUsuari.equals("") && !newRolUsuari.equals("")){
					
					Usuari usuari = new Usuari();
					usuari.setAll(-1, newNomUsuari, newPswUsuari, Integer.parseInt(newRolUsuari));
					ctrl.afegirUsuari(usuari);
					
					llistarUsuarisAction();
					
				}else{
					throw new ControladorException("Els camps no poden ser buits");
				}
						
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}						
		}

		private void llistarUsuarisAction() throws ServletException, IOException {
			
			urlExit="/intranet/usuaris.jsp";
			
			try {
				Vector llistaUsuaris = (Vector) ctrl.getUsuaris();
				System.err.println(llistaUsuaris);
				request.getSession().setAttribute("llistaUsuaris", llistaUsuaris);		
				
			    rd = getServletContext().getRequestDispatcher(urlExit);
			    rd.forward(request, response);
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}			
		}

		private void accedirAction() throws ServletException, IOException {
			/* Atributs que agafo de la jsp per a fer l'accio
			nomUsuari
			psw
			*/
			urlExit="/intranet/default.jsp";

			String missatge = "";
			boolean autenticat = false;
			String nomUsuari = request.getParameter("nomUsuari");
			String psw = request.getParameter("pwd");

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
								autenticat = true;
								missatge = "Benvingut a l'administració " + nomUsuari;
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
				rd = getServletContext().getRequestDispatcher(urlExit);
				rd.forward(request, response);
				
			}
			else
			{
				page=urlErrorLog+missatge;
				rd = getServletContext().getRequestDispatcher(page);
				rd.forward(request, response);
			}

			
		}
		
		public boolean entradaPlena(String entrada)
		{
			return (entrada!="");
		}

		public boolean identificat(String nomUsuari, String pwd) throws ServletException, IOException
		{	
			Usuari usuari = new Usuari();
			usuari.setLogin(nomUsuari);
			usuari.setPassword(pwd);
			
			try {
				usuari = ctrl.getUsuari(usuari);
				System.err.println("Usuari validat [nom usuari: "+usuari.getLogin()+", rol: "+usuari.getRol()+"]");
				request.getSession().setAttribute("nomUsuari", usuari.getLogin());
				request.getSession().setAttribute("rol", ""+usuari.getRol());
				return true;
			} catch (ControladorException e) {
				return false;	
			}
		}	
		

		
		private void sortirAction() throws ServletException, IOException {
			urlExit="/intranet/login.jsp";
			request.getSession().removeAttribute("NomUsuari");
			request.getSession().removeAttribute("rol");
			request.getSession().invalidate();
			rd = getServletContext().getRequestDispatcher(urlExit);
			rd.forward(request, response);
		}
	}