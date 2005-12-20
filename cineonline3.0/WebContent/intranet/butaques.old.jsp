<%@ page import="gestioCinema.gestioSessions.Sessio" %>
<%@ page import="gestioCinema.gestioSessions.ButacaSessio" %>
<%@ page import="java.util.Vector" %>
<%
	
	Sessio sess = (Sessio) session.getAttribute("sessio");
	Vector butaques = sess.getButaquesSessio();
	
	int numMaxColumnes = sess.getSala().getNumMaxColumnes();
	int numMaxFiles = sess.getSala().getNumMaxFiles();
	
	String butaquesTaula = "<table cellspacing=0 cellpadding=0 border=0>";
	String tipusButaca = "butaca_disponible";
	//String llista_butaques[];
	
	int i=0;
	int j=0;
	
	for (i=0; i< numMaxFiles;i++)
	{
		butaquesTaula+= "<tr>";
		for (j=0;j< numMaxColumnes;j++)
		{
			ButacaSessio bses =(ButacaSessio)butaques.elementAt(j);
			if (bses.getCompradaReservada())
			{
				
			}
			
			
			if ( bses.getOperativa())
			{				
				tipusButaca = "butaca_disponible";
			}
			else
			{
				tipusButaca = "butaca_no_operativa";
			}
			if (i== numMaxFiles/2)
			{
				tipusButaca = "butaca_no_operativa";
			}
			
			if ((i==3) && (j<=3))
			{
				tipusButaca = "butaca_reservada";
			}
			
			if ((i==15) && (j>4))
			{
				tipusButaca = "butaca_ocupada";
			}
			
			if  (tipusButaca == "butaca_disponible")
			{
				butaquesTaula+= "<td><div id='"+tipusButaca+"'><input type='Checkbox' name='cekbutaca_" + bses.getNumButaca() + "' class='check' /></div></td>";
			}
			else
			{
				butaquesTaula+= "<td><div id='"+tipusButaca+"'><a href=# id='"+ tipusButaca + "' title='(fil " + (i+1) +",col " + (j+1) + ")'></div></a></td>";
			}
		}
		butaquesTaula+= "</tr>";
	}
	butaquesTaula+= "</table>";
%>
<%= butaquesTaula %>
