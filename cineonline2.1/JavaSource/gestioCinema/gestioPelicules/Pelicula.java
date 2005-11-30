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
	private int durada;
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

	private static Vector campsPelicula;
	private Vector valorsPelicula;
	
	/*SQL*/
	
	public Pelicula() {
		id = -1;
		titol = "";
		titolOriginal = "";
		anny = -1;
		durada = -1;
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
	
	public void setAll(int id_new,
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

	public int getDurada() {
		return durada;
	}

	public void setDurada(int durada) {
		this.durada = durada;
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
	
	public boolean campsValids(){
		if(titol.equals(""))return false;
		if(anny>1850)return false;
		if(durada >= 1)return false;
		if(edatRecomenada >= 0)return false;
		return false;
	}
	
	public static Vector getCamps(){
		campsPelicula = new Vector();
		campsPelicula.add("id");
		campsPelicula.add("titol");
		campsPelicula.add("titol_original");
		campsPelicula.add("anny");
		campsPelicula.add("durada");
		campsPelicula.add("nacionalitat");
		campsPelicula.add("edat_recomenada");
		campsPelicula.add("tipus_color");
		campsPelicula.add("tipus_so");
		campsPelicula.add("genere");
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

	public void setAll(Vector rs){

	}

	
	public String sqlInsert(){
		return null;
	}
	
	public String sqlUpdate(){
		Iterator itCamps = getCamps().iterator();
		Iterator itValors = getValors().iterator();
		String values = "";
		
		if(itCamps.hasNext() && itValors.hasNext()){
			/*Treiem el id perque la base de dades s'encarrega*/
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
	
	public String sqlDelete(){
		return null;
	}
	
	public static String sqlSelect(){
		return null;
	}
	
	public String toString(){
		return ""+getValors();
	}
}
