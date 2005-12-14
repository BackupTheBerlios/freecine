/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gestioCinema.gestioSales;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author Ivan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Sala {
	private int id;
	private String nomSala;
	private int numMaxColumnes;
	private int numMaxFiles;
	private int numButaques;
	private Vector butaques;
	private String descripcio;

	public Sala(){
		this.id = -1;
		nomSala = null;
		numMaxColumnes = -1;
		numMaxFiles = -1;
		butaques = new Vector();	
	}
	
	public void setAll(
			int id,
			String nomSala,
			int numMaxColumnes,
			int numMaxFiles,
			int numButaques,
			String descripcio,
			Vector butaques){
		this.id = id;
		this.nomSala = nomSala;
		this.numMaxColumnes = numMaxColumnes;
		this.numMaxFiles = numMaxFiles;
		this.numButaques = numButaques;
		this.descripcio = descripcio;
		this.butaques = butaques;
	}
	
	/**
	 * @return
	 */
	public String numButaquesOperatives() {
//		Iterator itButaques = butaques.iterator();
//		Vector butaquesOperatives = new Vector();
//		Butaca butaca;
//		while (itButaques.hasNext()){
//			butaca = (Butaca)itButaques.next();
//			if(butaca.getOperativa()){
//				butaquesOperatives.add(butaca);
//			}
//		}
		//return butaquesOperatives;
		return ""+5;
	}

	public Vector getButaques() {
		return butaques;
	}

	public void setButaques(Vector butaques) {
		this.butaques = butaques;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomSala() {
		return nomSala;
	}

	public void setNomSala(String nomSala) {
		this.nomSala = nomSala;
	}

	public int getNumMaxColumnes() {
		return numMaxColumnes;
	}

	public void setNumMaxColumnes(int numMaxColumnes) {
		this.numMaxColumnes = numMaxColumnes;
	}

	public int getNumMaxFiles() {
		return numMaxFiles;
	}

	public void setNumMaxFiles(int numMaxFiles) {
		this.numMaxFiles = numMaxFiles;
	}
	
	public int getNumButaques() {
		return numButaques;
	}

	public void setNumButaques(int numButaques) {
		this.numButaques= numButaques;
	}
	
	public Vector getCamps(){
		Vector campsSala;
		campsSala = new Vector();
		campsSala.add("id");
		campsSala.add("nom");
		campsSala.add("num_butaques");
		campsSala.add("num_columnes");
		campsSala.add("descripcio");
		return campsSala;
	}
	public Vector getValors(){
		Vector valorsSala;
		valorsSala = new Vector();

		valorsSala.add(""+id);
		valorsSala.add(""+nomSala);
		valorsSala.add(""+numMaxColumnes);
		valorsSala.add(""+numMaxFiles);
		valorsSala.add(""+descripcio);
			
		return valorsSala;
	}
	
	public Vector getFormats(){	
		Vector formatSala = new Vector();

		formatSala.add(""+id);
		formatSala.add("'"+nomSala+"'");
		formatSala.add(""+numMaxColumnes+"");
		formatSala.add(""+numMaxFiles+"");
		formatSala.add("'"+descripcio+"'");
		
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
		values+=")";
		
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
