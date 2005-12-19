<%@ page import="gestioCinema.gestioSessions.Sessio" %>
<%@ page import="gestioCinema.gestioSessions.ButacaSessio" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%
	
	Sessio sessio = (Sessio)session.getAttribute("sessio");	

	Vector butaques = sessio.getButaquesSessio();
	Iterator itBut = butaques.iterator();
	
	int numMaxColumnes = sessio.getSala().getNumMaxColumnes();
	int numMaxFiles = sessio.getSala().getNumMaxFiles();
	
	String butaquesTaula= "";
	butaquesTaula+= "<table cellspacing=0 cellpadding=0 border=0 bgcolor=yellow>\n";
	String tipusButaca = "butaca_disponible";
	//String llista_butaques[];
	

	while(itBut.hasNext()){


		ButacaSessio but = (ButacaSessio) itBut.next();
		String sel = "";
		if(but.getNumColumna()==0){
			butaquesTaula+= "<tr>\n";
		}
		
		if (but.getOperativa())
		{				
			if (but.getCompradaReservada())
			{				
				if (but.isPagada())
				{				
					
					tipusButaca = "butaca_reservada";
					
				}
				else {
					tipusButaca = "butaca_ocupada";
					
					
				}
				
			}
			else
			{
				tipusButaca = "butaca_disponible";
			}
			
			
		}
		else
		{
			tipusButaca = "butaca_no_operativa";
			sel ="checked";
		}
		
		
		
		if (but.getOperativa())
		{
			if (but.getCompradaReservada())
			{				
				if (but.isPagada())
				{				
					
					butaquesTaula+= "<td><div id='"+tipusButaca+"'><input type='Hidden' name='cekbutaca_" + but.getNumButaca() + "' class='check' "+ sel +" /></div></td>\n";
					
				}
				else {
					
					butaquesTaula+= "<td><div id='"+tipusButaca+"'><input type='Hidden' name='cekbutaca_" + but.getNumButaca() + "' class='check' "+ sel +" /></div></td>\n";
					
				}				
			}
			else
			{
				butaquesTaula+= "<td><div id='"+tipusButaca+"'><input type='Checkbox' name='cekbutaca_" + but.getNumButaca() + "' class='check' "+ sel +" /></div></td>\n";
			}
			
		}
		else
		{
			butaquesTaula+= "<td><div id='"+tipusButaca+"'><input type='Hidden' name='cekbutaca_" + but.getNumButaca() + "' class='check' "+ sel +" /></div></td>\n";
		}
		
		if(but.getNumColumna() == numMaxColumnes-1){
			butaquesTaula+= "</tr>\n";
		}
		
	}
	
	butaquesTaula+= "</table><br />\n";
	
%>
<%= butaquesTaula %>
