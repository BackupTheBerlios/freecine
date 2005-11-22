/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sala;

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
	private int numButaquesOperatives;
	private int numMaxColumnes;
	private int numMaxFiles;
	private Vector butaques;
	private String descripcio;

	public Sala(){
		this.id = -1;
		nomSala = null;
		numButaquesOperatives = -1;
		numMaxColumnes = -1;
		numMaxFiles = -1;
		butaques = new Vector();	
	}
	
	public Sala(
			int id, 
			String nomSala, 
			String descripcio){
		
		this.id = id;
		this.nomSala = nomSala;
		this.descripcio = descripcio;
		
		this.numButaquesOperatives=-1;
		this.numMaxColumnes=-1;
		this.numMaxFiles=-1;
		this.butaques= new Vector();
	}
	
	
	public void setAll(
			int id,
			String nomSala,
			int numMaxColumnes,
			int numMaxFiles,
			String descripcio){
		this.id = id;
		this.nomSala = nomSala;
		this.numMaxColumnes = numMaxColumnes;
		this.numMaxFiles = numMaxFiles;
		this.descripcio = descripcio;
		generarButaques(numMaxFiles,numMaxColumnes);
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
	public int getNumButaquesOperatives() {
		return numButaquesOperatives;
	}
	public void setNumButaquesOperatives(int numButaquesOperatives) {
		this.numButaquesOperatives = numButaquesOperatives;
	}
	
	public int getNumMaxColumnes() {
		return numMaxColumnes;
	}
	
	public int getNumMaxFiles() {
		return numMaxFiles;
	}
	
	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return 	"id ="+id+", "+
				"nomSala ='"+nomSala+"' "+
				"numButaquesOperatives ="+numButaquesOperatives+", "+
				"numMaxFiles ="+numMaxFiles+", "+
				"numMaxColumnes ="+numMaxColumnes+", "+
				"descripcio ='"+descripcio+"')";
	}
	
	/*********************BUTAQUES**************************/
	/*
	 * Genera un vector de butaques on crea cada butaca en una 
	 * posició(fila , columna) i les posa totes a operatives.
	 * Per generar la sala sempre s'ha de passar el numMaxFiles
	 * i el numMaxColumnes
	 */
	public void generarButaques(int numMaxFiles, int numMaxColumnes){
		this.numMaxFiles = numMaxFiles;
		this.numMaxColumnes = numMaxColumnes;
		butaques = new Vector();
		int numButaca=0;
		Butaca butaca;
		for(int i = 1; i <= numMaxFiles; i++){
			for(int j = 1; j <= numMaxColumnes; j++){
				butaques.add(new Butaca(numButaca, i, j, true));
				numButaca++;
			}
		}
		this.numButaquesOperatives = numButaca;
	}
	
	public void generarButaques(){
		butaques = new Vector();
		int numButaca=0;
		Butaca butaca;
		for(int i = 1; i <= numMaxFiles; i++){
			for(int j = 1; j <= numMaxColumnes; j++){
				butaques.add(new Butaca(numButaca, i, j, true));
				numButaca++;
			}
		}
		this.numButaquesOperatives = numButaca;
	}
	
	/*
	 * Possa a no operativa una butaca mitjançant el id de la butaca 
	 */
	public void setButacaNoOperativaId(int id){
		Butaca butaca = (Butaca)butaques.elementAt(id-1);
		if(butaca.getOperativa()){
			butaca.setOperativa(false);
			numButaquesOperatives--;
		}
	}
	
	/*
	 * Possa a no operativa una butaca mitjançant la posició fila columna 
	 */
	public void setButacaNoOperativaFilaColumna(int fila, int columna){
		Butaca butaca=(Butaca)butaques.elementAt((fila-1)*numMaxFiles+(columna-1));
		if(butaca.getOperativa()){
			butaca.setOperativa(false);
			numButaquesOperatives--;
		}
	}
	
	/*
	 * Possa a operativa una butaca mitjançant el id de la butaca 
	 */
	public void setButacaOperativaId(int id){
		Butaca butaca = (Butaca)butaques.elementAt(id-1);
		if(!butaca.getOperativa()){
			butaca.setOperativa(true);
			numButaquesOperatives--;
		}
	}
	
	/*
	 * Possa a operativa una butaca mitjançant la posició fila columna 
	 */	
	public void setButacaOperativaFilaColumna(int fila, int columna){
		Butaca butaca=(Butaca)butaques.elementAt((fila-1)*numMaxFiles+(columna-1));
		if(!butaca.getOperativa()){
			butaca.setOperativa(true);
			numButaquesOperatives--;
		}
	}
	/************************SQL****************************/
	
	/*
	 * Genera el valors per a fer el update de l'objecte
	 */
	public String generarValuesUpdateSQL(){
		String valuesSQL;
		valuesSQL=+
			id+", '"+
			descripcio+", "+
			+numButaquesOperatives+", "
			+numMaxFiles+", "
			+numMaxColumnes+", '"
			+descripcio+"')";
		return valuesSQL;
	}
	
	
	/*
	 * Genera els valors de l'insert de l'objecte 
	 */
	public String generarValuesInsertSQL(){
		String valuesSQL;
		valuesSQL=
			"id ="+id+", "+
			"nomSala ='"+nomSala+"' "+
			"numButaquesOperatives ="+numButaquesOperatives+", "+
			"numMaxFiles ="+numMaxFiles+", "+
			"numMaxColumnes ="+numMaxColumnes+", "+
			"descripcio ='"+descripcio+"')";
		return valuesSQL;
	}
	
	/* Genera tots els valors del where amb tots els atributs de la clase que no
	 * son ni tenen el valor -1, null o buit 
	 */
	public String generarWhereSQL(){
		boolean primer=true;
		String valuesSQL;
		valuesSQL="";
		if(id>=0){
			if(primer){
				valuesSQL = valuesSQL.concat("id ="+id);
				primer=false;
			}else{
				valuesSQL = valuesSQL.concat(", id ="+id);
			}
		}
		if(nomSala != null && !nomSala.equals("")){
			if(primer){
				valuesSQL = valuesSQL.concat("nomSala ='"+nomSala+"'");
				primer=false;
			}else{
				valuesSQL = valuesSQL.concat(", nomSala ='"+nomSala+"'");
			}
		}
		if(numButaquesOperatives >=0 ){
			if(primer){
				valuesSQL = valuesSQL.concat("numButaquesOperatives ="+numButaquesOperatives+" ");
				primer=false;
			}else{
				valuesSQL = valuesSQL.concat(", numButaquesOperatives ="+numButaquesOperatives+" ");	
			}
		}
		if(numMaxFiles >=0){
			if(primer){
				valuesSQL = valuesSQL.concat("numMaxFiles ="+numMaxFiles+" ");
				primer=false;
			}else{
				valuesSQL = valuesSQL.concat(", numMaxFiles ="+numMaxFiles+" ");
			}
		}
		if(numMaxFiles >=0){
			if(primer){
				valuesSQL = valuesSQL.concat("numMaxColumnes="+numMaxColumnes+" ");
				primer=false;
			}else{
				valuesSQL = valuesSQL.concat(", numMaxColumnes="+numMaxColumnes+" ");
			}
			
		}
		if(descripcio != null && !descripcio.equals("")){
			if(primer){
				valuesSQL = valuesSQL.concat("descripcio ='"+descripcio+"' ");
			}else{
				
			}
			
		}
		return valuesSQL;
	}
	
	public void imprimirSala(){
		if(numButaquesOperatives>0){
			Iterator itButaques = butaques.iterator();
			Butaca butaca;
			while(itButaques.hasNext()){
				butaca = (Butaca)itButaques.next();
				System.out.print(
						"["+butaca.getNumButaca()+"("+butaca.getOperativa()+
						") : "+butaca.getNumFila()+", "+butaca.getNumColumna()+"]\t");
				if(butaca.getNumColumna() == numMaxColumnes){
					System.out.println("");
				}
			}		
		}
	}
	public void setSala(Sala sala){
		id = sala.getId();
		descripcio = sala.getDescripcio();
		numButaquesOperatives = sala.getNumButaquesOperatives();
		numMaxColumnes = sala.getNumMaxColumnes();
		numMaxFiles = sala.getNumMaxFiles();
		butaques = sala.getButaques();
		descripcio = sala.getDescripcio();
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
}
