package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioSales.Butaca;
import gestioCinema.gestioSessions.ControladorSessions;
import gestioCinema.gestioSessions.Sessio;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: GestioSessionsServlet
 *
 */
 public class GestioCompresReservesServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String urlExit="/intranet/default.jsp";
	private String urlError="/intranet/error.jsp";
	private RequestDispatcher rd;
	private ControladorCompresReserves ctrlCR;
	private SimpleDateFormat sdf;
	
	public GestioCompresReservesServlet() {
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
			if(accio.equals("comprar")) comprarAction();
			
			else{
			    rd = getServletContext().getRequestDispatcher(urlError+"?error=Acció incorrecta");
			    rd.forward(request, response);
			}
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
			rd.forward(request,response);
		}
	}   	 



	private void comprarAction() throws ServletException, IOException {
		urlExit="/intranet/ticket.jsp";
		
		try {
			
			String idSessio = (String) request.getAttribute("idSessio");
			String tipusVenda = (String) request.getAttribute("tipus_venda");
			Vector butaques = sala.getButaques();
			Iterator itBut = butaques.iterator();
			
			while(itBut.hasNext()){
				int id =((Butaca)itBut.next()).getNumButaca();
				String check = "cekbutaca_" + id;
				String noOperativa = request.getParameter(check);
				if (noOperativa == null)
				{
					ctrlSales.setButacaOperativa(idSala,""+id,"true");
				}
				else
				{
					ctrlSales.setButacaOperativa(idSala,""+id,"false");
				}
				
			}
			
			
			
			
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
} 