/*
 * Created on 09-nov-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pelicula;

import java.util.Iterator;
import java.util.Vector;
/**
 * @author Òscar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ControladorPelicula extends Pelicula {

	private Vector catalegpelicules=new Vector();

	public Vector getCatalegpelicules() {
		return catalegpelicules;
	}

	public void setCatalegpelicules(Vector catalegpelicules) {
		this.catalegpelicules = catalegpelicules;
	}

	public Pelicula getPelicula(int id) throws ControladorPeliculesException{
		Pelicula peli=new Pelicula();
		Iterator iter=catalegpelicules.iterator();
		while(iter.hasNext()){
			peli=(Pelicula)iter.next();
			if(peli.getId()==id)return peli;
		}
		throw new ControladorPeliculesException("[ControladorPelicula]:[getPelicula] -> " +
				"Error: No s'ha trobat cap Pel·lícula amb id= "+id);
	}

	public boolean afegirPelicula(int id,String titol,String titoloriginal,int any,int durada,String nacionalitat,int edatrecomenada,String tipusColor,String tipusSo,String genere,String director,String guionista,String productor,String actors,String sinopsis,String urlWeb,String urlImatge) {
		Pelicula pelicula_new= new Pelicula(id,titol,titoloriginal,any,durada,nacionalitat,edatrecomenada,tipusColor,tipusSo,genere,director,guionista,productor,actors,sinopsis,urlWeb,urlImatge);
		return catalegpelicules.add(pelicula_new);
	}
	
	public boolean eliminarPelicula(int id) throws ControladorPeliculesException{
		Pelicula peli=getPelicula(id);
		if(peli!=null)return catalegpelicules.remove(peli);
		else return false;
	}
	
	public boolean eliminarPelicula(Pelicula peli) throws ControladorPeliculesException{
		if(!catalegpelicules.remove(peli)){
			throw new ControladorPeliculesException("[ControladorPelicula]:[getPelicula] -> " +
				"Error: No s'ha trobat cap Pel·lícula amb id= "+peli.getId());
		}
		return true;
	}
	
	public boolean modificarPelicula(int id,String titol,String titoloriginal,int any,int durada,String nacionalitat,int edatrecomenada,String tipusColor,String tipusSo,String genere,String director,String guionista,String productor,String actors,String sinopsis,String urlWeb,String urlImatge) throws ControladorPeliculesException {
			Pelicula peli=getPelicula(id);
			Pelicula peli_new=new Pelicula(id,titol,titoloriginal,any,durada,nacionalitat,edatrecomenada,tipusColor,tipusSo,genere,director,guionista,productor,actors,sinopsis,urlWeb,urlImatge);
			if(peli!=null){
				catalegpelicules.add(peli_new);
				return eliminarPelicula(id);
			}
			else return false;
		}

	public void printPelicules() {
		Pelicula peli = new Pelicula();
		Iterator iter=catalegpelicules.iterator();
		System.out.println("Pel·lícules:");
		while(iter.hasNext()){
			peli=(Pelicula)iter.next();
			System.out.println(""+peli.getId()+" "+peli.getTitol().toString()+"");
		}
	}

}