package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioSales.Butaca;
import gestioCinema.gestioSales.ControladorSales;
import gestioCinema.gestioSales.Sala;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GestioSalesServlet
 *
 */

 public class GestioSalesServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String urlExit="/intranet/sales.jsp";
	private String urlError="/intranet/error.jsp";
	private RequestDispatcher rd;
	private ControladorSales ctrlSales;
	
	public GestioSalesServlet() {
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
		System.err.println("Sales accio "+ accio);
		
		try {	
			ctrlSales = new ControladorSales();
			if(accio.equals("llistarSales")) llistarSalesAction();
			else if(accio.equals("detallSala")) detallSalaAction();
			else if(accio.equals("afegir")) afegirSalaAction();
			else if(accio.equals("novaSala")) novaSalaAction();
			else if(accio.equals("modificar")) modificarSalaAction();
			else if(accio.equals("eliminar")) eliminarSalaAction();
			else if(accio.equals("modificarEstatsButaques")) modificarEstatsButaquesAction();
			else{
			    rd = getServletContext().getRequestDispatcher(urlError+"?error=Acció incorrecta");
			    rd.forward(request, response);
			}
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
			rd.forward(request,response);
		}
	}   	 

	private void novaSalaAction() throws ServletException, IOException {
		urlExit="/intranet/fitxa_sala.jsp";

		request.getSession().removeAttribute("sala");		
		
	    rd = getServletContext().getRequestDispatcher(urlExit);
	    rd.forward(request, response);
		
	}

	private void llistarSalesAction() throws ServletException, IOException{
		/*
		 * Crida al controlador de pelicules la funcio getSales
		 * on obté un vector de pelicules.
		 * Posa el vector de pelicules en un atribut de sessio
		 * Per que la jsp l'agafi session.getAttribute("llistaSales")
		 * i agafi les dades que vulgui de la sala
		 */
		urlExit="/intranet/sales.jsp";
		
		try {
			System.err.println("llistaSales");
			Vector llistaSales = ctrlSales.getSales();		
			request.getSession().setAttribute("llistaSales", llistaSales);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		}
	}
	
	private void detallSalaAction() throws ServletException, IOException{
		/*
		 * Mitjançant la id de la sala cridem la funcio 
		 * getSala i aquest ens retorna el objecte de la sala
		 * El posem a sessio amb l'atribut de 'sala' 
		 */
		urlExit="/intranet/fitxa_sala.jsp";
		String idSala = request.getParameter("idSala");
		
		if(idSala != null){
			try {
				
				Sala sala = ctrlSales.getSala(Integer.parseInt(idSala));		
				request.getSession().setAttribute("sala", sala);
				
			    rd = getServletContext().getRequestDispatcher(urlExit);
			    rd.forward(request, response);
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSala null");
		    rd.forward(request, response);
		}
	}
	
	private void afegirSalaAction() throws ServletException, IOException{
		/*
		 * Afagar les dades de la sala, les posa en Sala
		 * Comprova que el format de dades sigui correcte
		 * Executa al controlador de sala afegirPelciual i li pasa
		 * com a parametre la sala
		 */
		
		try {
			String nomSala = request.getParameter("nomSala");
			String numMaxColumnes = request.getParameter("numMaxColumnes");
			String numMaxFiles	= request.getParameter("numMaxFiles");
			String descripcio = request.getParameter("descripcio");
			Sala sala = new Sala();
			
			if(nomSala!=null && !nomSala.equals("")){
				sala.setNomSala(nomSala);
			}else{
				throw new ControladorException("nom sala no pot ser buit");
			}
		
			if(numMaxColumnes!=null && Integer.parseInt(numMaxColumnes)>0){
				sala.setNumMaxColumnes(Integer.parseInt(numMaxColumnes));
			}else{
				throw new ControladorException("el numero de columnes te de ser sueperior a 0");
			}
			
			if(numMaxFiles!=null && Integer.parseInt(numMaxFiles)>0){
				sala.setNumMaxFiles(Integer.parseInt(numMaxFiles));
			}else{
				throw new ControladorException("el numero de files te de ser sueperior a 0");
			}
			
			if(descripcio!=null){
				sala.setDescripcio(descripcio);
			}

			ctrlSales.afegirSala(sala);		
			request.getSession().removeAttribute("sala");

		    llistarSalesAction();
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		} catch (NumberFormatException e1){
			 RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=En alguns camps has introduït texte en comptes de números");
			 rd.forward(request, response);
		}
		
	}
	
	private void eliminarSalaAction() throws ServletException, IOException{
		/*
		 * Mitjançant la id de la sala cridem la funcio 
		 * eliminarSala i aquest ens retorna el objecte de la sala
		 * I anem a l'accio per anar a pelicules.
		 */
		String idSala = request.getParameter("idSala");
		
		if(idSala != null){
			try {
				
				ctrlSales.eliminarSala(Integer.parseInt(idSala));		
				request.getSession().removeAttribute("sala");

			    llistarSalesAction();
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSala null");
		    rd.forward(request, response);
		}
	}

	private void modificarSalaAction() throws ServletException, IOException{
		/*
		 * Afagar les dades de la sala, les posa en Sala
		 * Comprova que el format de dades sigui correcte
		 * Executa al controlador de sala modificarPelciual i li pasa
		 * com a parametre la sala i li retorna el mateix objecte actualitzat
		 */
		
		try {
			String idSala = request.getParameter("idSala");
			String nomSala = request.getParameter("nomSala");
			String numMaxColumnes = request.getParameter("numMaxColumnes");
			String numMaxFiles	= request.getParameter("numMaxFiles");
			String descripcio = request.getParameter("descripcio");
			Sala sala = new Sala();
			
			if(idSala!=null){
				
				sala.setId(Integer.parseInt(idSala));
			}else{
				throw new ControladorException("idSala no pot ser nula");
			}
		
			if(nomSala!=null && !nomSala.equals("")){
				sala.setNomSala(nomSala);
			}else{
				throw new ControladorException("nom sala no pot ser buit");
			}
		
			if(numMaxColumnes!=null && Integer.parseInt(numMaxColumnes)>0){
				sala.setNumMaxColumnes(Integer.parseInt(numMaxColumnes));
			}else{
				throw new ControladorException("el numero de columnes te de ser sueperior a 0");
			}
			
			if(numMaxFiles!=null && Integer.parseInt(numMaxFiles)>0){
				sala.setNumMaxFiles(Integer.parseInt(numMaxFiles));
			}else{
				throw new ControladorException("el numero de files te de ser sueperior a 0");
			}
			
			if(descripcio!=null){
				sala.setDescripcio(descripcio);
			}
			
			if(idSala != null){
				
					
					ctrlSales.modificarSala(sala);		
					request.getSession().removeAttribute("sala");
	
				    llistarSalesAction();
				    
				
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSala null");
			    rd.forward(request, response);
			}
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		} catch (NumberFormatException e1){
			 RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=En alguns camps has introduït texte en comptes de números");
			 rd.forward(request, response);
		}
		
	}
	
	private void modificarEstatsButaquesAction() throws ServletException, IOException{
		/*
		 * Afagar el idSala, el idButaca i el estat (0 o 1) i posa el estat adecuat a la butaca
		 * i actualitza a la base de dades.
		 */
		System.err.println("modificarEstatsButaques");
		urlExit="/intranet/fitxa_sala.jsp";
		String idSala = request.getParameter("idSala");
		
		if(idSala != null){
			try {
				System.err.println("gossa 1");
				Sala sala = ctrlSales.getSala(Integer.parseInt(idSala));		
				
				System.err.println("gossa 2");
				if(sala!=null){
					System.err.println("gossa 3");
					Vector butaques = sala.getButaques();
					Iterator itBut = butaques.iterator();
					System.err.println("gossa 4");
					while(itBut.hasNext()){
						System.err.println("gossa 5");
						int id =((Butaca)itBut.next()).getNumButaca();
						String check = "cekbutaca_" + id;
						String noOperativa = request.getParameter(check);
						if (noOperativa == null)
						{
							System.err.println("gossa 5 1");
							ctrlSales.setButacaOperativa(idSala,""+id,"true");
						}
						else
						{
							System.err.println("gossa 5 2");
							ctrlSales.setButacaOperativa(idSala,""+id,"false");
						}
						
					}
					System.err.println("gossa 6");
					ctrlSales.getSala(Integer.parseInt(idSala));		
					request.getSession().setAttribute("sala", sala);
					
				}
				System.err.println("gossa 7");
			    detallSalaAction();
			    
			} catch (ControladorException e) {
			    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			    rd.forward(request, response);
			}
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSala null");
		    rd.forward(request, response);
		}
	}
}