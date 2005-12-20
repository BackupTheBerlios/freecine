package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.Pelicula;
import gestioCinema.gestioSales.Butaca;
import gestioCinema.gestioSessions.ButacaSessio;
import gestioCinema.gestioSessions.Sessio;
import gestioCompraReserva.CompraReserva;
import gestioCompraReserva.ControladorCompresReserves;

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
 * Servlet implementation class for Servlet: GestioPeliculesServlet
 *
 */
 public class GestioClientServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String urlExit="/extranet/consola.jsp";
	private String urlError="/extranet/error.jsp";
	private RequestDispatcher rd;
	private ControladorCompresReserves ctrl;
	private SimpleDateFormat sdf;
	
	public GestioClientServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		rd = getServletContext().getRequestDispatcher(urlError+"?error=Accéss denegat!!! \n");
	    rd.forward(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		String accio = request.getParameter("accio");
		
		try {
			
			ctrl = new ControladorCompresReserves();
			if(accio.equals("goCineonline")) goWeb("/extranet/index.jsp");
			else if(accio.equals("llistarPelicules")) llistarAction();
			else if(accio.equals("detallPelicula")) detallPeliculaAction();
			else if(accio.equals("goCartellera")) goWeb("/extranet/cartellera.jsp");
			else if(accio.equals("goEntrades_online")) goWeb("/extranet/entrades_online.jsp");
			else if(accio.equals("goTarifes")) goWeb("/extranet/tarifes.jsp");
			else if(accio.equals("goOnsom")) goWeb("/extranet/onsom.jsp");
			else if(accio.equals("goServeis")) goWeb("/extranet/serveis.jsp");
			else if(accio.equals("llistarSessionsCartellera")) llistarSessionsCartelleraAction();
			else if(accio.equals("venda")) vendaAction();
			else if(accio.equals("cerca")) cercaAction();
			else if(accio.equals("continuar venda")) continuarVendaAction();
			else if(accio.equals("confirmar")) confirmarAction();
			else if(accio.equals("cerca pelicula")) cercaPeliculesAction();
			
			
			else{
				rd = getServletContext().getRequestDispatcher(urlError+"?error=Accés Fallit");
			    rd.forward(request, response);
			    
			}
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
		}
	}
	
	private void detallPeliculaAction() throws ServletException, IOException {
		urlExit="/extranet/fitxa_sinopsis.jsp";
		int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
		try {
			
			Pelicula pelicula = ctrl.getPelicula(idPelicula);	
			System.err.println(pelicula);
			request.getSession().setAttribute("pelicula", pelicula);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		}
	}

	private void llistarAction() throws ServletException, IOException {		
		urlExit="/extranet/sinopsis.jsp";		
		try {
			Vector llistaPelicules = ctrl.getPelicules();		
			request.getSession().setAttribute("llistaPelicules", llistaPelicules);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		}
		
	}
	
	private void llistarSessionsCartelleraAction() throws ServletException, IOException {
		urlExit="/extranet/cartellera.jsp";
		
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date data_actual = new Date();
			String str_data_actual = sdf.format(data_actual);
			
			/*
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(data_actual);*/
			

			Vector llistaSessions = ctrl.getSessionsCartellera(""+ str_data_actual);
			
			
			
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
	
	private void vendaAction() throws ServletException, IOException {
		urlExit="/extranet/venda.jsp";
		String idSessio = request.getParameter("idSessio");
		
		if(idSessio != null){
			try {
				
				Sessio sessio = ctrl.getSessio(Integer.parseInt(idSessio));				
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
	

	private void cercaAction() throws ServletException, IOException {
		urlExit="/extranet/cartellera.jsp";
		String dia= request.getParameter("dia");
		String mes= request.getParameter("mes");
		String anny= request.getParameter("anny");
		String data = anny+"-"+mes+"-"+dia;
		try {
			Vector llistaSessions = ctrl.getSessionsPerData(""+ data);

			request.getSession().setAttribute("llistaSessions", llistaSessions);
			request.getSession().setAttribute("dataActual",data);
			System.err.println("[getSessioCartellera]"+llistaSessions);
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
   
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		}
		
	}
	
	private void continuarVendaAction() throws ServletException, IOException{
		urlExit="/extranet/ticket.jsp";
		String idSessio = request.getParameter("idSessio");
		String tipusVenda = request.getParameter("tipus_venda");
		boolean pagada = (tipusVenda.equals("pagada"));
		
		try{
		
			if(idSessio !=null){
				Sessio sessio = ctrl.getSessio(Integer.parseInt(idSessio));
				if(sessio!=null){
					
					Vector butaquesSessio = sessio.getButaquesSessio();
					Iterator itBut = butaquesSessio.iterator();
					Vector butCR= new Vector();
					
					while(itBut.hasNext()){
						int id =((Butaca)itBut.next()).getNumButaca();
						String check = "cekbutaca_" + id;
						String seleccionada = request.getParameter(check);
						if (seleccionada != null)
						{
							ButacaSessio but = ctrl.getButacaSessio(Integer.parseInt(idSessio), id);
							but.setPagada(pagada);
							butCR.add(but);
						}
					}
					
					if(butCR.size()==0){
						RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=No ha seleccionat cap butaca");
					    rd.forward(request, response);
					}
					else
					{
						request.getSession().setAttribute("butaquesSelec", butCR);
						request.getSession().setAttribute("sessio", sessio);
						request.getSession().setAttribute("tipusVenda", tipusVenda);
			
						RequestDispatcher rd = getServletContext().getRequestDispatcher(urlExit);
						rd.forward(request, response);
					}
					
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=No existeix la sessio");
				    rd.forward(request, response);
				}
			
		    
		    
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error=idSessio null");
			    rd.forward(request, response);
			}
		}catch(ControladorException e){
			rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			rd.forward(request,response);
		}
	}
	
	private void confirmarAction() throws ServletException, IOException {
		urlExit="/extranet/finalitzar.jsp";
		Sessio sessio = (Sessio) request.getSession().getAttribute("sessio");
		String tipusVenda = request.getParameter("tipus_venda");
		Vector butSelec = (Vector) request.getSession().getAttribute("butaquesSelec");
		boolean pagada = (tipusVenda.equals("compra"));
		
		CompraReserva cr = new CompraReserva();
		//String codi = sessio.generarCodi()+"tv-"+tipusVenda.charAt(0)+ (int)(Math.random()*10000);
		String codi = ""+sessio.generarCodi()+ (int)(Math.random()*10000);
		Iterator itBut= butSelec.iterator();
		Vector vCR = new Vector();
		while(itBut.hasNext()){
			ButacaSessio bs = (ButacaSessio) itBut.next();
			cr = new CompraReserva();
			cr.setAll(-1,pagada,codi,sessio.donaHora(),sessio, bs);
			vCR.add(cr);
		}
		
		try {
			itBut= vCR.iterator();
			while(itBut.hasNext()){
				CompraReserva crva = (CompraReserva) itBut.next();
				ctrl.afegirCompraReserva(crva);
			}
			
			
			request.getSession().setAttribute("codiClau", codi);
			request.getSession().setAttribute("tipusVenda", tipusVenda);
			rd = getServletContext().getRequestDispatcher(urlExit);
			rd.forward(request,response);
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			rd.forward(request,response);
		}
		
		
		
	}
	
	private void cercaPeliculesAction() throws ServletException, IOException{

		urlExit="/extranet/sinopsis.jsp";
		String titol = request.getParameter("titol");
		String director = request.getParameter("director");
		String any = request.getParameter("any");
		
		try {
		
			Vector llistaPelicules = ctrl.cercaPelicules(titol,director,any);		
			request.getSession().setAttribute("llistaPelicules", llistaPelicules);		
			
		    rd = getServletContext().getRequestDispatcher(urlExit);
		    rd.forward(request, response);
		    
		} catch (ControladorException e) {
		    RequestDispatcher rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
		    rd.forward(request, response);
		}
	}

	private void goWeb(String url) throws ServletException, IOException{		
		rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
}