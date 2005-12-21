<%!
	public boolean entrada_plena(String entrada)
	{
		return (entrada!="");
	}

	public boolean identificat(String nomUsuari, String pwd)
	{
		String login = "pepe";
		String password = "pepe";
		return ((nomUsuari.compareTo(login)==0)&&(pwd.compareTo(password)==0));		
	}	
%>

<%
String missatge = "";
boolean autenticat = false;

if(request.getParameter("nomUsuari")!=null)
{
	if(request.getParameter("pwd")!=null)
	{
		if (entrada_plena(request.getParameter("nomUsuari")))
		{
			if (entrada_plena(request.getParameter("pwd")))
			{
				if (identificat(request.getParameter("nomUsuari"),request.getParameter("pwd")))
				{
					missatge = "Benvingut a l'administració " + request.getParameter("nomUsuari");
					autenticat = true;
				}
				else
				{
					missatge = "Nom d'usuari o password incorrecte.";
				}				
			}
			else
			{
				missatge = "Cal intoduir el password.";
			}
		}
		else
		{
			missatge = "Cal introduir el nom d'usuari." + missatge;
		}					
	}
}
else
{
	missatge = "No s'ha passat els parametres correctament.";
}


if (autenticat)
{
	%>
	<jsp:forward page="login.jsp" />
	<%
}
else
{
	%>
	<jsp:forward page="default.jsp" />
	<%
}

%>