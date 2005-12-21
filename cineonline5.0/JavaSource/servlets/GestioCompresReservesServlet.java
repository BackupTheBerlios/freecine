package servlets;

import gestioCinema.ControladorException;
import gestioCinema.gestioSales.Butaca;
import gestioCinema.gestioSessions.ButacaSessio;
import gestioCinema.gestioSessions.Sessio;
import gestioCompraReserva.CompraReserva;
import gestioCompraReserva.ControladorCompresReserves;

import java.io.IOException;
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
			
			ctrlCR = new ControladorCompresReserves();
			if(accio.equals("continuar venda")) continuarVendaAction();
			else if(accio.equals("confirmar")) confirmarAction();
			else if(accio.equals("cerca entrades")) cercaEntradesAction();
			else if(accio.equals("lliurar")) lliurarEntradesAction();
			
			else{
			    rd = getServletContext().getRequestDispatcher(urlError+"?error=Acció incorrecta");
			    rd.forward(request, response);
			}
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error=No es pot connectar a la base de dades");
			rd.forward(request,response);
		}
	}

	private void lliurarEntradesAction() throws ServletException, IOException {
		urlExit="/intranet/entrades_online.jsp";
		String codiEntrega = request.getParameter("codi_entrega");		
		try {
			ctrlCR.setCompresReservesPagadaCodiEntrega(codiEntrega);	
			Vector llistaCR = ctrlCR.getCompresReservesCodiEntrega(codiEntrega);
			request.getSession().setAttribute("llistaCR", llistaCR);
			request.getSession().setAttribute("entrada",codiEntrega);
		
			rd = getServletContext().getRequestDispatcher(urlExit);
			rd.forward(request,response);
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			rd.forward(request,response);
		}
		
	}

	private void cercaEntradesAction() throws ServletException, IOException {
		urlExit="/intranet/entrades_online.jsp";
		
		
		
		try {
			String codiEntrega = request.getParameter("codi_entrega");
			if(codiEntrega==null || codiEntrega.equals("")){
				throw new ControladorException("El codi d'entrega està buit");
			}
			Vector llistaCR = ctrlCR.getCompresReservesCodiEntrega(codiEntrega);
			request.getSession().setAttribute("llistaCR", llistaCR);
			request.getSession().setAttribute("entrada",codiEntrega);
			
			rd = getServletContext().getRequestDispatcher(urlExit);
			rd.forward(request,response);
			
			
			
		} catch (ControladorException e) {
			rd = getServletContext().getRequestDispatcher(urlError+"?error="+e.getMessage());
			rd.forward(request,response);
		}
		
	}

	private void confirmarAction() throws ServletException, IOException {
		urlExit="/intranet/finalitzar.jsp";
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
				ctrlCR.afegirCompraReserva(crva);
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

	private void continuarVendaAction() throws ServletException, IOException{
		urlExit="/intranet/ticket.jsp";
		String idSessio = request.getParameter("idSessio");
		String tipusVenda = request.getParameter("tipus_venda");
		boolean pagada = (tipusVenda.equals("pagada"));
		
		try{
		
			if(idSessio !=null){
				Sessio sessio = ctrlCR.getSessio(Integer.parseInt(idSessio));
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
							ButacaSessio but = ctrlCR.getButacaSessio(Integer.parseInt(idSessio), id);
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
}
		