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

		private void modificarUsuariAction() throws ServletException, IOException {
			urlExit="/intranet/default.jsp";
			/* Atributs que agafo de la jsp per a fer l'accio
			nouNomUsuari
			nouPswUsuari
			nouRolUsuari
			*/
			
			/*Ho agafo de sessio, no m'ho passa la jsp, pq el usuari ja tindria d'estar validat*/
			String userLogin = (String) request.getSession().getAttribute("nomUsuari");
			String userRol = (String) request.getSession().getAttribute("rol");
			
			
			if(userLogin!=null && userRol!=null && !userLogin.equals("") && !userRol.equals("")){
				try {
					if(userRol.equals("0")){
						String idUsuari = request.getParameter("idUsuari");
						String newNomUsuari = request.getParameter("nouNomUsuari");
						String newPswUsuari = request.getParameter("nouPswUsuari");
						String newRolUsuari = request.getParameter("nouRolUsuari");
						
						if(idUsuari!=null && newNomUsuari!=null && newPswUsuari!=null && newRolUsuari!=null &&
								!idUsuari.equals("") && !newNomUsuari.equals("") && !newPswUsuari.equals("") && !newRolUsuari.equals("")){
							
							Usuari usuari = new Usuari();
							usuari.setAll(Integer.parseInt(idUsuari), newNomUsuari, newPswUsuari, Integer.parseInt(newRolUsuari));
							ctrl.modificarUsuari(usuari);
							
							llistarUsuarisAction();
							
						}else{
							throw new ControladorException("Els camps no poden ser buits");
						}
					}else{
						RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=No tens suficients " +
								"privilègis per a realitzar l'acció");
					    rd.forward(request, response);	
					}
							
				} catch (ControladorException e) {
				    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
				    rd.forward(request, response);
				}
			}else{
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlErrorLog+"? Cal que et loguegis per a fer aquesta acció");
			    rd.forward(request, response);
			}
		}

		private void afegirUsuariAction() throws ServletException, IOException {
			urlExit="/intranet/default.jsp";
			
			/* Atributs que agafo de la jsp per a fer l'accio
			nouNomUsuari
			nouPswUsuari
			nouRolUsuari
			*/
			
			/*Ho agafo de sessio, no m'ho passa la jsp, pq el usuari ja tindria d'estar validat*/
			String userLogin = (String) request.getSession().getAttribute("nomUsuari");
			String userRol = (String) request.getSession().getAttribute("rol");
			
			
			if(userLogin!=null && userRol!=null && !userLogin.equals("") && !userRol.equals("")){
				try {
					if(userRol.equals("0")){
						String newNomUsuari = request.getParameter("nouNomUsuari");
						String newPswUsuari = request.getParameter("nouPswUsuari");
						String newRolUsuari = request.getParameter("nouRolUsuari");
						
						if(newNomUsuari!=null && newPswUsuari!=null && newRolUsuari!=null &&
								!newNomUsuari.equals("") && !newPswUsuari.equals("") && !newRolUsuari.equals("")){
							
							Usuari usuari = new Usuari();
							usuari.setAll(-1, newNomUsuari, newPswUsuari, Integer.parseInt(newRolUsuari));
							ctrl.afegirUsuari(usuari);
							
							llistarUsuarisAction();
							
						}else{
							throw new ControladorException("Els camps no poden ser buits");
						}
					}else{
						RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=No tens suficients " +
								"privilègis per a realitzar l'acció");
					    rd.forward(request, response);	
					}
							
				} catch (ControladorException e) {
				    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
				    rd.forward(request, response);
				}
			}else{
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlErrorLog+"? Cal que et loguegis per a fer aquesta acció");
			    rd.forward(request, response);
			}
						
		}

		private void llistarUsuarisAction() throws ServletException, IOException {
			urlExit="/intranet/default.jsp";
			/* Atributs que agafo de la jsp per a fer l'accio
			 * CAP
			 */
			
			/*Ho agafo de sessio, no m'ho passa la jsp, pq el usuari ja tindria d'estar validat*/
			String userLogin = (String) request.getSession().getAttribute("nomUsuari");
			String userRol = (String) request.getSession().getAttribute("rol");
			
			
			if(userLogin!=null && userRol!=null && !userLogin.equals("") && !userRol.equals("")){
				try {
					Vector llistaUsuaris;
					if(userRol.equals("0")){
						llistaUsuaris = ctrl.getUsuaris();
					}else{
						llistaUsuaris = ctrl.getUsuarisRolInf(Integer.parseInt("rol"));
					}
							
					request.getSession().setAttribute("llistaUsuaris", llistaUsuaris);		
					
				    rd = getServletContext().getRequestDispatcher(urlExit);
				    rd.forward(request, response);
				    
				} catch (ControladorException e) {
				    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
				    rd.forward(request, response);
				}
			}else{
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlErrorLog+"? Cal que et loguegis per a fer aquesta acció");
			    rd.forward(request, response);
			}
			
			
			
		}

		private void accedirAction() throws ServletException, IOException {
			/* Atributs que agafo de la jsp per a fer l'accio
			nomUsuari
			psw
			*/

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
				page=urlErrorLog+"?missatge="+missatge;
				
			}
			rd = getServletContext().getRequestDispatcher(page);
			rd.forward(request, response);
			
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
				if(usuari!=null && usuari.validarUsuari(nomUsuari,pwd)){
					request.getSession().setAttribute("nomUsuari", usuari.getLogin());
					request.getSession().setAttribute("rol", ""+usuari.getRol());
					return true;
				}else return false;
				
			} catch (ControladorException e) {
				rd = getServletContext().getRequestDispatcher(urlError+e.getMessage());
				rd.forward(request,response);
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