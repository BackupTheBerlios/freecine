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
			else if(accio.equals("cerca")) cercaAction();
			else if(accio.equals("cerca sessio")) cercaSessioAction();
			
			
			else{
			    rd = getServletContext().getRequestDispatcher(urlError+"?error=Acció incorrecta");
			    rd.forward(request, response);
			}
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
			rd.forward(request,response);
		}
	}   	 

	private void cercaSessioAction() throws ServletException, IOException {
		urlExit="/intranet/sessions.jsp";

		try {
			String dia= request.getParameter("dia");
			String mes= request.getParameter("mes");
			String anny= request.getParameter("anny");
			if(dia.length()==1) dia="0"+dia;
			if(mes.length()==1) mes="0"+mes;
			String data = anny+"-"+mes+"-"+dia;
			Vector llistaSessions = ctrlSessio.getSessionsPerData(""+ data);

			request.getSession().setAttribute("llistaSessions", llistaSessions);
			request.getSession().setAttribute("dataActual",data);
			System.err.println("[getSessioCartellera]"+llistaSessions);
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
   
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		} catch (NumberFormatException e1) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=En alguns camps has introduit texte en comptes de numeros ");
		    rd.forward(request, response);
		}
		
	}

	private void cercaAction() throws ServletException, IOException {
		urlExit="/intranet/cartellera.jsp";
		
		try {
			String dia= request.getParameter("dia");
			String mes= request.getParameter("mes");
			String anny= request.getParameter("anny");
			
			if(dia.length()==1) dia="0"+dia;
			if(mes.length()==1) mes="0"+mes;
			String data = anny+"-"+mes+"-"+dia;
			Vector llistaSessions = ctrlSessio.getSessionsPerData(""+ data);

			request.getSession().setAttribute("llistaSessions", llistaSessions);
			request.getSession().setAttribute("dataActual",data);
			System.err.println("[getSessioCartellera]"+llistaSessions);
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
   
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		} catch (NumberFormatException e1) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=En alguns camps has introduit texte en comptes de numeros ");
		    rd.forward(request, response);
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
		urlExit="/intranet/sessions.jsp";
		
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
			
			if(data!=null && !data.equals("") && data.length()==10 && horaInici!=null && !horaInici.equals("")){
				if(horaInici.length()==5){
					sessio.setDataHora(data+" "+horaInici+":00");
				}else{
					sessio.setDataHora(data+" "+horaInici);
				}
			}else{
				throw new ControladorException("data i/o hora incorrectes");
			}
			
			if(idPelicula!=null){
				sessio.setPelicula(ctrlSessio.getPelicula(Integer.parseInt(idPelicula)));
			}else{
				throw new ControladorException("idPelicula no pot ser buida");
			}
			
			if(preu!=null && !preu.equals("")){
				sessio.setPreu(Double.parseDouble(preu));
			}else{
				throw new ControladorException("preu incorrecte");
			}
			
			if(idSala!=null && !preu.equals("")){
				sessio.setSala(ctrlSessio.getSala(Integer.parseInt(idSala)));
			}else{
				throw new ControladorException("idSala no pot ser buida");
			}

			
			ctrlSessio.afegirSessio(sessio);		
			request.getSession().removeAttribute("sessio");

		    llistarSessionsAction();
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		} catch (NumberFormatException e1) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=En alguns camps has introduit texte en comptes de numeros ");
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
 