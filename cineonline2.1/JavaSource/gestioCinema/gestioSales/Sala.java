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
			String descripcio,
			Vector butaques){
		this.id = id;
		this.nomSala = nomSala;
		this.numMaxColumnes = numMaxColumnes;
		this.numMaxFiles = numMaxFiles;
		this.descripcio = descripcio;
		this.butaques = butaques;
	}
	
	public Vector getButaquesOperatives() {
		Iterator itButaques = butaques.iterator();
		Vector butaquesOperatives = new Vector();
		Butaca butaca;
		while (itButaques.hasNext()){
			butaca = (Butaca)itButaques.next();
			if(butaca.getOperativa()){
				butaquesOperatives.add(butaca);
			}
		}
		return butaquesOperatives;
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
}
