package gestioCinema.gestioSessions;
import gestioCinema.gestioPelicules.Pelicula;
import gestioCinema.gestioSales.Sala;

import java.util.Iterator;
import java.util.Vector;

public class Sessio {
	private int id;
	private String dataHora;
	private double preu;
	private Pelicula pelicula;
	private Sala sala;
	private Vector butaquesSessio;
	
	public Sessio (){
		id = -1;
		dataHora = "";
		preu=-1;
		pelicula = new Pelicula();
		sala = new Sala();
		butaquesSessio = new Vector ();
	}
	
	public void setAll( int idd, 
			String data_Hora,
			double preuu,
			Pelicula peli,
			Sala sal,
			Vector butSes){
		id = idd;
		dataHora = data_Hora;
		preu = preuu;
		pelicula = peli;
		sala = sal;
		butaquesSessio = butSes;
	}
	
	public int getId (){
		return id;
	}
	
	public String getDataHora (){
		return dataHora;
	}
	
	public Pelicula getPelicula (){
		return pelicula;
	}
	
	public Sala getSala (){
		return sala;
	}
	
	public Vector getButaquesSessio (){
		return butaquesSessio;
	}
	
	public void setId (int idd){
		id=idd;
	}
	
	public void setDataHora (String dat){
		dataHora=dat;
	}
	
	public void setPelicula (Pelicula peli){
		pelicula = peli;
	}
	
	public void setSala (Sala sal){
		sala=sal;
	}
	
	public double getPreu(){
		return preu;
	}
	
	public void setPreu(double preu){
		this.preu = preu;
	}
	
	public void setButaquesSessio (Vector butSes){
		butaquesSessio = butSes;
	}
	
	public String donarHora(){
	//	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
	//	Date data = new Date();
	//	data.setDate(dataHora);
	//	String str = sdf.format(data);
		return dataHora;
	}
	
	public Vector getCamps(){
		Vector campsSala;
		campsSala = new Vector();
		
		campsSala.add("id");
		campsSala.add("data_hora_inici");
		campsSala.add("preu");
		campsSala.add("id_sala");
		campsSala.add("id_pelicula");
		return campsSala;
	}
	
	
	public Vector getValors(){
		Vector valorsSala;
		valorsSala = new Vector();
		
		valorsSala.add(""+id);
		valorsSala.add(""+dataHora);
		valorsSala.add(""+preu);
		valorsSala.add(""+sala.getId());
		valorsSala.add(""+pelicula.getId());
			
		return valorsSala;
	}
	
	public Vector getFormats(){	
		Vector formatSala = new Vector();
		
		formatSala.add(""+id);
		formatSala.add("'"+dataHora+"'");
		formatSala.add("'"+preu+"'");
		formatSala.add(""+sala.getId()+"");
		formatSala.add(""+pelicula.getId()+"");
		
		return formatSala;
	}
	
	public String sqlInsert(){
		Iterator itCamps = getCamps().iterator();
		Iterator itFormats = getFormats().iterator();
		String values = "(";
		String valor ="";
		int i=0;
		while(itCamps.hasNext()){
			i++;
			valor= (String)itCamps.next();			
			if (i==1)
			{
				
				values+=(String) itCamps.next();
				
			}else{
				
				values+=", "+valor;
				
			}
		}
		
		
		values+=") VALUES (";
		
		i=0;
		valor="";
		while(itFormats.hasNext()){
			i++;
			valor= (String)itFormats.next();
			
			if (i==1)
			{
				
				values+=""+ itFormats.next();
				
			}else{
				
				values+=", "+valor;
				
			}
		}
		values = values+")";
		
		return values;
	}
	
	public String sqlUpdate(){
		Iterator itCamps = getCamps().iterator();
		Iterator itValors = getFormats().iterator();
		String values = "";
		
		if(itCamps.hasNext() && itValors.hasNext()){
			/*Treiem el id perque el id no es pot modificar*/
			itCamps.next();
			itValors.next();
		}
		
		while(itCamps.hasNext() && itValors.hasNext()){
			values = values + itCamps.next() +" = "+itValors.next();
			if(itValors.hasNext()){
				values = values +", ";
			}
		}
		return values;
	}
	
	public String donaHora(){		
		String amm = getDataHora();		
		String args[] = amm.split(" ");
		String data = args[1];
		String args2[] = data.split(":");
		data = args2[0] + ":" + args2[1];
		return ""+data;
	}
}
