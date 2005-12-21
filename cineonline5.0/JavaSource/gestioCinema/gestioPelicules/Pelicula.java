/*
 * Created on 09-nov-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package gestioCinema.gestioPelicules;




import java.util.Iterator;
import java.util.Vector;

/**
 * @author Òscar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Pelicula {
	private int id;
	private String titol;
	private String titolOriginal;
	private int anny;
	private String durada;
	private String nacionalitat;
	private int edatRecomenada;
	private String tipusColor;
	private String tipusSo;
	private String genere;
	private String director;
	private String guionista;
	private String productor;
	private String actors;
	private String sinopsis;
	private String urlWeb;
	private String urlImatge;
	
	/*SQL*/
	
	public Pelicula() {
		id = -1;
		titol = "";
		titolOriginal = "";
		anny = -1;
		durada = "";
		nacionalitat = "";
		edatRecomenada = -1;
		tipusColor = "";
		tipusSo = "";
		genere = "";
		director = "";
		guionista = "";
		productor = "";
		actors = "";
		sinopsis = "";
		urlWeb = "";
		urlImatge = "";
	}
	
	public void setAll(
			int id_new,
			String titol_new,
			String titolOriginal_new,
			int anny_new,
			String durada_new,
			String nacionalitat_new,
			int edatRecomenada_new,
			String tipusColor_new,
			String tipusSo_new,
			String genere_new,
			String director_new,
			String guionista_new,
			String productor_new,
			String actors_new,
			String sinopsis_new,
			String urlWeb_new,
			String urlImatge_new) {
		
		this.id=id_new;
		this.titol=titol_new;
		this.titolOriginal=titolOriginal_new;
		this.anny=anny_new;
		this.durada=durada_new;
		this.nacionalitat=nacionalitat_new;
		this.edatRecomenada=edatRecomenada_new;
		this.tipusColor=tipusColor_new;
		this.tipusSo=tipusSo_new;
		this.genere=genere_new;
		this.director=director_new;
		this.guionista=guionista_new;
		this.productor=productor_new;
		this.actors=actors_new;
		this.sinopsis=sinopsis_new;
		this.urlWeb=urlWeb_new;
		this.urlImatge=urlImatge_new;
	}
	
	public void setAll(
			int id_new,
			String titol_new,
			String titolOriginal_new,
			int anny_new,
			int durada_new,
			String nacionalitat_new,
			int edatRecomenada_new,
			String tipusColor_new,
			String tipusSo_new,
			String genere_new,
			String director_new,
			String guionista_new,
			String productor_new,
			String actors_new,
			String sinopsis_new,
			String urlWeb_new,
			String urlImatge_new) {
		
		this.id=id_new;
		this.titol=titol_new;
		this.titolOriginal=titolOriginal_new;
		this.anny=anny_new;
		setDurada(durada_new);
		this.nacionalitat=nacionalitat_new;
		this.edatRecomenada=edatRecomenada_new;
		this.tipusColor=tipusColor_new;
		this.tipusSo=tipusSo_new;
		this.genere=genere_new;
		this.director=director_new;
		this.guionista=guionista_new;
		this.productor=productor_new;
		this.actors=actors_new;
		this.sinopsis=sinopsis_new;
		this.urlWeb=urlWeb_new;
		this.urlImatge=urlImatge_new;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public int getAnny() {
		return anny;
	}

	public void setAnny(int anny) {
		this.anny = anny;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDurada() {
		return durada;
	}

	public void setDurada(String durada) {
		this.durada = durada;
	}
	
	public void setDurada(int durada) {
		String hora= ""+durada/60;
		String minuts= ""+durada%60;
		
		this.durada = hora+":"+minuts+":00";
	}

	public int getEdatRecomenada() {
		return edatRecomenada;
	}

	public void setEdatRecomenada(int edatRecomenada) {
		this.edatRecomenada = edatRecomenada;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getGuionista() {
		return guionista;
	}

	public void setGuionista(String guionista) {
		this.guionista = guionista;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNacionalitat() {
		return nacionalitat;
	}

	public void setNacionalitat(String nacionalitat) {
		this.nacionalitat = nacionalitat;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getTipusColor() {
		return tipusColor;
	}

	public void setTipusColor(String tipusColor) {
		this.tipusColor = tipusColor;
	}

	public String getTipusSo() {
		return tipusSo;
	}

	public void setTipusSo(String tipusSo) {
		this.tipusSo = tipusSo;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getTitolOriginal() {
		return titolOriginal;
	}

	public void setTitolOriginal(String titolOriginal) {
		this.titolOriginal = titolOriginal;
	}

	public String getUrlImatge() {
		return urlImatge;
	}

	public void setUrlImatge(String urlImatge) {
		this.urlImatge = urlImatge;
	}

	public String getUrlWeb() {
		return urlWeb;
	}

	public void setUrlWeb(String urlWeb) {
		this.urlWeb = urlWeb;
	}
	
	public Vector getCamps(){
		Vector campsPelicula;
		campsPelicula = new Vector();
		campsPelicula.add("id");
		campsPelicula.add("titol");
		campsPelicula.add("titol_original");
		campsPelicula.add("anny");
		campsPelicula.add("durada");
		campsPelicula.add("id_nacionalitat");
		campsPelicula.add("edat_recomanada");
		campsPelicula.add("tipus_color");
		campsPelicula.add("tipus_so");
		campsPelicula.add("id_genere");
		campsPelicula.add("director");
		campsPelicula.add("guionista");
		campsPelicula.add("productor");
		campsPelicula.add("actors");
		campsPelicula.add("sinopsis");
		campsPelicula.add("url_web");
		campsPelicula.add("url_imatge");
		return campsPelicula;
	}
	public Vector getValors(){
		Vector valorsPelicula;
		valorsPelicula = new Vector();
		valorsPelicula.add(""+id);
		valorsPelicula.add(titol);
		valorsPelicula.add(titolOriginal);
		valorsPelicula.add(""+anny);
		valorsPelicula.add(""+durada);
		valorsPelicula.add(nacionalitat);
		valorsPelicula.add(""+edatRecomenada);
		valorsPelicula.add(tipusColor);
		valorsPelicula.add(tipusSo);
		valorsPelicula.add(genere);
		valorsPelicula.add(director);
		valorsPelicula.add(guionista);
		valorsPelicula.add(productor);
		valorsPelicula.add(actors);
		valorsPelicula.add(sinopsis);
		valorsPelicula.add(urlWeb);
		valorsPelicula.add(urlImatge);	
		return valorsPelicula;
	}
	
	public Vector getFormats(){	
		Vector formatPelicula = new Vector();
		formatPelicula.add(""+id);
		formatPelicula.add("'"+titol+"'");
		formatPelicula.add("'"+titolOriginal+"'");
		formatPelicula.add(""+anny);
		formatPelicula.add("'"+durada+"'");
		formatPelicula.add(""+nacionalitat);
		formatPelicula.add(""+edatRecomenada);
		formatPelicula.add("'"+tipusColor+"'");
		formatPelicula.add("'"+tipusSo+"'");
		formatPelicula.add(""+genere);
		formatPelicula.add("'"+director+"'");
		formatPelicula.add("'"+guionista+"'");
		formatPelicula.add("'"+productor+"'");
		formatPelicula.add("'"+actors+"'");
		formatPelicula.add("'"+sinopsis+"'");
		formatPelicula.add("'"+urlWeb+"'");
		formatPelicula.add("'"+urlImatge+"'");	
		return formatPelicula;
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
	
	public String duradaMinuts(){		
		String amm = getDurada();		
		String args[] = amm.split(":");		
		int minuts = 60 * Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
		return ""+minuts;
	}

}
