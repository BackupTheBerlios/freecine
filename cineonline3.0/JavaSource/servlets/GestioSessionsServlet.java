package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioSessions.ControladorSessions;
import gestioCinema.gestioSessions.Sessio;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private SimpleDateFormat sdf;
	
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
			else if(accio.equals("eliminar")) eliminarSessioAction();
			else if(accio.equals("llistarSessionsCartellera")) llistarSessionsCartelleraAction();
			else if(accio.equals("venda")) vendaAction();
			
			else{
			    rd = getServletContext().getRequestDispatcher(urlError+"?error=Acció incorrecta");
			    rd.forward(request, response);
			}
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
			rd.forward(request,response);
		}
	}   	 

	private void vendaAction() throws ServletException, IOException {
		urlExit="/intranet/venda.jsp";
		String idSessio = request.getParameter("idSessio");
		
		if(idSessio != null){
			try {
				
				Sessio sessio = ctrlSessio.getSessio(Integer.parseInt(idSessio));				
				request.getSession().setAttribute("sessio", sessio);				
				System.err.println(sessio.getPelicula().getTitol());
				
			    rd = getServletContext().getRequestDispatcher(urlExit);
			    rd.forward(request, response);
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSessio null");
		    rd.forward(request, response);
		}
		
	}

	private void llistarSessionsCartelleraAction() throws ServletException, IOException {
		urlExit="/intranet/cartellera.jsp";
		
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date data_actual = new Date();
			String str_data_actual = sdf.format(data_actual);
			
			/*
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(data_actual);*/
			

			Vector llistaSessions = ctrlSessio.getSessionsCartellera(""+ str_data_actual);
			
			
			
			request.getSession().setAttribute("llistaSessions", llistaSessions);
			request.getSession().setAttribute("dataActual",str_data_actual);
			System.err.println("[getSessioCartellera]"+llistaSessions);
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
   
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		}
		
	}

	private void novaSessioAction() throws ServletException, IOException {
		urlExit="/intranet/fitxa_sessio.jsp";

		request.getSession().removeAttribute("sessio");
		try {
			
			
			Vector llistaSales = ctrlSessio.getSales();
			Vector llistaPelicules = ctrlSessio.getPelicules();
			
			request.getSession().setAttribute("llistaPelicules", llistaPelicules);
			request.getSession().setAttribute("llistaSales", llistaSales);
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
			
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
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
				
			    rd = getServletContext().getRequestDispatcher(urlExit);
			    rd.forward(request, response);
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSessio null");
		    rd.forward(request, response);
		}
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
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
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
		String horaInici = request.getParameter("horaInici");
		String preu = request.getParameter("preu");
		Sessio sessio = new Sessio();
		
		urlError="/intranet/error.jsp";

		try {
			
			
			System.err.println("GestioSessionsServlet data "+data);
			System.err.println("GestioSessionsServlet horaInici "+horaInici);
			System.err.println("GestioSessionsServlet idPelicula " +idPelicula);
			System.err.println("GestioSessionsServlet preu "+preu);
			System.err.println("GestioSessionsServlet idSala "+idSala);
			
			if(data!=null && !data.equals("") && horaInici!=null && !horaInici.equals("")){
				sessio.setDataHora(data+" "+horaInici+":00");
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=data i hora incorrectes");
			    rd.forward(request, response);
			}
			
			if(idPelicula!=null){
				sessio.setPelicula(ctrlSessio.getPelicula(Integer.parseInt(idPelicula)));
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idPelicula no pot ser buida");
			    rd.forward(request, response);
			}
			
			if(preu!=null && !preu.equals("")){
				sessio.setPreu(Double.parseDouble(preu));
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=preu incorrecte");
			    rd.forward(request, response);
			}
			
			if(idSala!=null){
				sessio.setSala(ctrlSessio.getSala(Integer.parseInt(idSala)));
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSala no pot ser buida");
			    rd.forward(request, response);
			}

			
			ctrlSessio.afegirSessio(sessio);		
			request.getSession().removeAttribute("sessio");

		    llistarSessionsAction();
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
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
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSessio null");
		    rd.forward(request, response);
		}
	}
}
 