package gestioCinema.gestioUsuaris;

import java.util.Iterator;
import java.util.Vector;

public class Usuari {
	private int id;
	private String login;
	private String password;
	private int rol;

	public Usuari() {
		
	}
	
	public void setAll(int id, String login, String password, int rol){
		this.id = id;
		this.login = login;
		this.password = password;
		this.rol = rol;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getNomUsuari() {
		return login;
	}

	public void setNomUsuari(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}
	
	public Vector getCamps(){
		Vector campsSala;
		campsSala = new Vector();
		campsSala.add("id");
		campsSala.add("login");
		campsSala.add("password");
		campsSala.add("tipus");
		return campsSala;
	}
	
	public Vector getValors(){
		Vector valorsSala;
		valorsSala = new Vector();

		valorsSala.add(""+id);
		valorsSala.add(""+login);
		valorsSala.add(""+password);
		valorsSala.add(""+rol);
		return valorsSala;
	}
	
	public Vector getFormats(){	
		Vector formatSala = new Vector();
		formatSala.add(""+id);
		formatSala.add("'"+login+"'");
		formatSala.add("'"+password+"'");
		formatSala.add(""+rol+"");
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
	
	public String toString(){
		return ""+getValors();
	}

}
