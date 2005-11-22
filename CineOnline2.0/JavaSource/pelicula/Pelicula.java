/*
 * Created on 09-nov-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pelicula;

/**
 * @author Òscar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Pelicula {

	private int id;
	
	private String titol;

	private String titoloriginal;

	private int any;

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

	/**
	 * 
	 */
	public Pelicula() {
		// TODO Auto-generated constructor stub
	}
	
	public Pelicula(int id_new,String titol_new,String titoloriginal_new,int any_new,int durada_new,String nacionalitat_new,int edatRecomenada_new,String tipusColor_new,String tipusSo_new,String genere_new,String director_new,String guionista_new,String productor_new,String actors_new,String sinopsis_new,String urlWeb_new,String urlImatge_new) {
		this.id=id_new;
		this.titol=titol_new;
		this.titoloriginal=titoloriginal_new;
		this.any=any_new;
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
		
	public void setAll(int id_new,String titol_new,String titoloriginal_new,int any_new,int durada_new,String nacionalitat_new,int edatRecomenada_new,String tipusColor_new,String tipusSo_new,String genere_new,String director_new,String guionista_new,String productor_new,String actors_new,String sinopsis_new,String urlWeb_new,String urlImatge_new) {
		this.id=id_new;
		this.titol=titol_new;
		this.titoloriginal=titoloriginal_new;
		this.any=any_new;
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

	public String getTitol() {return titol;}

	public void setTitol(String titol) {this.titol = titol;}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public String getTitoloriginal() {return titoloriginal;}

	public void setTitoloriginal(String titoloriginal) {this.titoloriginal = titoloriginal;}

	public int getAny() {return any;}

	public void setAny(int any) {this.any = any;}

	public int getDurada() {return durada;}

	public void setDurada(int durada) {this.durada = durada;}

	public String getNacionalitat() {return nacionalitat;}

	public void setNacionalitat(String nacionalitat) {this.nacionalitat = nacionalitat;}

	public int getEdatRecomenada() {return edatRecomenada;}

	public void setedatRecomenada(int edatRecomenada) {this.edatRecomenada = edatRecomenada;}

	public String getTipusColor(){return tipusColor;}
	
	public void setTipusColor(String tipusColor){this.tipusColor=tipusColor;}
	
	public String getTipusSo(){return tipusSo;}
	
	public void setTipusSo(String tipusSo){this.tipusSo=tipusSo;}
	
	public String getGenere() {return genere;}

	public void setGenere(String genere) {this.genere = genere;}

	public String getDirector() {return director;}

	public void setDirector(String director) {this.director = director;}
	
	public String getGuionista() {return guionista;}

	public void setGuionista(String guionista) {this.guionista = guionista;}

	public String getProductor() {return productor;}

	public void setProductor(String productor) {this.productor = productor;}

	public String getActors() {return actors;}

	public void setActors(String actors) {this.actors = actors;}

	public String getSinopsis() {return sinopsis;}

	public void setSinopsis(String sinopsis) {this.sinopsis = sinopsis;}

	public String getUrlWeb() {return urlWeb;}

	public void setUrlWeb(String urlWeb) {this.urlWeb = urlWeb;}

	public String getUrlImatge() {return urlImatge;}

	public void setUrlImatge(String urlImatge) {this.urlImatge = urlImatge;}Object getNomPelicula() {
		// TODO Auto-generated method stub
		return null;
	}
}
