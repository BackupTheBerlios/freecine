package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioSessions.ControladorSessions;
import gestioCinema.gestioSessions.Sessio;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GestioSessionsServlet
 *
 */
 public class GestioSessionsServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String urlExit="/intranet/sessions.jsp";
	private String urlError="/intranet/error.jsp";
	private RequestDispatcher rd;
	private ControladorSessions ctrlSessio;
	
	public GestioSessionsServlet() {
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
			
			ctrlSessio = new ControladorSessions();
			if(accio.equals("llistarSessions")) llistarSessionsAction();
			else if(accio.equals("detallSessio")) detallSessioAction();
			else if(accio.equals("afegir")) afegirSessioAction();
			else if(accio.equals("novaSessio")) novaSessioAction();
			else if(accio.equals("modificar")) modificarSessioAction();
			else if(accio.equals("eliminar")) eliminarSessioAction();
			else{
			    rd = getServletContext().getRequestDispatcher(urlError+"?Error=Acció incorrecta");
			    rd.forward(request, response);
			}
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?Error=No es pot connectar a la base de dades");
			rd.forward(request,response);
		}
	}   	 

	private void novaSessioAction() throws ServletException, IOException {
		urlExit="/intranet/fitxa_sessio.jsp";

		request.getSession().removeAttribute("sessio");		
		
	    rd = getServletContext().getRequestDispatcher(urlExit);
	    rd.forward(request, response);
		
	}

	private void llistarSessionsAction() throws ServletException, IOException{
		/*
		 * Crida al controlador de sessions la funcio getSessions
		 * on obté un vector de sessions.
		 * Posa el vector de sessions en un atribut de sessio
		 * Per que la jsp l'agafi session.getAttribute("llistaSessio")
		 * i agafi les dades que vulgui de la sessio
		 */
		urlExit="/intranet/sessions.jsp";
		
		try {
			
			Vector llistaSessions = ctrlSessio.getSessions();
			request.getSession().setAttribute("llistaSessions", llistaSessions);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}
	
	private void detallSessioAction() throws ServletException, IOException{
		/*
		 * Mitjançant la id de la sessio cridem la funcio 
		 * getSessio i aquest ens retorna el objecte de la sessio
		 * El posem a sessio amb l'atribut de 'sessio' 
		 */
		urlExit="/intranet/fitxa_sessio.jsp";
		String idSessio = request.getParameter("idSessio");
		
		if(idSessio != null){
			try {
				
				Sessio sessio = ctrlSessio.getSessio(Integer.parseInt(idSessio));
				Vector llistaPelicules = ctrlSessio.getPelicules();
				Vector llistaSales = ctrlSessio.getSales();
				
				request.getSession().setAttribute("sessio", sessio);
				request.getSession().setAttribute("llistaPelicules", llistaPelicules);
				request.getSession().setAttribute("llistaSales", llistaSales);
				request.getSession().setAttribute("llistaSessio", sessio);
				
			    rd = getServletContext().getRequestDispatcher(urlExit);
			    rd.forward(request, response);
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=idSessio null");
		    rd.forward(request, response);
		}
	}
	
	private void afegirSessioAction() throws ServletException, IOException{
		/*
		 * Afagar les dades de la sessio, les posa en Sessio
		 * Comprova que el format de dades sigui correcte
		 * Executa al controlador de sala afegirPelciual i li pasa
		 * com a parametre la sala
		 */
		
		String data = request.getParameter("data");
		String idPelicula = request.getParameter("idPelicula");
		String idSala = request.getParameter("idSala");
		Sessio sessio = new Sessio();
		

		try {
		
			if(data!=null && !data.equals("")){
				sessio.setDataHora(data);
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=data no pot ser buida");
			    rd.forward(request, response);
			}
			
			if(idPelicula!=null && !data.equals("")){
				sessio.setPelicula(ctrlSessio.getPelicula(Integer.parseInt(idPelicula)));
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=idPelicula no pot ser buida");
			    rd.forward(request, response);
			}
			
			if(idSala!=null && !data.equals("")){
				sessio.setSala(ctrlSessio.getSala(Integer.parseInt(idSala)));
				sessio.setButaquesSessio(ctrlSessio.getButaquesSessio(Integer.parseInt(idSala)));
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=idSala no pot ser buida");
			    rd.forward(request, response);
			}

			
			ctrlSessio.afegirSessio(sessio);		
			request.getSession().removeAttribute("sessio");

		    llistarSessionsAction();
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
		    rd.forward(request, response);
		}
	}
	
	private void eliminarSessioAction() throws ServletException, IOException{
		/*
		 * Mitjançant la id de la sessio cridem la funcio 
		 * eliminarSessio i aquest ens retorna el objecte de la sessio
		 * I anem a l'accio per anar a pelicules.
		 */
		String idSessio = request.getParameter("idSessio");
		
		if(idSessio != null){
			try {
				
				ctrlSessio.eliminarSessio(idSessio);		
				request.getSession().removeAttribute("sessio");

			    llistarSessionsAction();
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=idSessio null");
		    rd.forward(request, response);
		}
	}

	private void modificarSessioAction() throws ServletException, IOException{
		/*
		 * Afagar les dades de la sessio, les posa en Sessio
		 * Comprova que el format de dades sigui correcte
		 * Executa al controlador de sessio modificarPelciual i li pasa
		 * com a parametre la sessio i li retorna el mateix objecte actualitzat
		 */
		
		String idSessio = request.getParameter("idSessio");
		String data = request.getParameter("data");
		String idPelicula = request.getParameter("idPelicula");
		String idSala = request.getParameter("idSala");
		Sessio sessio = new Sessio();
		
		

		
		if(idSessio != null){
			try {
				if(idSessio!=null){
					
					sessio.setId(Integer.parseInt(idSessio));
				}
			
				if(data!=null && !data.equals("")){
					sessio.setDataHora(data);
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=nom sessio no pot ser buit");
				    rd.forward(request, response);
				}
				
				if(idPelicula!=null && !data.equals("")){
					sessio.setPelicula(ctrlSessio.getPelicula(Integer.parseInt(idPelicula)));
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=nom sessio no pot ser buit");
				    rd.forward(request, response);
				}
				
				if(idSala!=null && !data.equals("")){
					sessio.setSala(ctrlSessio.getSala(Integer.parseInt(idSala)));
					sessio.setButaquesSessio(ctrlSessio.getButaquesSessio(Integer.parseInt(idSala)));
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=nom sessio no pot ser buit");
				    rd.forward(request, response);
				}

				
				ctrlSessio.modificarSessio(sessio);		
				request.getSession().removeAttribute("sessio");

			    llistarSessionsAction();
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?Error=idSessio null");
		    rd.forward(request, response);
		}
	}
}