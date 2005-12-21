package gestioCinema.gestioPelicules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;

public class ControladorPelicules extends Controlador{ 
	public ControladorPelicules() throws ControladorException {
		super();
	}
	
	public Vector toVectorPelicules(ResultSet rs) throws SQLException {
		/*
		 * Converteix un ResultSet a Vector de Pelicules
		 */
		Vector pelicules = new Vector();
		
		while (rs.next()) {
			Pelicula pelicula = new Pelicula();
			pelicula.setAll(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getString(6),
				rs.getInt(7),
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12),
				rs.getString(13),
				rs.getString(14),
				rs.getString(15),
				rs.getString(16),
				rs.getString(17));
				
			pelicules.addElement(pelicula);
		}
		return pelicules;	
	}

	public Vector getPelicules() throws ControladorException{
		ResultSet rsPelicules;
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula";*/
		try {
			
			String query = "SELECT  " +
								"id, " +
								"titol, " +
								"titol_original, " +
								"anny, " +
								"durada, " +
								"id_nacionalitat, " +
								"edat_recomanada, " +
								"tipus_color, " +
								"tipus_so, " +
								"id_genere, " +
								"director, " +
								"guionista, " +
								"productor, " +
								"actors, " +
								"sinopsis, " +
								"url_web, " +
								"url_imatge " +
							"FROM Pelicula ORDER BY titol ASC";
			/*
			String query ="SELECT * FROM pelicula";*/
			rsPelicules = selectRS(query);
		
			return toVectorPelicules(rsPelicules);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getPelicules] Error SQL:"+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getPelicules] Error SQL: "+e.getMessage());
		}
	}
	
	public Vector getPeliculesAlfabeticament() throws ControladorException{
		ResultSet rsPelicules;
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula";*/
		try {
			
			String query = "SELECT  " +
								"id, " +
								"titol, " +
								"titol_original, " +
								"anny, " +
								"durada, " +
								"id_nacionalitat, " +
								"edat_recomanada, " +
								"tipus_color, " +
								"tipus_so, " +
								"id_genere, " +
								"director, " +
								"guionista, " +
								"productor, " +
								"actors, " +
								"sinopsis, " +
								"url_web, " +
								"url_imatge " +
							"FROM Pelicula ORDER BY titol ASC";
			/*
			String query ="SELECT * FROM pelicula";*/
			rsPelicules = selectRS(query);
		
			return toVectorPelicules(rsPelicules);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getPelicules] Error SQL:"+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getPelicules] Error SQL: "+e.getMessage());
		}
	}
	
	public Vector cercaPelicules(String titol, String director, String any) throws ControladorException{
		ResultSet rsPelicules;
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula";*/
		try {
			
			String query = "SELECT  " +
								"id, " +
								"titol, " +
								"titol_original, " +
								"anny, " +
								"durada, " +
								"id_nacionalitat, " +
								"edat_recomanada, " +
								"tipus_color, " +
								"tipus_so, " +
								"id_genere, " +
								"director, " +
								"guionista, " +
								"productor, " +
								"actors, " +
								"sinopsis, " +
								"url_web, " +
								"url_imatge " +
							"FROM Pelicula " +
							"WHERE ";
			String condicions = "";
			if(titol.equals("")){
				condicions+="titol LIKE '%" + titol + "%' AND ";
			}
			
			if(director.equals("")){
				condicions+="director LIKE '%" + director + "%' AND ";
	
			}
			
			if(!any.equals("")){
				condicions+="anny LIKE '%" + any + "%' AND ";
			}
			
			condicions+=" (0 = 0) ORDER BY titol ASC ";

			System.err.println(query+condicions);
			rsPelicules = selectRS(query+condicions);
		
			return toVectorPelicules(rsPelicules);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getPelicules] Error SQL:"+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getPelicules] Error SQL: "+e.getMessage());
		}
	}

	public Pelicula getPelicula(int id) throws ControladorException{
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula WHERE id="+id;";*/
		try {
			String query ="SELECT  " +
								"id, " +
								"titol, " +
								"titol_original, " +
								"anny, " +
								"durada, " +
								"id_nacionalitat, " +
								"edat_recomanada, " +
								"tipus_color, " +
								"tipus_so, " +
								"id_genere, " +
								"director, " +
								"guionista, " +
								"productor, " +
								"actors, " +
								"sinopsis, " +
								"url_web, " +
								"url_imatge " +
							"FROM Pelicula "+
							"WHERE id="+id;
								
			Pelicula pelicula;
			Vector resultat = toVectorPelicules(selectRS(query));
			if(resultat.size()==0)
			{
				return new Pelicula();
			}
			else
			{
				pelicula = (Pelicula) resultat.elementAt(0);
				return pelicula;
			}
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getPelicula] Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getPelicula] Error SQL: "+e.getMessage());
		}
		
	}
	
	public void eliminarPelicula(String id) throws ControladorException {
		String query ="DELETE FROM PELICULA WHERE id="+id;
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[eliminarPelicula(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[eliminarPelicula(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void modificarPelicula(Pelicula pelicula) throws ControladorException {
		String query ="UPDATE PELICULA SET "+pelicula.sqlUpdate()+" WHERE id = "+pelicula.getId();
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[modificarPelicula(Pelicula pelicula)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[modificarPelicula(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void afegirPelicula(Pelicula pelicula) throws ControladorException{
		String query ="INSERT INTO PELICULA "+pelicula.sqlInsert();
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[afegirPelicula(Pelicula pelicula)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[afegirPelicula(Pelicula pelicula)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}

	/* 
	 * 
	 * <<<<<<<<<<<<<<<<INICI GESTIO NACIONALITAT>>>>>>>>>>>>>>>>>>>>>>
	 *  
	 * */
	
	public Vector getNacionalitats() throws ControladorException {
		String query ="SELECT * FROM nacionalitat ORDER by pais ASC";
		Vector nac;
		try {
			nac = selectVector(query);
			return nac;
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getNacionalitats()] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getNacionalitats()] query:"+query+"\n Error SQL: "+e.getMessage());
		}
		
	}

	public Vector getNacionalitat(int idNacionalitat) throws ControladorException {
		String query ="SELECT * FROM nacionalitat WHERE id="+idNacionalitat;
		Vector nac;
		try {
			nac = selectVector(query);
			System.err.println("[Nacionalitat]"+(Vector) nac.firstElement());
			return (Vector) nac.firstElement();
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getNacionalitat(int idNacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getNacionalitat(int idNacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}

	public void modificarNacionalitat(String idNacionalitat, String nomNacionalitat) throws ControladorException {
		String query ="UPDATE NACIONALITAT SET pais = '"+nomNacionalitat+"' WHERE id = "+idNacionalitat;
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[modificarNacionalitat(String idNacionalitat, String nomNacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[modificarNacionalitat(String idNacionalitat, String nomNacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
		
	}
	
	public void afegirNacionalitat(String nomNacionalitat) throws ControladorException {
		String query ="INSERT INTO NACIONALITAT (pais) VALUES ('"+nomNacionalitat+"')";
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[afegirNacionalitat(String nacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[afegirNacionalitat(String nacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void eliminarNacionalitat(String idNacionalitat) throws ControladorException {
		String query ="DELETE FROM NACIONALITAT WHERE id="+idNacionalitat;
		try {
			
			ResultSet rs = selectRS("SELECT id FROM PELICULA WHERE id_nacionalitat = "+idNacionalitat);
			
			if(rs.next()){
				System.err.println("[ControladorPelicules]:[eliminarNacionalitat(int idNacionalitat)] query:"+query+"\n Error: Hi han pelicules que depenent d'aquesta nacionalitat");
				throw new ControladorException("[ControladorPelicules]:[eliminarNacionalitat(int idNacionalitat)] query:"+query+"\n Error: Hi han pelicules que depenent d'aquesta nacionalitat");
			}else{
				update(query);
				
			}
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[eliminarNacionalitat(int idNacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[eliminarNacionalitat(int idNacionalitat)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	
	

	/* 
	 * 
	 * <<<<<<<<<<<<<<<<FI GESTIO NACIONALITAT>>>>>>>>>>>>>>>>>>>>>>
	 *  
	 * */

	/* 
	 * 
	 * <<<<<<<<<<<<<<<<INICI GESTIO GENERE>>>>>>>>>>>>>>>>>>>>>>
	 *  
	 * */
	public Vector getGeneres() throws ControladorException {
		String query ="SELECT * FROM genere ORDER by descripcio ASC";
		Vector nac;
		try {
			nac = selectVector(query);
			return nac;
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getGeneres()] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getGeneres()] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public Vector getGenere(int idGenere) throws ControladorException {
		String query ="SELECT * FROM Genere WHERE id="+idGenere;
		Vector nac;
		try {
			nac = selectVector(query);
			return (Vector) nac.firstElement();
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getGenere(int idGenere)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getGenere(int idGenere)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void modificarGenere(String idGenere, String nomGenere) throws ControladorException {
		String query ="UPDATE GENERE SET descripcio = '"+nomGenere+"' WHERE id = "+idGenere;
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[modificarGenere(String idGenere, String nomGenere)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[modificarGenere(String idGenere, String nomGenere)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
		
	}
	
	public void afegirGenere(String nomGenere) throws ControladorException {
		String query ="INSERT INTO GENERE (descripcio) VALUES ('"+nomGenere+"')";
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[afegirGenere(String genere)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[afegirGenere(String genere)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void eliminarGenere(String idGenere) throws ControladorException {

		String query ="DELETE FROM GENERE WHERE id="+idGenere;
		try {
			/* Comprovem que cap pelicula depengui d'aquest genere */
			ResultSet rs = selectRS("SELECT id FROM PELICULA WHERE id_genere = "+idGenere);
			if(rs.next()){
				/* Hi ha pelicules que depen aquest genere */
				System.err.println("[ControladorPelicules]:[eliminarGenere(int idGenere)] query:"+query+"\n Error: Hi han pelicules que depenent d'aquest genere");
				throw new ControladorException("[ControladorPelicules]:[eliminarGenere(int idGenere)] query:"+query+"\n Error: Hi han pelicules que depenent d'aquest genere");
			}else{
				/* Realitzem la consula ja que no existeix cap pelicula que depengui d'aquest genere */
				update(query);
			}
			
			
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[eliminarNacionalitat(int idGenere)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[eliminarNacionalitat(int idGenere)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	/* 
	 * 
	 * <<<<<<<<<<<<<<<<FI GESTIO GENERE>>>>>>>>>>>>>>>>>>>>>>
	 *  
	 * */
}
