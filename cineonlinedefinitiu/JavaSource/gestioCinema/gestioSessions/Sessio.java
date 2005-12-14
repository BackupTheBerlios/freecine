package gestioCinema.gestioSessions;
import gestioCinema.gestioPelicules.Pelicula;
import gestioCinema.gestioSales.Sala;

import java.util.Vector;

public class Sessio {
	private int id;
	private String data;
	private String horaInici;
	private Pelicula pelicula;
	private Sala sala;
	//private Vector butaquesSessio;
	
	public Sessio (){
		id = -1;
		data = "";
		horaInici = "";
		pelicula = null;
		sala = null;
		//butaquesSessio = new Vector ();
	}
	
	public int getId (){
		return id;
	}
	
	public String getData (){
		return data;
	}
	
	public String getHoraInici (){
		return horaInici;
	}
	
	public Pelicula getPelicula (){
		return pelicula;
	}
	
	public Sala getSala (){
		return sala;
	}
	
//	public Vector getButaquesSessio (){
//	return butaquesSessio;
//	}
	
	public void setId (int idd){
		id=idd;
	}
	
	public void setData (String dat){
		data=dat;
	}
	
	public void setHoraInici (String horaInic){
		horaInici = horaInic;
	}
	
	public void setPelicula (Pelicula peli){
		pelicula = peli;
	}
	
	public void setSala (Sala sal){
		sala=sal;
	}
	
	/*public void setButaquesSessio (Vector butSes){
	 butaquesSessio = butSes;
	 }*/
	
	public void setAll( int idd, 
			String dat,
			String horaInic,
			Pelicula peli,
			Sala sal){
		id = idd;
		data = dat;
		horaInici = horaInic;
		pelicula = peli;
		sala = sal;
		//butaquesSessio = butSes;
	}
}
