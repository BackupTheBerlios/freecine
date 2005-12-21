<%
	int numMaxColumnes = 9;
	int numMaxFiles = 19;
	String butaquesTaula = "<table>";
	String tipusButaca = "butaca_disponible";
	String llista_butaques[];
	
	int i, j;
	for (i=0; i< numMaxFiles;i++)
	{
		butaquesTaula+= "<tr>";
		for (j=0;j< numMaxColumnes;j++)
		{
			if (j == numMaxColumnes/2)
			{
				tipusButaca = "butaca_no_operativa";
			}
			else
			{
				tipusButaca = "butaca_disponible";
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
			butaquesTaula+= "<td><div id='"+tipusButaca+"'><a href=javascript:alert('afegintotrient') id='"+ tipusButaca + "' title='(fil " + (i+1) +",col " + (j+1) + ")'></a></div></td>";
		}
		butaquesTaula+= "</tr>";
	}
	butaquesTaula+= "</table>";
%>
<%= butaquesTaula %>