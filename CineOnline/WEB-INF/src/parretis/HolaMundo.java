package parretis;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HolaMundo extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("");
		out.println("");
		out.println("Prueba");
		out.println("");
		out.println(" ¡Hola mundo! ");
		out.println("");
	}

}
