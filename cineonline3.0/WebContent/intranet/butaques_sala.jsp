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
	
	String butaquesTaula= "<form name='frmButaca' action='GestioSalesServlet'>";
	butaquesTaula+= "<table cellspacing=0 cellpadding=0 border=0 bgcolor=yellow>\n";
	butaquesTaula+= "<input type='Hidden' name='idSala' value='"+ sala.getId() + "'>\n";
	butaquesTaula+= "<input type='Hidden' name='accio' value='modificarEstatsButaques'>\n";
	String tipusButaca = "butaca_disponible";
	//String llista_butaques[];
	

	while(itBut.hasNext()){


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
	butaquesTaula+= "<input type='Submit' name='bt_accio' value='modificar butaques' class='boto_accio' />\n";
	butaquesTaula+= "</form>\n";
	
%>
<%= butaquesTaula %>
