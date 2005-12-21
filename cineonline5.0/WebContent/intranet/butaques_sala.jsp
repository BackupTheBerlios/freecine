<%@ page import="gestioCinema.gestioSales.Sala" %>
<%@ page import="gestioCinema.gestioSales.Butaca" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%
	
	Sala sala = (Sala)session.getAttribute("sala");	

	Vector butaques = sala.getButaques();
	Iterator itBut = butaques.iterator();
	
	int numMaxColumnes = sala.getNumMaxColumnes();
	int numMaxFiles = sala.getNumMaxFiles();
	
	String butaquesTaula= "";
	butaquesTaula+= "<table cellspacing=0 cellpadding=0 border=0 style='width:auto;'>\n";
	butaquesTaula+= "<tr><td><div id='pantalla'>PANTALLA</div></td></tr>\n";
	butaquesTaula+= "<tr><td><br /></td></tr>\n";
	butaquesTaula+= "</table>\n";
	butaquesTaula+= "<table cellspacing=0 cellpadding=0 border=0 style='width:auto;'>\n";
	String tipusButaca = "butaca_disponible";

	while(itBut.hasNext())
	{
		Butaca but = (Butaca) itBut.next();
		String sel = "";
		if(but.getNumColumna()==0){
			butaquesTaula+= "<tr>\n";
		}
		
		if ( but.getOperativa())
		{				
			tipusButaca = "butaca_disponible";
			
		}
		else
		{
			tipusButaca = "butaca_no_operativa";
			sel ="checked";
		}
		
		butaquesTaula+= "<td><input type='Checkbox' name='cekbutaca_" + but.getNumButaca() + "' class='check' "+ sel +" /></td>\n";
		
		if(but.getNumColumna() == numMaxColumnes-1){
			butaquesTaula+= "</tr>\n";
		}
		
	}
	
	butaquesTaula+= "</table><br />\n";
	butaquesTaula+= "<form name='frmmodsala' actio='GestioSalesServlet'>\n";
	butaquesTaula+= "<input type='Hidden' name='accio' value='modificarEstatsButaques' />\n";
	butaquesTaula+= "<input type='Submit' name='bt_accio' value='modificar butaques' class='boto_accio' />\n";
	butaquesTaula+= "</form>\n";
	
%>
<%= butaquesTaula %>
